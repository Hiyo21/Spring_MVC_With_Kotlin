package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {

	@Bean
	public DataSource dataSource(){
		return new SimpleDriverDataSource(org.postgresql.Driver.class, "jdbc:postgresql://192.168.2.102:5432/spring_practice", "Chris", "1234");
	}
	
	@Bean
	public UserDao userDao(){
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao();
	}
	
}
