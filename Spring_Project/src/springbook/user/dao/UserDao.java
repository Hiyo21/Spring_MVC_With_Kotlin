package springbook.user.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.util.List;

public class UserDao {
	protected User user;
	public DataSource dataSource;
	protected JdbcTemplate jdbcTemplate;
		
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) {		
		jdbcTemplate.update("insert into users values(?, ?, ?, ?, ?, ?, ?)", user.getId(),user.getName(),user.getPassword(), user.getLevel().value, user.getLogin(), user.getLikes(), user.getEmail());
	}
	
	public User get(String id) {
		return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] {id}, (rs, rowNum) -> { return new User().setId(rs.getString("id")).setName(rs.getString("name")).setPassword(rs.getString("password")).setLevel(rs.getInt("level")).setLogin(rs.getInt("login")).setLikes(rs.getInt("likes")).setEmail(rs.getString("email"));
		});
	}
	
	public void deleteAll() {
		jdbcTemplate.update("truncate users");
	}
	
	public int getCount() {
		return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
	}

	public int update(User toBeUpdated) {
	 	return jdbcTemplate.update("update users set name = ?, password = ?, level = ?, login = ?, likes = ?, email = ? where id = ?", toBeUpdated.getName(), toBeUpdated.getPassword(), toBeUpdated.getLevel().value, toBeUpdated.getLogin(), toBeUpdated.getLikes(), toBeUpdated.getEmail(), toBeUpdated.getId());			
	}
	
	public List<User> getAll(){
		return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<User>(User.class));
	}
}