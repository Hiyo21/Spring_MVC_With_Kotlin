package mvc.service

import mvc.domain.Criteria
import mvc.domain.Post

interface PostService {
   fun register(post:Post)
   fun read(postNo:Int):Post
   fun modify(post:Post)
   fun remove(postNo:Int)
   fun listAllPosts():List<Post>
   fun listPagedPosts(pageNo:Int):List<Post>
   fun listCriteria(criteria: Criteria):List<Post>
   fun listCountCriteria(criteria: Criteria):Int
}