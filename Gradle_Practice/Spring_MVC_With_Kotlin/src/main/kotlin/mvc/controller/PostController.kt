package mvc.controller

import mvc.domain.Criteria
import mvc.domain.PageMaker
import mvc.domain.Post
import mvc.service.PostService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/post/*")
class PostController {
    val logger: Logger = LoggerFactory.getLogger(PostController::class.java)

    @Autowired lateinit var postService:PostService

    @RequestMapping("/register", method = arrayOf(RequestMethod.GET))
    fun registerGET(post: Post, model:Model){
        logger.info("register get is called...")
    }

    @RequestMapping("/register", method= arrayOf(RequestMethod.POST))
    fun registerPOST(post:Post, redirectAttributes: RedirectAttributes):String{
        logger.info("register post is called....")
        logger.info(post.toString())

        postService.register(post)
        redirectAttributes.addFlashAttribute("msg", "success")
        return "redirect:/post/post_list_criteria"
    }

    @RequestMapping("/register_result_success", method = arrayOf(RequestMethod.GET))
    fun registerResultSuccess (model:Model){
        logger.info("registration is successful!!!")
    }

/*    @RequestMapping("/post_list", method=arrayOf(RequestMethod.GET))
    fun showPostList(@RequestParam("page_no") pageNo:Int, model:Model){
        logger.info("show post list: page - " + pageNo)
        model.addAttribute("postList", postService.listPagedPosts(pageNo))
    }*/

    @RequestMapping("/post_list", method=arrayOf(RequestMethod.GET))
    fun showPostList(@ModelAttribute("criteria") criteria: Criteria, model:Model){
        logger.info("show post list: page - ")
        model.addAttribute("postList", postService.listCriteria(criteria))
        var pageMaker = PageMaker()
        pageMaker.criteria = criteria
        pageMaker.totalCount = postService.listCountCriteria(criteria)
        model.addAttribute("pageMaker", pageMaker)
    }

    @RequestMapping("/read", method=arrayOf(RequestMethod.GET))
    fun readPost(@RequestParam("post_no") postNo:Int, model:Model){
        model.addAttribute(postService.read(postNo))
    }

    @RequestMapping("/modify", method=arrayOf(RequestMethod.GET))
    fun modifyGET(@RequestParam("post_no") postNo: Int, @ModelAttribute("criteria") criteria: Criteria, model:Model){
        model.addAttribute(postService.read(postNo))
    }

    @RequestMapping("/modify", method=arrayOf(RequestMethod.POST))
    fun modifyPOST(post: Post, criteria: Criteria, redirectAttributes: RedirectAttributes): String{
        postService.modify(post)
        redirectAttributes.addFlashAttribute("page", criteria.page)
        redirectAttributes.addFlashAttribute("perPageNum", criteria.perPageNum)
        redirectAttributes.addFlashAttribute("msg", "post is modified")
        return "redirect:/post/post_list"
    }

    @RequestMapping("/delete", method=arrayOf(RequestMethod.POST))
    fun delete(@RequestParam("post_no")postNo: Int, @ModelAttribute("criteria") criteria: Criteria, redirectAttributes: RedirectAttributes):String {
        postService.remove(postNo)
        redirectAttributes.addFlashAttribute("criteria", criteria)
        redirectAttributes.addFlashAttribute("msg", "post is deleted")
        return "redirect:/post/post_list"
    }

    @RequestMapping("/readPage", method= arrayOf(RequestMethod.GET))
    fun read(@RequestParam("post_no") postNo: Int, @ModelAttribute("criteria") criteria: Criteria, model:Model){
        model.addAttribute(postService.read(postNo))
    }


}