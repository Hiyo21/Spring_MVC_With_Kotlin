package springbook.user.sqlservice;

public class DefaultSqlService extends BaseSqlService {
	public DefaultSqlService() {
		setSqlReader(new JAXBXmlSqlReader());
		setSqlRegistry(new HashMapSqlRegistry());
	}
}
