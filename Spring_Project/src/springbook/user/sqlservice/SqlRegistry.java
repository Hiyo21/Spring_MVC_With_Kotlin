package springbook.user.sqlservice;

public interface SqlRegistry {
	void registerSql(String key, String sql); // 읽어들인 SQL을 이 메소드를 이용해 레지스트리에 저장한다.
	
	String findSql(String key) throws SqlNotFoundException;
}
