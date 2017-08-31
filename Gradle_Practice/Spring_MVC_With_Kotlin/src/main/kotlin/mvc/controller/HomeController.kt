package mvc.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Clock
import java.time.LocalDate

@Controller
class HomeController {
    private var logger: Logger = LoggerFactory.getLogger(HomeController::class.java)

    @RequestMapping("/")
    fun redirectToHome(model: Model):String {
        logger.info("redirect to home")
        model.addAttribute("current_time", LocalDate.now(Clock.systemDefaultZone()).toString())
        return "Home"
    }
}