package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnectionMaker implements ConnectionMaker {
    private final String DriverName = "org.postgresql.Driver";
	private final String DBAddress = "jdbc:postgresql://localhost:5432/postgres";
	private final String DBName = "Chris";
	private final String DBpassword = "1";
	
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DriverName);
		return DriverManager.getConnection(DBAddress, DBName, DBpassword);	
	}
	
}
