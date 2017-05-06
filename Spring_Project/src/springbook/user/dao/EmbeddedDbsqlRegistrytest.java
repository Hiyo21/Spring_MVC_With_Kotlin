package springbook.user.dao;

import org.junit.After;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import springbook.issuetracker.sqlservice.EmbeddedDbSqlRegistry;
import springbook.issuetracker.sqlservice.UpdatableSqlRegistry;
import springbook.user.sqlservice.SqlUpdateFailureException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2017-05-06.
 */
public class EmbeddedDbsqlRegistrytest extends AbstractUpdatableSqlRegistryTest {
    EmbeddedDatabase db;

    @Override
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:/schema.sql")
                .build();

        EmbeddedDbSqlRegistry embeddedDbSqlRegistry = new EmbeddedDbSqlRegistry();
        embeddedDbSqlRegistry.setDataSource(db);

        return embeddedDbSqlRegistry;
    }

    @Test
    public void transactionalUpdate(){
        checkFindResult("SQL1","SQL2","SQL3");
        Map<String, String> sqlmap = new HashMap<>();
        sqlmap.put("KEY1", "Modified1");
        sqlmap.put("99999", "Modified99999");

        try {
            sqlRegistry.updateSql(sqlmap);
            fail();
        }catch(SqlUpdateFailureException e){

        }
        checkFindResult("SQL1","SQL2","SQL3");
    }

    private void fail() {
    }

    @After
    public void tearDown(){
        db.shutdown();
    }
}
