package mvc.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView

@ControllerAdvice
class CommonExceptionAdvice {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(Exception::class)
    fun commonError(e:Exception): ModelAndView {
        logger.info(e.toString())
        var modelAndView:ModelAndView = ModelAndView()
        modelAndView.viewName = "/error/error_common"
        modelAndView.addObject("exception",e)
        return modelAndView
    }
}