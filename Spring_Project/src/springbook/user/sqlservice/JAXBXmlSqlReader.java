package springbook.user.sqlservice;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import springbook.user.dao.UserDao;

public class JAXBXmlSqlReader implements SqlReader {
	private static final String DEFAULT_SQLMAP_FILE = "sqlmap.xml";
	private String sqlmapFile = DEFAULT_SQLMAP_FILE;
	
	public void setSqlmapFile(String sqlmapFile) {
		this.sqlmapFile = sqlmapFile;
	}

	@Override
	public void read(SqlRegistry sqlRegistry) {
		String contextPath = Sqlmap.class.getPackage().getName();
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(contextPath);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			InputStream is = UserDao.class.getResourceAsStream(sqlmapFile);
			Sqlmap tempMap = (Sqlmap)unmarshaller.unmarshal(is);
			for(SqlType t : tempMap.sql){
				sqlRegistry.registerSql(t.key, t.value);
			}
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		
		
		
	}

}
