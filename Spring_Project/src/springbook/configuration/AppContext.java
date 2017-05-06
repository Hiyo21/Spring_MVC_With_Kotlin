package springbook.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springbook.issuetracker.sqlservice.EmbeddedDbSqlRegistry;
import springbook.user.dao.JdbcContext;
import springbook.user.dao.UserDao;
import springbook.user.service.UserService;
import springbook.user.sqlservice.*;

import javax.sql.DataSource;

/**
 * Created by Chris on 2017-05-06.
 */
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = "springbook.user")
public class AppContext {
    @Autowired UserDao userDao;
    @Autowired UserService userService;

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(org.postgresql.Driver.class);
        simpleDriverDataSource.setUrl("jdbc:postgresql://mypractice.c5ro0f7bp99v.ap-northeast-1.rds.amazonaws.com:5432/myPractice");
        simpleDriverDataSource.setUsername("shoonara2");
        simpleDriverDataSource.setPassword("clee0121");
        return simpleDriverDataSource;
    }

    @Bean
    public PlatformTransactionManager ptManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }


    @Bean
    public SqlService sqlService() {
        OxmSqlService sqlService = new OxmSqlService();
        sqlService.setUnmarshaller(unmarshaller());
        sqlService.setSqlRegistry(sqlRegistry());
        return sqlService;
    }

    @Bean
    public Unmarshaller unmarshaller(){
        Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setContextPath("springbook.user.sqlservice");
        return unmarshaller;
    }

    @Bean
    public DataSource embeddedDatabase(){
        return new EmbeddedDatabaseBuilder().setName("embeddedDatabase").setType(EmbeddedDatabaseType.HSQL).addScript("classpath:/schema.sql").build();
    }

    @Bean
    public SqlRegistry sqlRegistry(){
        EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
        sqlRegistry.setDataSource(embeddedDatabase());
        return sqlRegistry;
    }

    @Bean
    public SqlReader sqlReader() {
        JAXBXmlSqlReader sqlReader = new JAXBXmlSqlReader();
        sqlReader.setSqlmapFile("classpath:/sqlmap.xml");
        return sqlReader;
    }

    @Bean
    public JdbcContext jdbcContext() {
        JdbcContext jdbcContext = new JdbcContext();
        jdbcContext.setDataSource(dataSource());
        return jdbcContext;
    }


}
