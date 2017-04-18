package springbook.learningtest.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionAdvice implements MethodInterceptor {
	PlatformTransactionManager ptManager;
	
	public void setPtManager(PlatformTransactionManager ptManager) {
		this.ptManager = ptManager;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		TransactionStatus trStatus = ptManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			Object ret = invocation.proceed();
			ptManager.commit(trStatus);
			return ret;
		}catch(RuntimeException e){
			ptManager.rollback(trStatus);
			throw e;
		}
	}

}
