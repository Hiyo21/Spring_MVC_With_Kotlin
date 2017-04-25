package springbook.learningtest.spring.pointcut;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class PointcutExpressionTest {

	@Test
	public void methodSignaturePointcut() throws SecurityException, NoSuchMethodException{
		Map<String, boolean[]> pcExpression = new HashMap<String, boolean[]>();
		pcExpression.put("execution(* Hello (..))", new boolean[] {true, true, false, false, false, false});
		pcExpression.put("execution(* Hello (String))", new boolean[] {false, true, false, false, false, false});
		pcExpression.put("execution(* *(int, int))", new boolean[] {false, false, true, true, false, false});
		pcExpression.put("execution(* plus(int, int))", new boolean[] {false, false, false, true, false, false});
		pcExpression.put("execution(* *..TargetInterface.*(..))", new boolean[] {true, true, true, true, false, false});
		pcExpression.put("execution(* *..B*.*(..))", new boolean[] {false, false, false, false, false, true});
		
		pcExpression.forEach((k,v)-> { targetClassPointcutMatches(k, v); });
	}
	
	public void pointcutMatches(String expression, boolean shouldMatch, Class<?> clazz, String methodName, Class<?>...args) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(expression);
		
		try {
			assertEquals(pointcut.getClassFilter().matches(clazz) && 
					pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null)
					,shouldMatch);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public void targetClassPointcutMatches(String expression, boolean...expected) {
		pointcutMatches(expression, expected[0], Target.class, "Hello");
		pointcutMatches(expression, expected[1], Target.class, "Hello", String.class);
		pointcutMatches(expression, expected[2], Target.class, "minus", int.class, int.class);
		pointcutMatches(expression, expected[3], Target.class, "plus", int.class, int.class);
		pointcutMatches(expression, expected[4], Target.class, "method");
		pointcutMatches(expression, expected[5], Bean.class, "method");
	}
}
