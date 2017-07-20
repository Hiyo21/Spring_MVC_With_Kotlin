package mvc.persistence

import mvc.domain.Post

interface PostDAO {
    fun getTime():String
    fun insertPost(post: Post):Int
    fun deletePost(postNo:Int):Int
    fun deleteAllPosts():Int
    fun updatePost(post:Post):Int
    fun selectPost(postNo: String): Post
    fun selectAllPosts():List<Post>
}