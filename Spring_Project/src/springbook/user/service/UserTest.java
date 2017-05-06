package springbook.user.service;

import org.junit.Before;
import org.junit.Test;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import static org.junit.Assert.assertEquals;

public class UserTest {
	User user;
	
	@Before
	public void setUser(){
		user = new User();
	}
	
	@Test
	public void upgradeLevel(){
		Level[] levels = Level.values();
		for (Level level : levels){
			if (level.nextLevel == null) continue;
			user.setLevel(level.value);
			user.upgradeLevel();
			assertEquals(user.getLevel(), level.nextLevel);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void cannnotUpgradeLevel(){
		Level[] levels = Level.values();
		for (Level level : levels){
			if(level.nextLevel != null) continue;
			user.setLevel(level.value);
			user.upgradeLevel();
		}
	}
	
	@Test
	public void newUser(){
		User defaultUser = new User("h7", "hhh", "jjk");
		assertEquals(defaultUser.getLevel(), Level.BASIC);
		assertEquals(defaultUser.getLogin(), 0);
		assertEquals(defaultUser.getLikes(), 0);
	}
}
