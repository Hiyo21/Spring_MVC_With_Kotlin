package learningtest.web.fundamental.test;

import learningtest.web.fundamental.controller.HelloWorldController;
import learningtest.web.fundamental.model.HelloWorld;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by shoon on 2017-06-21.
 */
public class MockServletTest extends AbstractDispatcherServletTest{

    @Test
    public void helloControllerTest(){
        ConfigurableDispatcherServlet servlet = new ConfigurableDispatcherServlet();
        System.out.println(getClass().getResource("/WEB-INF/dispatcher-servlet.xml").getPath());
        servlet.setLocations("/WEB-INF/dispatcher-servlet.xml");

        servlet.setClasses(HelloWorld.class);
        try {
            servlet.init(new MockServletConfig("dispatcher"));
            MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
            System.out.println(req.getSession().getServletContext().getRealPath("/"));
            req.addParameter("id", "4");
            MockHttpServletResponse res = new MockHttpServletResponse();
            servlet.service(req, res);

            ModelAndView mav = servlet.getModelAndView();
            Assert.assertEquals(mav.getViewName(), "/learningtest/learningtest_first.jsp");
            Assert.assertEquals(mav.getModel().get("HelloMessage"), "Hello! 4");

        } catch (ServletException e)  {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void helloControllerTestWithHelper() throws ServletException, IOException{
        ModelAndView mav = setLocations("/WEB-INF/dispatcher-servlet.xml").setClasses(HelloWorld.class).initRequest("/hello", RequestMethod.GET).addParameter("id","4").runService().getModelAndView();
        Assert.assertEquals(mav.getViewName(),"/learningtest/learningtest_first.jsp");
        Assert.assertEquals(mav.getModel().get("HelloMessage"), "Hello! 4");
    }

}
