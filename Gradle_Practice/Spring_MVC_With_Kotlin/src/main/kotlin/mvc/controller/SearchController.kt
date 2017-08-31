package mvc.controller

import mvc.domain.Criteria
import mvc.domain.PageMaker
import mvc.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/search/*")
class SearchController {
    var logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var postService: PostService

    @RequestMapping("list", method= arrayOf(RequestMethod.GET))
    fun listPage(@ModelAttribute searchCriteria: Criteria, model: Model){
        logger.info(searchCriteria.toString())

        model.addAttribute("list", postService.listCriteria(criteria = searchCriteria))

        var pageMaker:PageMaker = PageMaker()
        pageMaker.criteria = searchCriteria

        pageMaker.totalCount = postService.listCountCriteria(searchCriteria)

        model.addAttribute("pageMaker", pageMaker)
    }
}