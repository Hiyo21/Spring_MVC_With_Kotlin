package mvc.controller

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@WebAppConfiguration
@ContextConfiguration(locations = arrayOf("file:src/main/webapp/WEB-INF/**/dispatcher-servlet.xml"))
class MockMvcTest {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired private lateinit var wac: WebApplicationContext
    private lateinit var mockMvc: MockMvc

    @Before
    fun setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
        logger.info("...setup")
    }

    @Test
    fun testActionA(){
        mockMvc.perform(MockMvcRequestBuilders.get("/actionA"))
    }
}