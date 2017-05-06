package springbook.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springbook.user.domain.User;
import springbook.user.sqlservice.SqlService;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoJdbc extends UserDao {
	@Autowired private SqlService sqlService;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void add(User user){
		jdbcTemplate.update(this.sqlService.getSql("userAdd"), user.getId(), user.getName(), user.getPassword(), user.getLevel().getIntValue(), user.getLogin(), user.getLikes(), user.getEmail());
	}
	
	@Override
	public User get(String id) {
		return jdbcTemplate.queryForObject(sqlService.getSql("userGet"), new Object[] {id}, (rs, rowNum) -> { return new User().setId(rs.getString("id")).setName(rs.getString("name")).setPassword(rs.getString("password")).setLevel(rs.getInt("level")).setLogin(rs.getInt("login")).setLikes(rs.getInt("likes")).setEmail(rs.getString("email"));
		});
	} 

	@Override
	public List<User> getAll() {
		return jdbcTemplate.query(sqlService.getSql("userGetAll"), new BeanPropertyRowMapper<User>(User.class));
	}
	
	@Override
	public void deleteAll() {
		jdbcTemplate.update(sqlService.getSql("userDeleteAll"));
	}
	
	@Override
	public int getCount() {
		return jdbcTemplate.queryForObject(sqlService.getSql("userGetCount"), Integer.class);
	}
	
	@Override
	public int update(User toBeUpdated) {
		return jdbcTemplate.update(sqlService.getSql("userUpdate"), toBeUpdated.getName(), toBeUpdated.getPassword(), toBeUpdated.getLevel().getIntValue(), toBeUpdated.getLogin(), toBeUpdated.getLikes(), toBeUpdated.getEmail(), toBeUpdated.getId());
	}
	
	
}
