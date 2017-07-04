package learningtest.DBConnection

import org.junit.Assert
import org.junit.Test
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.SimpleDriverDataSource


@Configuration
open class DatasourceConnectionTest : DBConnectionTest {
    var ds:SimpleDriverDataSource = SimpleDriverDataSource()

    @Bean
    override fun setupConnection() {
        ds.setDriverClass(org.mariadb.jdbc.Driver::class.java)
        ds.url="jdbc:mysql://localhost:3306/springmvc"
        ds.username="chris"
        ds.password="c2249l"

       }

    @Test
    override fun testConnection() {
        setupConnection()
         Assert.assertNotNull(ds.getConnection("chris","c2249l"))
       }
}