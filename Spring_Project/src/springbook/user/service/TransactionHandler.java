package springbook.user.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {
	private Object target;
	private PlatformTransactionManager ptManager;
	private String pattern;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().startsWith(pattern)){
			return invokeInTransaction(method, args);
		}else{
			return method.invoke(target, args);
		}
	}
	
	public void setPtManager(PlatformTransactionManager ptManager){
		this.ptManager = ptManager;
	}
	
	public void setPattern(String pattern){
		this.pattern = pattern;
	}
	
	private Object invokeInTransaction(Method method, Object[] args) throws Throwable {
		TransactionStatus trStatus = this.ptManager.getTransaction(new DefaultTransactionDefinition());
		try{
			Object ret = method.invoke(target, args);
			this.ptManager.commit(trStatus);
			return ret;
		}catch (InvocationTargetException e) {
			this.ptManager.rollback(trStatus);
			throw e.getTargetException();
		}
	}

}
