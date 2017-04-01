package springbook.user.service;

import static org.junit.Assert.assertEquals;
import static springbook.user.service.UserServiceImpl.LIKE_COUNT_FOR_GOLD;
import static springbook.user.service.UserServiceImpl.LOGIN_COUNT_FOR_SILVER;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/Test_ApplicationContext.xml")
public class UserServiceTest extends UserServiceImpl{
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	UserDao userDao;
	List<User> users;
	
	@Before
	public void setUp(){
		users = Arrays.asList( new User("h1","hello","world",Level.BASIC,LOGIN_COUNT_FOR_SILVER-1,0, "h1@practice.com")
				              , new User("h2", "hello", "java", Level.BASIC, LOGIN_COUNT_FOR_SILVER, 0, "h2@practice.com")
				              , new User("h3", "hello", "c#", Level.SILVER, LOGIN_COUNT_FOR_SILVER, LIKE_COUNT_FOR_GOLD-1, "h3@practice.com")
				              , new User("h4", "hello", "scala", Level.SILVER, LOGIN_COUNT_FOR_SILVER, LIKE_COUNT_FOR_GOLD, "h4@practice.com")
							  , new User("h5", "hello", "python", Level.GOLD, LOGIN_COUNT_FOR_SILVER, 40, "h5@practice.com"));
	}
	
	@Test
	public void upgradeLevels() {
		userDao.deleteAll();
		for(User user: users) {
			userDao.add(user);
		}
		MockMailSender mockMailSender = new MockMailSender();
		userServiceImpl.setMailSender(mockMailSender);
		userServiceImpl.upgradeLevels();
		
		checkLevel(users.get(0), false);
		checkLevel(users.get(1), true);
		checkLevel(users.get(2), false);
		checkLevel(users.get(3), true);
		checkLevel(users.get(4), false);
	}
	
	private void checkLevel(User user, boolean isUpgraded) {
		User userUpdate = userDao.get(user.getId());
		if(isUpgraded){
			assertEquals(userUpdate.getLevel(), user.getLevel().nextLevel);
		}else{
			assertEquals(userUpdate.getLevel(), user.getLevel());
		}
	}	
}


class MockMailSender implements MailSender {
	private List<String> requests = new ArrayList<String>();
	
	public List<String> getRequests(){
		return requests;
	}
	
	public void send(SimpleMailMessage mailMessage) throws MailException {
		requests.add(mailMessage.getTo()[0]);
	}
	
	public void send(SimpleMailMessage[] mailMessage) throws MailException {
	}
}