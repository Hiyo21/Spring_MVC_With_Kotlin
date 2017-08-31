/*
package mvc.persistence

import mvc.domain.Post
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

//@RunWith(SpringJUnit4ClassRunner::class)
//@ContextConfiguration(locations = arrayOf("/applicationContext/test-applicationContext.xml"))
//@Transactional("transactionManager")
open class PostDAOTest {
//    @Autowired lateinit var postDAO: PostDAO

/*    @Test
    fun testInsertPost(){
        val result = postDAO.insertPost(Post(1,"post03", "asdsdfsdf","user03@test.com"))
        Assert.assertEquals(result, 1)
    }

    @Test
    fun testDeletePost() {
        val post4 = Post(1, "post04","adfgadfg",  "user04@test.com")

        val result = postDAO.insertPost(post4)
        Assert.assertEquals(result, 1)
        val result2 = postDAO.deletePost(1)
        Assert.assertEquals(result2, 1)
        Assert.assertEquals(postDAO.selectAllPosts().size, 0)
    }

    @Test
    fun testDeleteAll() {
        val post1 = Post(1, "post01","adfgadfg",  "user01@test.com")
        val post2 = Post(2, "post02","adfgadfg",  "user02@test.com")
        val post3 = Post(3, "post03","adfgadfg",  "user03@test.com")

        postDAO.insertPost(post1)
        postDAO.insertPost(post2)
        postDAO.insertPost(post3)

        val result = postDAO.deleteAllPosts()
        Assert.assertEquals(result,3)
        val res = postDAO.selectAllPosts()
        Assert.assertEquals(res.size, 0)
    }

    @Test
    fun testUpdatePost() {
        val post1 = Post(1, "post01","adfgadfg",  "user01@test.com")
        postDAO.insertPost(post1)
        val postSelected = postDAO.selectPost(1)
        val updatedPost = Post(1,"post02-02", "content updated", "user02_02@test.com",modifyDate = LocalDateTime.now())
        postDAO.updatePost(updatedPost)
        val membUpdated = postDAO.selectPost(1)
        Assert.assertNotEquals(postSelected, membUpdated)
        Assert.assertEquals(updatedPost.postTitle, membUpdated.postTitle)
    }

    @Test
    fun testSelectPost() {
        val post5 = Post(1,"post05","hhh", "user05@test.com")
        val result = postDAO.insertPost(post5)
        Assert.assertEquals(result, 1)
        val post5Selected = postDAO.selectPost(1)
        Assert.assertEquals(post5.postTitle, post5Selected.postTitle)
        Assert.assertEquals(post5.poster, post5Selected.poster)
    }

    @Test
    fun testSelectAllPosts() {
        val post1 = Post(1, "post01","adfgadfg",  "user01@test.com")
        val post2 = Post(2, "post02","adfgadfg",  "user02@test.com")
        val post3 = Post(3, "post03","adfgadfg",  "user03@test.com")

        postDAO.insertPost(post1)
        postDAO.insertPost(post2)
        postDAO.insertPost(post3)

        val posts = postDAO.selectAllPosts()
        Assert.assertEquals(posts.size, 3)
        Assert.assertNotEquals(posts[0], posts[1])
        Assert.assertNotEquals(posts[1], posts[2])
    }
*/
}
*/