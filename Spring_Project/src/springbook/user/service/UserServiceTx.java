package springbook.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import springbook.user.domain.User;

public class UserServiceTx implements UserService{
	@Autowired
	UserService userService;
	PlatformTransactionManager ptManager;
	TransactionStatus trStatus;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPtManager(PlatformTransactionManager ptManager) {
		this.ptManager = ptManager;
	}


	@Override
	public void add(User user) {
		trStatus = ptManager.getTransaction(new DefaultTransactionDefinition());
		try{
			userService.add(user);
		}catch(RuntimeException e){
			ptManager.rollback(trStatus);
			throw e;
		}

	}

	@Override
	public void upgradeLevels() {
		trStatus = ptManager.getTransaction(new DefaultTransactionDefinition());
		try{
			userService.upgradeLevels();
			ptManager.commit(trStatus);
		}catch(RuntimeException e){
			ptManager.rollback(trStatus);
			throw e;
		}
		
	}

}
