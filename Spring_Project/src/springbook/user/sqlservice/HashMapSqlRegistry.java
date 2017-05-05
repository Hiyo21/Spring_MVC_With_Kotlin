package springbook.user.sqlservice;

import java.util.HashMap;
import java.util.Map;

public class HashMapSqlRegistry implements SqlRegistry {
	private Map<String, String> userSqlMap = new HashMap<String, String>();
	
	public void setUserSqlMap(Map<String, String> userSqlMap) {
		this.userSqlMap = userSqlMap;
	}

	@Override
	public void registerSql(String key, String sql) {
		userSqlMap.put(key, sql);
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		String sql = userSqlMap.get(key);
		if(sql == null) throw new SqlNotFoundException("sql not found");
		return sql;
	}

}
