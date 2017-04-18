package springbook.user.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionAdvice implements MethodInterceptor {
	PlatformTransactionManager ptManager;
	
	public PlatformTransactionManager getPtManager() {
		return ptManager;
	}

	public void setPtManager(PlatformTransactionManager ptManager) {
		this.ptManager = ptManager;
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		TransactionStatus trStatus = this.ptManager.getTransaction(new DefaultTransactionDefinition());
		try{
			Object ret = invocation.proceed();
			this.ptManager.commit(trStatus);
			return ret;
		}catch(RuntimeException e){
			this.ptManager.rollback(trStatus);
			throw e;
		}
	}

}
