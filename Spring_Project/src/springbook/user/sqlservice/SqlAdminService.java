package springbook.user.sqlservice;

import springbook.issuetracker.sqlservice.UpdatableSqlRegistry;

public class SqlAdminService{
	private UpdatableSqlRegistry updatableSqlRegistry;

	public UpdatableSqlRegistry getUpdatableSqlRegistry() {
		return updatableSqlRegistry;
	}

	public void setUpdatableSqlRegistry(UpdatableSqlRegistry updatableSqlRegistry) {
		this.updatableSqlRegistry = updatableSqlRegistry;
	}
	
	//public void updateEventListener(UpdateEvent event) {
	//	this.updatableSqlRegistry.updateSql(event.get(KEY_ID), event.get(SQL_ID));
	//}
}
