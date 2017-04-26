package springbook.user.sqlservice;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import springbook.user.dao.UserDao;

public class XmlSqlService implements SqlService {
	private String sqlmapFile;
	private Map<String, String> userSqlMap = new HashMap<String, String>();
	
	public String getSqlmapFile() {
		return sqlmapFile;
	}

	public void setSqlmapFile(String sqlmapFile) {
		this.sqlmapFile = sqlmapFile;
	}

	public Map<String, String> getUserSqlMap() {
		return userSqlMap;
	}

	public void setUserSqlMap(Map<String, String> userSqlMap) {
		this.userSqlMap = userSqlMap;
	}

	@PostConstruct //이 Annotation은 빈의 초기화 method로 지정!
	public void loadSql(){
		try{
			InputStream is = UserDao.class.getResourceAsStream(this.sqlmapFile);
			Unmarshaller unmarshaller = JAXBContext.newInstance(Sqlmap.class.getPackage().getName()).createUnmarshaller();
			Sqlmap tempMap = (Sqlmap) unmarshaller.unmarshal(is);
			
			for(SqlType t : tempMap.sql){
				userSqlMap.put(t.key, t.value);
			}
			
		}catch(JAXBException e){
			throw new RuntimeException(e);
		}
	}


	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		String value = userSqlMap.get(key);
		System.out.println(value);
		if(value == null) throw new SqlRetrievalFailureException(key + "is not found");
		return value;
	}
}
