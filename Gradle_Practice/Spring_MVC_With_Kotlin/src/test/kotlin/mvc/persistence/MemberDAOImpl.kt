package mvc.persistence

import mvc.domain.Member
import org.apache.ibatis.session.SqlSession
import org.springframework.stereotype.Repository
import javax.annotation.Resource

@Repository
class MemberDAOImpl:MemberDAO {
    @Resource lateinit var sqlSession:SqlSession

    fun withNamespace (mappedQuery:String):String  {
        return "mvc.mapper.MemberMapper." + mappedQuery
    }

    override fun getTime(): String {
        return sqlSession.selectOne(withNamespace("getTime"))
    }

    override fun insertMember(member: Member): Int {
        return sqlSession.insert(withNamespace("insertMember"),member)
    }

    override fun deleteMember(userid: String): Int {
       return sqlSession.delete(withNamespace("deleteMember"),userid)
    }

    override fun deleteAll(): Int {
        return sqlSession.delete(withNamespace("deleteAll"))
    }

    override fun updateMember(member: Member): Int {
        return sqlSession.update(withNamespace("updateMember"), member)
    }

    override fun selectMember(userid: String): Member {
        return sqlSession.selectOne(withNamespace("selectMember"), userid)
    }

    override fun selectAllMembers(): List<Member> {
        return sqlSession.selectList(withNamespace("selectAllMembers"))
    }
}