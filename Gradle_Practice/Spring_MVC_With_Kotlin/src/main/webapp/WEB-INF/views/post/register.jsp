<%--
  Created by IntelliJ IDEA.
  User: shoon
  Date: 2017-07-27
  Time: 오후 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ include file="./../include/adminLTEHeader.jsp"%>
<!DOCTYPE html>
<section class="content">
    <form action="/post/register" method="post">
        <ul>
            <li><label>postTitle</label><input name="postTitle" title="title"> </li>
            <li><label>content</label><input name="content" title="content" size = "100"></li>
            <li><label>poster</label><input name="poster" title="poster"></li>
        </ul>
        <input type="submit" value="Submit">
        <button type="reset" onclick="backToPostList();">Back</button>
    </form>
</section>
</div>
</div>
<script>
    function backToPostList(){
        window.location = '/post/post_list';
    }
</script>
<%@ include file="../include/adminLTEFooter.jsp"%>
