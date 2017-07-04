package learningtest.DBConnection

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.sql.Connection
import javax.sql.DataSource


@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("/applicationContext/test-applicationContext.xml")
class MariaDBConnectionTest : DBConnectionTest {
    var conn:Connection? = null
    @Autowired lateinit var dataSource:DataSource

    @Before
    override fun setupConnection() {
        conn = dataSource.connection
    }

    @Test
    override fun testConnection(){
        Assert.assertNotNull(conn)
    }

}