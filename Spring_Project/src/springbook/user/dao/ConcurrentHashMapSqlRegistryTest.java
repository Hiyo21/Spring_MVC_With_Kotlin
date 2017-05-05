package springbook.user.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import springbook.issuetracker.sqlservice.UpdatableSqlRegistry;
import springbook.user.sqlservice.ConcurrentHashMapSqlRegistry;
import springbook.user.sqlservice.SqlNotFoundException;
import springbook.user.sqlservice.SqlUpdateFailureException;

public class ConcurrentHashMapSqlRegistryTest {
	UpdatableSqlRegistry sqlRegistry;
	
	@Before
	public void setUp(){
		sqlRegistry = new ConcurrentHashMapSqlRegistry();
		sqlRegistry.registerSql("KEY1", "SQL1");
		sqlRegistry.registerSql("KEY2", "SQL2");
		sqlRegistry.registerSql("KEY3", "SQL3");
	}
	
	@Test
	public void find(){
		checkFindResult("SQL1", "SQL2", "SQL3");
	}
	
	private void checkFindResult(String expected1, String expected2, String expected3){
		assertEquals(sqlRegistry.findSql("KEY1"), expected1);
		assertEquals(sqlRegistry.findSql("KEY2"), expected2);
		assertEquals(sqlRegistry.findSql("KEY3"), expected3);
	}
	
	@Test(expected=SqlNotFoundException.class)
	public void unknownKey(){
		sqlRegistry.findSql("99999");
	}
	
	@Test
	public void updateSingle(){
		sqlRegistry.updateSql("KEY2", "Modified2");
		checkFindResult("SQL1", "Modified2", "SQL3");
	}
	
	@Test
	public void updateMulti(){
		Map<String, String> sqlmap = new HashMap<String, String>();
		sqlmap.put("KEY1", "Modified1");
		sqlmap.put("KEY3", "Modified3");
		
		sqlRegistry.updateSql(sqlmap);
		checkFindResult("Modified1", "SQL2", "Modified3");
	}
	
	@Test(expected=SqlUpdateFailureException.class)
	public void updateWithNotExistingKey(){
		sqlRegistry.updateSql("99999", "Modified2");
	}
	
}
