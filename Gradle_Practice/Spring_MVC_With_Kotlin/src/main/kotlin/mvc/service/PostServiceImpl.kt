package mvc.service

import mvc.domain.Criteria
import mvc.domain.Post
import mvc.persistence.PostDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class PostServiceImpl:PostService {

    @Autowired lateinit var postDAO:PostDAO

    override fun register(post: Post) {
        postDAO.insertPost(post)
    }

    override fun read(postNo: Int): Post {
        return postDAO.selectPost(postNo)
    }

    override fun modify(post: Post) {
        postDAO.updatePost(post)
    }

    override fun remove(postNo: Int) {
        postDAO.deletePost(postNo)
    }

    override fun listAllPosts(): List<Post> {
        return postDAO.selectAllPosts()
    }

    override fun listPagedPosts(pageNo: Int): List<Post> {
        var pageNoCal = 1
        if(pageNo > 1) pageNoCal = (pageNo - 1) * 10
        return postDAO.selectPagedPosts(pageNoCal)
    }

    override fun listCriteria(criteria: Criteria): List<Post> {
        return postDAO.selectCriteria(criteria)
    }

    override fun listCountCriteria(criteria: Criteria): Int {
        return postDAO.countPaging(criteria)
    }

}