package springbook.user.service;

import java.util.List;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

public class UserService {
	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void upgradeLevel(){
		List<User> userList = userDao.getAll();
		
		for(User user : userList){
			if(canUpgradeLevel(user)) upgradeLevel(user);
		}
	}
	
	private void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}

	private boolean canUpgradeLevel(User user){
		Level level = user.getLevel();
		switch(level){
		case BASIC:
			return (user.getLogin() >= 50);
		case SILVER:
			return (user.getLikes() >= 30);
		case GOLD:
			return false;
		default:
			throw new IllegalArgumentException("unknown level");
		}
	}
	
}