package springbook.user.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import springbook.user.domain.Level;
import springbook.user.domain.User;

public class UserDao {
	private User user;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
		
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void add(User user) throws ClassNotFoundException, SQLException{		
		jdbcTemplate.update("insert into users values(?, ?, ?, ?, ?, ?)", user.getId(),user.getName(),user.getPassword(), user.getLevel().value, user.getLogin(), user.getLikes());
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] {id}, (rs, rowNum) -> { return new User().setId(rs.getString("id")).setName(rs.getString("name")).setPassword(rs.getString("password")).setLevel(rs.getInt("level")).setLogin(rs.getInt("login")).setLikes(rs.getInt("likes"));
		});
	}
	
	public void deleteAll() throws ClassNotFoundException, SQLException{
		jdbcTemplate.update("truncate users");
	}
	
	public int getCount() throws ClassNotFoundException, SQLException{
		return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
	}

	public int update(User toBeUpdated) {
	 	return jdbcTemplate.update("update users set name = ?, password = ?, level = ?, login = ?, likes = ? where id = ?", toBeUpdated.getName(), toBeUpdated.getPassword(), toBeUpdated.getLevel().value, toBeUpdated.getLogin(), toBeUpdated.getLikes(), toBeUpdated.getId());			
	}
	
	public void upgradeLevel(){
		List<User> userList = jdbcTemplate.queryForList("select * from users", User.class);
		
		for(User user : userList){
			Boolean changed = null;
			switch(user.getLevel()){
			case BASIC:
				if(user.getLogin() >= 50){
					user.setLevel(Level.SILVER.value);
					changed = true;
				}
				break;
			case SILVER:
				if(user.getLikes() >= 30){
					user.setLevel(Level.GOLD.value);
					changed = true;
				}
				break;
			default:
				changed = false;
				break;
			}
			if(changed) update(user);
		}
	}
}