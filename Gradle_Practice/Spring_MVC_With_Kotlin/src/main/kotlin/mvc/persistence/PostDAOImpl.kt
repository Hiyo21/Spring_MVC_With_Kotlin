package mvc.persistence

import mvc.domain.Post
import org.apache.ibatis.session.SqlSession
import javax.annotation.Resource

class PostDAOImpl:PostDAO {
    @Resource lateinit var sqlSession: SqlSession

    fun withNamespace (mappedQuery:String):String  {
        return "mvc.mapper.PostMapper." + mappedQuery
    }

    override fun insertPost(post: Post): Int {
        return sqlSession.insert("insertPost", post)
    }

    override fun deletePost(postNo: Int): Int {
        return sqlSession.delete(withNamespace("deletePost"),postNo)
    }

    override fun deleteAllPosts(): Int {
        return sqlSession.delete(withNamespace("deleteAllPosts"))
    }

    override fun updatePost(post: Post): Int {
        return sqlSession.update(withNamespace("updatePost"), post)
    }

    override fun selectPost(postNo: String): Post {
        return sqlSession.selectOne(withNamespace("selectPost"), postNo)
    }

    override fun selectAllPosts(): List<Post> {
        return sqlSession.selectList(withNamespace("selectAllPosts"))
    }

    override fun getTime(): String {
        return sqlSession.selectOne("mvc.mapper.PostMapper.getTime")
    }
}