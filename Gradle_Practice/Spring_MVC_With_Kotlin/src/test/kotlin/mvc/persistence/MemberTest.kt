package mvc.persistence

import mvc.domain.Member
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(locations = arrayOf("/applicationContext/test-applicationContext.xml"))
@Transactional
open class MemberTest {
    @Autowired lateinit var memberDAO:MemberDAO
    lateinit var member0:Member
    lateinit var member1:Member
    lateinit var member2:Member


    @Before
    fun setupMembers() {
        member0 = Member("user00", "user00", "user_00", "user00@test.com")
        member1 = Member("user01", "user01", "user_01", "user01@test.com")
        member2 = Member("user02", "user02", "user_02", "user02@test.com")
        val listOfMembers:List<Member> = listOf(member0, member1, member2)
        for(i in listOfMembers){
            memberDAO.insertMember(i)
        }
    }

    @Test
    fun testGetTime(){
        println(memberDAO.getTime())
    }

    @Test
    fun testInsertMember(){
        val result = memberDAO.insertMember(Member("user03", "user03","user_03","user03@test.com"))
        Assert.assertEquals(result, 1)
    }

    @Test
    fun testDeleteMember() {
        val member4 = Member("user04","user04","user_04","user04@test.com")

        val result = memberDAO.insertMember(member4)
        Assert.assertEquals(result, 1)
        val result2 = memberDAO.deleteMember(member4.userid)
        Assert.assertEquals(result2, 1)
        Assert.assertEquals(memberDAO.selectAllMembers().size, 3)
    }

    @Test
    fun testDeleteAll() {
        val result = memberDAO.deleteAll()
        Assert.assertEquals(result,3)
        val res = memberDAO.selectAllMembers()
        Assert.assertEquals(res.size, 0)
    }

    @Test
    fun testUpdateMember() {
        val membSelected = memberDAO.selectMember("user02")
        membSelected.username="user02-02"
        membSelected.email="user02_02@test.com"
        membSelected.updateDate = LocalDateTime.now()
        memberDAO.updateMember(membSelected)
        val membUpdated = memberDAO.selectMember("user02")
        Assert.assertNotEquals(membSelected, membUpdated)
    }

    @Test
    fun testSelectMember() {
        val member5 = Member("user05","user05","user_05","user05@test.com")
        val result = memberDAO.insertMember(member5)
        Assert.assertEquals(result, 1)
        val member5Selected = memberDAO.selectMember(member5.userid)
        Assert.assertEquals(member5.userid, member5Selected.userid)
        Assert.assertEquals(member5.username, member5Selected.username)
    }

    @Test
    fun testSelectAllMembers() {
       val members = memberDAO.selectAllMembers()
        Assert.assertEquals(members.size, 3)
        Assert.assertNotEquals(members[0], members[1])
        Assert.assertNotEquals(members[1], members[2])
    }
}