package springbook.user.sqlservice;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XmlSqlService implements SqlService, SqlRegistry, SqlReader {
	private SqlReader sqlReader;
	private SqlRegistry sqlRegistry;
	private Map<String, String> userSqlMap = new HashMap<String, String>();

	public void setUserSqlMap(Map<String, String> userSqlMap) {
		this.userSqlMap = userSqlMap;
	}

	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}

	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}

	@PostConstruct //이 Annotation은 빈의 초기화 method로 지정!
	public void loadSql(){
		this.sqlReader.read(this.sqlRegistry);
	}


	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
			try{
				return this.sqlRegistry.findSql(key);
			}catch(SqlNotFoundException e){
				throw new SqlRetrievalFailureException("sql not found ", e);
			}
	}

	@Override
	public void registerSql(String key, String sql) {
		userSqlMap.put(key, sql);
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		String value = userSqlMap.get(key);
		if(value == null) throw new SqlRetrievalFailureException(key + "is not found");
		return value;
	}

	@Override
	public void read(SqlRegistry sqlRegistry) {
		String contextPath = Sqlmap.class.getPackage().getName();
		try{
			JAXBContext context = JAXBContext.newInstance(contextPath);
		    Unmarshaller unmarshaller = context.createUnmarshaller();
		    InputStream is = Sqlmap.class.getResourceAsStream(contextPath);
		    Sqlmap sqlMap = (Sqlmap) unmarshaller.unmarshal(is);
		    for(SqlType t : sqlMap.getSql())
		    {
		    	sqlRegistry.registerSql(t.getKey(), t.getValue());
		    }
		}catch(JAXBException e){
			throw new RuntimeException(e);
		}
		
	}
}
