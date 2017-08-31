package mvc.persistence

import mvc.domain.Criteria
import mvc.domain.Post

interface PostDAO {
    fun insertPost(post: Post):Int
    fun deletePost(postNo:Int):Int
    fun deleteAllPosts():Int
    fun updatePost(post:Post):Int
    fun selectPost(postNo: Int): Post
    fun selectAllPosts():List<Post>
    fun selectPagedPosts(pageNumber:Int):List<Post>
    fun selectCriteria(criteria: Criteria):List<Post>
    fun countPaging(criteria: Criteria):Int
}