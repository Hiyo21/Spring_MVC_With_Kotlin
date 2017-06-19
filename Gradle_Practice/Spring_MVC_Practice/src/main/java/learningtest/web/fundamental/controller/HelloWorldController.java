package learningtest.web.fundamental.controller;

import learningtest.web.fundamental.model.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shoon on 2017/06/19.
 */
public class HelloWorldController implements Controller {
    @Autowired HelloWorld helloWorld;

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String str = httpServletRequest.getParameter("id");
        String sayHelloResult = helloWorld.sayHello(str);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("HelloMessage", sayHelloResult);

        return new ModelAndView("/learningtest_first.jsp", resultMap);
    }
}
