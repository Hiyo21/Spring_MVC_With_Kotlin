package springbook.user.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/Test_ApplicationContext.xml")
public class UserServiceTest {
	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;
	List<User> users;
	
	@Before
	public void setUp(){
		users = Arrays.asList( new User("h1","hello","world",Level.BASIC,49,0)
				              , new User("h2", "hello", "java", Level.BASIC, 50, 0)
				              , new User("h3", "hello", "c#", Level.SILVER, 50, 29)
				              , new User("h4", "hello", "scala", Level.SILVER, 50, 30)
							  , new User("h5", "hello", "python", Level.GOLD, 50, 40));
		
	}
	
	@Test
	public void upgradeLevels() throws ClassNotFoundException, SQLException{
		userDao.deleteAll();
		for(User user: users) {
			userDao.add(user);
		}
		
		userService.upgradeLevel();
		
		checkLevel(users.get(0), false);
		checkLevel(users.get(1), true);
		checkLevel(users.get(2), false);
		checkLevel(users.get(3), true);
		checkLevel(users.get(4), false);
	}
	
	private void checkLevel(User user, boolean isUpgraded) throws ClassNotFoundException, SQLException{
		User userUpdate = userDao.get(user.getId());
		if(isUpgraded){
			assertEquals(userUpdate.getLevel(), user.getLevel().nextLevel);
		}else{
			assertEquals(userUpdate.getLevel(), user.getLevel());
		}
	}
	
}
