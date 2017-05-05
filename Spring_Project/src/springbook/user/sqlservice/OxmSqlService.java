package springbook.user.sqlservice;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import springbook.user.dao.UserDao;

public class OxmSqlService implements SqlService{
	private final BaseSqlService baseSqlService = new BaseSqlService();
	private final OxmSqlReader oxmSqlReader = new OxmSqlReader();
	private SqlRegistry sqlRegistry = new HashMapSqlRegistry();
	
	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller){
		this.oxmSqlReader.setUnmarshaller(unmarshaller);
	}
	
	public void setSqlmap(Resource sqlmap){
		this.oxmSqlReader.setSqlmap(sqlmap);
	}
	
	@PostConstruct
	public void loadSql(){
		this.baseSqlService.setSqlReader(oxmSqlReader);
		this.baseSqlService.setSqlRegistry(sqlRegistry);
		this.baseSqlService.loadSql();
	}
	
	
	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		return this.baseSqlService.getSql(key);
	}
	
	private class OxmSqlReader implements SqlReader{
		private Unmarshaller unmarshaller;
		private static final String DEFAULT_SQLMAP = "sqlmap.xml";
		private Resource sqlmap = new ClassPathResource(DEFAULT_SQLMAP, UserDao.class);
		
		public void setUnmarshaller(Unmarshaller unmarshaller) {
			this.unmarshaller = unmarshaller;
		}

		public void setSqlmap(Resource sqlmap) {
			this.sqlmap = sqlmap;
		}

		@Override
		public void read(SqlRegistry sqlRegistry) {
			try{
				Source source = new StreamSource(sqlmap.getInputStream());
				Sqlmap sqlmap = (Sqlmap)unmarshaller.unmarshal(source);
				for(SqlType t : sqlmap.sql){
					sqlRegistry.registerSql(t.key, t.value);
				}
			}catch(IOException e){
				throw new IllegalArgumentException("sqlmap file @" + sqlmap.getFilename() + "not found", e);
			}
		}
	}

}
