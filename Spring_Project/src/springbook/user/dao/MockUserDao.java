package springbook.user.dao;

import springbook.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class MockUserDao extends UserDao {
	private List<User> users;
	private List<User> updated = new ArrayList();
	
	public MockUserDao(List<User> users){
		this.users = users; 
	}
	
	public List<User> getUpdated(){
		return updated;
	}
	
	public List<User> getAll(){
		return this.users;
	}


	public int update(User user){
		updated.add(user);
		return 0;
	}

	public void add(User user) { throw new UnsupportedOperationException(); }
	public void deleteAll() { throw new UnsupportedOperationException(); }
	public User get(String id) { throw new UnsupportedOperationException(); }
	public int getCount() { throw new UnsupportedOperationException(); }	
}
