package springbook.user.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)  //Spring의 test context framework인 Junit 확장기능 지정
@ContextConfiguration(locations="/Test_ApplicationContext.xml")
public class UserDaoTest {
	@Autowired
	ApplicationContext context;
	@Autowired
	UserDao dao;
	User user1;
	User user2;
	User user3;
	
	@Before
	public void addUsers() {
		user1 = new User("h1","hello","world");
		user2 = new User("h2", "hello", "spring");
		user3 = new User("h3", "hi", "me");
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		dao.deleteAll();
		assertEquals(dao.getCount(),0);
		
		dao.add(user1);
		dao.add(user2);
		
		assertNotEquals(dao.get("h1").getId(), dao.get("h2").getId());
		assertEquals(dao.get("h1").getName(), dao.get("h2").getName());
		assertEquals(dao.getCount(), 2);
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		dao.deleteAll();
		assertEquals(dao.getCount(),0);
		
		dao.get("unknown_id");
	}
	
}
