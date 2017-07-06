package mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("actionE")
    public String actionE(){
        logger.info("action E is Called!!!!");
        return "actionEResult";
    }
}
