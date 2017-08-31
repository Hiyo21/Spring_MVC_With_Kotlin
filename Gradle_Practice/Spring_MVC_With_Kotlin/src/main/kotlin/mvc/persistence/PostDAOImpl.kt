package mvc.persistence

import mvc.domain.Criteria
import mvc.domain.Post
import org.apache.ibatis.session.SqlSession
import org.springframework.stereotype.Repository
import javax.annotation.Resource

@Repository
class PostDAOImpl:PostDAO {
    @Resource lateinit var sqlSession: SqlSession

    fun withNamespace (mappedQuery:String):String  {
        return "mvc.mapper.PostMapper." + mappedQuery
    }

    override fun insertPost(post: Post): Int {
        return sqlSession.insert(withNamespace("insertPost"), post)
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

    override fun selectPost(postNo: Int): Post {
        return sqlSession.selectOne<Post>(withNamespace("selectPost"), postNo)
    }

    override fun selectAllPosts(): List<Post> {
        return sqlSession.selectList<Post>(withNamespace("selectAllPosts"))
    }

    override fun selectPagedPosts(pageNumber: Int): List<Post> {
        return sqlSession.selectList<Post>(withNamespace("selectPagedPosts"), pageNumber)
    }

    override fun selectCriteria(criteria: Criteria):List<Post> {
        return sqlSession.selectList<Post>(withNamespace("selectCriteria"), criteria)
    }

    override fun countPaging(criteria: Criteria): Int {
        return sqlSession.selectOne(withNamespace("selectCountPaging"), criteria)
    }
}