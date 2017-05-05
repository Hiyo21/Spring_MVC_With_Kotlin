package springbook.learningtest.embeddeddatabase;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmbeddedDatabaseTest {
	EmbeddedDatabase db;
	JdbcTemplate template;
	
	@Before
	public void setUp(){
		db = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:/springbook/learningtest/embeddeddatabase/schema.sql")
				.addScript("classpath:/springbook/learningtest/embeddeddatabase/data.sql")
				.build();
		template = new JdbcTemplate(db);
	}
	
	@After
	public void shutdown(){
		db.shutdown();
	}
	
	@Test
	public void initData(){

		assertEquals((int)template.queryForObject("select count(*) from sqlmap", Integer.class), 2);
		List<Map<String, Object>>list = template.queryForList("select * from sqlmap order by key_");
		assertEquals((String)list.get(0).get("key_"), "KEY1");
		assertEquals((String)list.get(0).get("sql_"), "SQL1");
		assertEquals((String)list.get(1).get("key_"), "KEY2");
		assertEquals((String)list.get(1).get("sql_"), "SQL2");
	}
	
	@Test
	public void insert(){
		template.update("insert into sqlmap(key_, sql_) values(?, ?)", "KEY3", "SQL3");
		assertEquals((int)template.queryForObject("select count(*) from sqlmap", Integer.class), 3);
	}
}
