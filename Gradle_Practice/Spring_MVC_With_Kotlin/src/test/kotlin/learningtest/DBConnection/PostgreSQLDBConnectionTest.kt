package learningtest.DBConnection

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.sql.Connection
import java.sql.DriverManager

class PostgreSQLDBConnectionTest : DBConnectionTest {
    var conn:Connection? = null

    @Before
    override fun setupConnection() {
        val url:String = "jdbc:postgresql://localhost:5432/postgres"
        val username = "postgres"
        val password = "2249"
        conn = DriverManager.getConnection(url, username, password)
    }

    @Test
    override fun testConnection(){
        println(conn)
        Assert.assertNotNull(conn)
    }


}