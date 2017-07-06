package mvc.controller

import mvc.domain.ProductVO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

// @Controller: SpringのControllerオブジェクトであることを示す
@Controller
class SampleController {
    // loggingをインスタンス化。LoggerFactory.getLoggerで生成　（newじゃない！）
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    // @RequestMapping : 特定URIとのマッピング情報を記録
    @RequestMapping("actionA")
    fun actionA(){
        logger.info("actionA is called")
    }

    @RequestMapping("actionB")
    fun actionB(){
        logger.info ("actionB is Called")
    }

    // if return type is string, then Spring Framework looks for return value + .jsp
    // in this case, Spring looks for /WEB-INF/view/actionCResult.jsp
    // but URL is still localhost:xxxx/actionC!
    @RequestMapping("actionC")
    fun actionC(@ModelAttribute("msg") msg:String):String {
        logger.info("action C is called")
        return "actionCResult"
    }

    @RequestMapping("actionD")
    fun actionD(model: Model):String{
        model.addAttribute(ProductVO(name = "Sample Product", price = 1300.toDouble()))
        logger.info("actionD is called!!")
        return "productDetail"
    }

    // FlashAttributes - > special scope (allows messages/ data to transfer while redirecting to another page)
    @RequestMapping("actionF")
    fun actionF(ra: RedirectAttributes):String {
        logger.info("actionF is called!")
        ra.addFlashAttribute("msg", "redirected to another class.")
        return "redirect:actionG"
    }

    @RequestMapping("actionG")
    fun actionG(msg:String){
        logger.info("actionG is called with $msg")
    }
}