package mvc.persistence

import mvc.domain.Member

interface MemberDAO {
    fun getTime():String
    fun insertMember(member:Member):Int
    fun deleteMember(userid:String):Int
    fun deleteAll():Int
    fun updateMember(member:Member):Int
    fun selectMember(userid: String):Member
    fun selectAllMembers():List<Member>
}