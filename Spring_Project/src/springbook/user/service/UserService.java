package springbook.user.service;

import org.springframework.transaction.annotation.Transactional;
import springbook.user.domain.User;

import java.util.List;

@Transactional
public interface UserService {
	void add(User user);
	void upgradeLevels();
	void update(User user);
	void deleteAll();
	
	@Transactional(readOnly=true)
	User get(String id);
	
	@Transactional(readOnly=true)
	List<User> getAll();
	
	
}
