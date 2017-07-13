package mvc.persistence

import mvc.domain.Member
import org.apache.ibatis.session.SqlSession
import org.springframework.stereotype.Repository
import javax.annotation.Resource

@Repository
class MemberDAOImpl:MemberDAO {

    fun withNamespace (mappedQuery:String):String  {
        return "mvc.mapper.MemberMapper." + mappedQuery
    }

    override fun getTime(): String {
        return Companion.sqlSession.selectOne("mvc.mapper.MemberMapper.getTime")
    }

    override fun insertMember(member: Member): Int {
        return Companion.sqlSession.insert("insertMember",member)
    }

    override fun deleteMember(userid: String): Int {
       return Companion.sqlSession.delete(withNamespace("deleteMember"),userid)
    }

    override fun deleteAll(): Int {
        return Companion.sqlSession.delete(withNamespace("deleteAll"))
    }

    override fun updateMember(member: Member): Int {
        return Companion.sqlSession.update(withNamespace("updateMember"), member)
    }

    override fun selectMember(userid: String): Member {
        return Companion.sqlSession.selectOne(withNamespace("selectMember"), userid)
    }

    override fun selectAllMembers(): List<Member> {
        return Companion.sqlSession.selectList(withNamespace("selectAllMembers"))
    }

    companion object {
        @Resource lateinit var sqlSession:SqlSession
    }
}