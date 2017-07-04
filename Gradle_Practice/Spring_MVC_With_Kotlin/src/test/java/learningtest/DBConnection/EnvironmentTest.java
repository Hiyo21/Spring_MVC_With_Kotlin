package learningtest.DBConnection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext/test-applicationContext.xml")
@PropertySource("/properties/database.properties")
public class EnvironmentTest {
    @Autowired
    private Environment env;

    @Test
    public void testEnvironment(){
        System.out.println(env.getProperty("username"));
        Assert.assertNotNull(env);
    }
}
