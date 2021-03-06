package springbook.user.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)  //Spring의 test context framework인 Junit 확장기능 지정
@ContextConfiguration(classes= springbook.configuration.AppContext.class)
public class UserDaoTest {
	@Autowired  UserDao dao;
	User user1;
	User user2;
	User user3;
	int exitCode;
	
	@Before
	public void addUsers() {
		user1 = new User("h1","hello","world", Level.BASIC, 1, 0, "h1@practice.com");
		user2 = new User("h2", "hello", "spring", Level.SILVER, 55, 10, "h2@practice.com");
		user3 = new User("h3", "hi", "me", Level.GOLD, 100, 40, "h3@practice.com");
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {

		dao.deleteAll();
		assertEquals(dao.getCount(),0);
		dao.add(user1);
		dao.add(user2);
		dao.add(user3);
		
		assertNotEquals(dao.get("h1").getId(), dao.get("h2").getId());
		assertEquals(dao.get("h1").getName(), dao.get("h2").getName());
		assertEquals(dao.getCount(), 3);
		
		User userget1 = dao.get(user1.getId());
	    checkSameUser(userget1, user1);
	    
	    User userget2 = dao.get(user2.getId());
	    checkSameUser(userget2, user2);
	    
	    User userget3 = dao.get(user3.getId());
	    checkSameUser(userget3, user3);
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		dao.deleteAll();
		assertEquals(dao.getCount(),0);
		
		dao.get("unknown_id");
	}
	
	private void checkSameUser(User user1, User user2){
		assertEquals(user1.getId(), user2.getId());
		assertEquals(user1.getName(), user2.getName());
		assertEquals(user1.getPassword(), user2.getPassword());
		assertEquals(user1.getLevel(), user2.getLevel());
		assertEquals(user1.getLogin(), user2.getLogin());
		assertEquals(user1.getLikes(), user2.getLikes());
		assertEquals(user1.getEmail(), user2.getEmail());
	}
	
	@Test
	public void update() throws ClassNotFoundException, SQLException {
		dao.deleteAll();
		dao.add(user1);
		
		User toBeUpdated = dao.get(user1.getId());
		
		toBeUpdated.setName("hhh").setPassword("111").setLevel(Level.GOLD.value).setLogin(1000).setLikes(999).setEmail("hhh@email.com");
		exitCode = dao.update(toBeUpdated);
		assertEquals(exitCode, 1);
		
		User userToBeSearched = dao.get(toBeUpdated.getId());
		checkSameUser(userToBeSearched, toBeUpdated);
	}
}
