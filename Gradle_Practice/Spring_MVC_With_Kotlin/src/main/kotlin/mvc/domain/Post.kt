package mvc.domain

import java.time.LocalDateTime

data class Post(val postNo:Int = 0, private val postTitle:String = ""
                , val content:String = "", val poster:String = ""
                , val postDate: LocalDateTime = LocalDateTime.now()
                , val modifyDate:LocalDateTime = LocalDateTime.now()
                , val viewCount:Int = 0)




