package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void workWithStrategy(StatementStrategy stmt, String...args) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		
		try{
			c = this.getDataSource().getConnection();
			
			ps = stmt.makePreparedStatement(c);
			if(args.length > 0){
				for(int i = 1 ; i <= args.length ; i++){
					ps.setString(i, args[i-1]);
				}
			}
			ps.executeUpdate();
		}
		catch(SQLException sqle){
			throw sqle;
		}
		finally{
			ps.close();
			c.close();
		}
	}
	
	public void executeSimpleSql(final String query, String... args) throws SQLException{
		workWithStrategy(x -> x.prepareStatement(query), args);

	}
}
