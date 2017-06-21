package learningtest.web.fundamental.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by shoon on 2017/06/19.
 */
@Controller
public class HelloWorldController {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name, ModelMap map){
        map.put("message", "Hello! " + name);
        return "/learningtest_first.jsp";
    }

    @RequestMapping("what")
    public void what(){
        logger.info("whaT?");
    }
}
