package mvc.domain

import java.time.Clock
import java.time.LocalDateTime

data class Member (var userid:String = "",
                   var userpw:String = "",
                   var username:String = "",
                   var email:String = "",
                   var regDate:LocalDateTime = LocalDateTime.now(Clock.systemDefaultZone()),
                   var updateDate:LocalDateTime = LocalDateTime.now(Clock.systemDefaultZone())){

}