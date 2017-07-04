package learningtest.DBConnection

import org.apache.ibatis.session.SqlSessionFactory
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("/applicationContext/test-applicationContext.xml")
class MyBatisConnectionTest {

    @Autowired private lateinit var sqlSessionFactory: SqlSessionFactory

    @Test
    fun testConnection(){
        Assert.assertNotNull(sqlSessionFactory)
        Assert.assertNotNull(sqlSessionFactory.openSession())
        val session1 = sqlSessionFactory.openSession()
        val session2 =  sqlSessionFactory.openSession()
        Assert.assertNotEquals(session1,session2)
        session1.close()
        session2.close()
    }
}