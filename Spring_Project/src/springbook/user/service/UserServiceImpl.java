package springbook.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

public class UserServiceImpl implements UserService {
	UserDao userDao;
	@Autowired
	PlatformTransactionManager ptManager;
	MailSender mailSender;

	public static final int LOGIN_COUNT_FOR_SILVER = 50;
	public static final int LIKE_COUNT_FOR_GOLD = 30;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setPtManager(PlatformTransactionManager ptManager) {
		this.ptManager = ptManager;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void add(User user) {	
		userDao.add(user);
	}
	
	public void upgradeLevels() {
		List<User> userList = userDao.getAll();
		
		for(User user : userList){
			if(canUpgradeLevel(user)) upgradeLevel(user);
		}
	}
	
	private void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user); 
		sendUpgradeNoticeMail(user);
	}

	private void sendUpgradeNoticeMail(User user) {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable","true");
		props.put("mail.debug", "true"); 
		props.put("mail.smtp.auth", "true");
		
		if(mailSender.getClass().equals(JavaMailSenderImpl.class)) ((JavaMailSenderImpl)mailSender).setJavaMailProperties(props);
		
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shoonara21@gmail.com");
		message.setTo("shoonara21@gmail.com");
		message.setSubject("upgrade안내");
		message.setText("사용자님의 등급이 " + user.getLevel().name() + "으로 승급되었습니다.");
		
		mailSender.send(message);
	}

	private boolean canUpgradeLevel(User user){
		Level level = user.getLevel();
		switch(level){
		case BASIC:
			return (user.getLogin() >= LOGIN_COUNT_FOR_SILVER);
		case SILVER:
			return (user.getLikes() >= LIKE_COUNT_FOR_GOLD);
		case GOLD:
			return false;
		default:
			throw new IllegalArgumentException("unknown level");
		}
	}


	
}