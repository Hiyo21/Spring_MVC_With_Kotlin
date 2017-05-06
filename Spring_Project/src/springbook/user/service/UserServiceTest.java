package springbook.user.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springbook.user.dao.MockUserDao;
import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = springbook.configuration.AppContext.class)
public class UserServiceTest extends UserServiceImpl{
	@Autowired UserService userService;
	@Autowired UserService testUserService;
	@Autowired UserDao userDao;
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
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		
		MockUserDao mockUserDao = new MockUserDao(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		MockMailSender mockMailSender = new MockMailSender();
		userServiceImpl.setMailSender(mockMailSender);
		userServiceImpl.upgradeLevels();
		
		List<User> updated= mockUserDao.getUpdated();
		assertEquals(updated.size(), 2);
		checkUserAndLevel(updated.get(0), "h2", Level.SILVER);
		checkUserAndLevel(updated.get(1), "h4", Level.GOLD);
		
		
		List<String> request = mockMailSender.getRequests();
		assertEquals(request.size(), 0);
		//assertEquals(request.get(0), "shoonara21@gmail.com");
		//assertEquals(request.get(1), "shoonara21@gmail.com");
	}
	
	
	@Test
	public void mockUpgradeLevels() throws Exception {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		
		UserDao mockUserDao = mock(UserDao.class);
		when(mockUserDao.getAll()).thenReturn(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		
		MailSender mockMailSender = mock(MailSender.class);
		userServiceImpl.setMailSender(mockMailSender);
		
		userServiceImpl.upgradeLevels();
		
		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao).update(users.get(1));
		assertEquals(users.get(1).getLevel(), Level.SILVER);
		verify(mockUserDao).update(users.get(3));
		assertEquals(users.get(3).getLevel(), Level.GOLD);
		
	}
	
	@Test
	public void upgradeAllOrNothing() {
		userDao.deleteAll();
		for(User user : users) userDao.add(user);
		
		try{
			this.testUserService.upgradeLevels();
			//fail("TestUserServiceException expected");
		}catch(Exception e){
			
		}
		checkLevel(users.get(0), false);
	}
	
	// transactionManager를 이용해서 transaction을 수동 시작. 원하는 단위로 transaction을 묶는다.
	@Test
	public void transactionSync() {
		userDao.deleteAll();
		assertEquals(userDao.getCount(), 0);
		
		DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
		//txDefinition.setReadOnly(true);
		TransactionStatus txStatus = ptManager.getTransaction(txDefinition);
		
		
		//userService.deleteAll();
		
		userService.add(users.get(1));
		userService.add(users.get(0));
		
		assertEquals(userDao.getCount(), 2);
		
		ptManager.rollback(txStatus);
		assertEquals(userDao.getCount(), 0);
		//ptManager.commit(txStatus);
	}
	
	private void checkUserAndLevel(User updated, String expectedId, Level expectedLevel) {
		assertEquals(updated.getId(), expectedId);
		assertEquals(updated.getLevel(), expectedLevel);
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