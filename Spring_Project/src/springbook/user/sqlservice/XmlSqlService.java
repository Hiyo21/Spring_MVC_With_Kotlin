package springbook.user.sqlservice;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import springbook.user.dao.UserDao;

public class XmlSqlService implements SqlService {
	private Map<String, String> userSqlMap = new HashMap<String, String>();
	
	public XmlSqlService() {
		String contextPath = Sqlmap.class.getPackage().getName();
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(contextPath);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			InputStream is = UserDao.class.getResourceAsStream("/springbook/user/dao/sqlmap.xml");
			Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(is);
			
			for(SqlType sql : sqlmap.getSql()){
				userSqlMap.put(sql.getKey(), sql.getValue());
			}
		} catch (JAXBException e){
			throw new RuntimeException();
		}
	}	
	
	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		String sql = userSqlMap.get(key);
		if(sql == null) throw new SqlRetrievalFailureException(key + "is not found");
		return sql;
	}

}
