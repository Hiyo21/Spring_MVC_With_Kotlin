package springbook.user.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

public class UserDao {
	private User user;
	private DataSource dataSource;
	private JdbcContext jdbcContext;
		
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
		this.dataSource = dataSource;
	}

	public JdbcContext getJdbcContext() {
		return jdbcContext;
	}

	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public void add(User user) throws ClassNotFoundException, SQLException{		
		jdbcContext.executeSimpleSql("insert into users(id, name, password) values(?, ?, ?)", user.getId(),user.getName(),user.getPassword());
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("select id, name, password from users where id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			this.user = new User();
			this.user.setId(rs.getString("id"));
			this.user.setName(rs.getString("name"));
			this.user.setPassword(rs.getString("password"));
		}
		rs.close();
		ps.close();
		c.close();
		
		if(user == null) throw new EmptyResultDataAccessException(1);
		
		return this.user;
	}
	
	public void deleteAll() throws ClassNotFoundException, SQLException{
		jdbcContext.executeSimpleSql("truncate users");
	}
	
	public int getCount() throws ClassNotFoundException, SQLException{
		int cnt = 0;
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM users");
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return cnt;
	}
}