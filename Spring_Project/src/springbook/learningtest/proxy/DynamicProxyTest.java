package springbook.learningtest.proxy;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import springbook.learningtest.jdk.Hello;
import springbook.learningtest.jdk.HelloTarget;
import springbook.learningtest.jdk.UppercaseHandler;

public class DynamicProxyTest{
	@Test
	public void simpleProxy(){
		Hello proxiedHello = (Hello)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] {Hello.class}, new UppercaseHandler(new HelloTarget()));
	}
	
	@Test
	public void proxyFactoryBean() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		pfBean.addAdvice(new UppercaseAdvice());
		Hello proxiedHello = (Hello) pfBean.getObject();
		
		assertEquals(proxiedHello.sayHello("Toby"), "HELLO TOBY");
		assertEquals(proxiedHello.sayHi("Toby"), "HI TOBY");
		assertEquals(proxiedHello.sayThankYou("Toby"), "THANK YOU TOBY");
	}
	
	@Test
	public void pointCutAdvisor(){
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		
		NameMatchMethodPointcut pointCut = new NameMatchMethodPointcut();
		pointCut.setMappedName("sayH*");
		
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointCut, new UppercaseAdvice()));
		
		Hello proxiedHello = (Hello) pfBean.getObject();
		
		assertEquals(proxiedHello.sayHello("Toby"), "HELLO TOBY");
		assertEquals(proxiedHello.sayHi("Toby"), "HI TOBY");
		assertEquals(proxiedHello.sayThankYou("Toby"), "Thank You Toby");
	}
	
	static class UppercaseAdvice implements MethodInterceptor {
		public Object invoke(MethodInvocation invocation) throws Throwable {
			String ret = (String)invocation.proceed();
			return ret.toUpperCase();
		}

	}
	
	//expanded pointcut
	@Test
	public void classNamePointcutAdvisor() {
		//get pointcut ready
		
		NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut() {
			public ClassFilter getClassFilter() {
				return new ClassFilter(){
					public boolean matches(Class<?> clazz) {
						return clazz.getSimpleName().startsWith("HelloT");
					}
				};
			}
		};
		
		classMethodPointcut.setMappedName("sayH*");
		
		checkAdviced(new HelloTarget(), classMethodPointcut, true);
		
		class HelloWorld extends HelloTarget {};
		checkAdviced(new HelloWorld(), classMethodPointcut, false);
		
		class HelloToby extends HelloTarget {};
		checkAdviced(new HelloToby(), classMethodPointcut, true);
	}

	private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
			ProxyFactoryBean pfBean = new ProxyFactoryBean();
			pfBean.setTarget(target);
			pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
			Hello proxiedHello = (Hello)pfBean.getObject();
			
			if(adviced) 
			{
				assertEquals(proxiedHello.sayHello("Toby"), ("HELLO TOBY"));
				assertEquals(proxiedHello.sayHi("Toby"),("HI TOBY"));
				assertEquals(proxiedHello.sayThankYou("Toby"), ("Thank You Toby"));
			}else
			{
				assertEquals(proxiedHello.sayHello("Toby"), ("Hello Toby"));
				assertEquals(proxiedHello.sayHi("Toby"), ("Hi Toby"));
				assertEquals(proxiedHello.sayThankYou("Toby"), ("Thank You Toby"));
			}		
	}
	
	
}