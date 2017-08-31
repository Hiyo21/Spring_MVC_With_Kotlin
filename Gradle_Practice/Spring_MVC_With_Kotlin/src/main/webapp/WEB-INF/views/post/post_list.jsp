<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./../include/adminLTEHeader.jsp"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <script src="/resources/node_modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <body>
        <table class="table table-bordered">
            <tr>
                <th style="width: 10px">Post NO</th>
                <th>Title</th>
                <th>Poster</th>
                <th>post_date</th>
                <th>modify_date</th>
                <th style="width: 40px">views</th>
            </tr>
        <c:forEach items="${postList}" var="post">
            <tr>
                <td>${post.postNo}</td>
                <td><a href="./readPage${pageMaker.makeQuery(pageMaker.criteria.page)}&post_no=${post.postNo}">${post.postTitle}</a></td>
                <td>${post.poster}</td>
                <td>${post.postDate}</td>
                <td>${post.modifyDate}</td>
                <td><span class="badge bg-red">${post.viewCount}</span></td>
            </tr>
        </c:forEach>
        </table>

        <div class="text-center">
            <ul class="pagination">
                <c:if test="${pageMaker.prevExists}">
                    <li><a href="/post/post_list${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
                </c:if>

                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                    <li <c:out value="${pageMaker.criteria.page == idx ? 'class = active': ''}"/>>
                        <a href="/post/post_list${pageMaker.makeQuery(idx)}">${idx}</a>
                    </li>
                </c:forEach>

                <c:if test="${pageMaker.nextExists && pageMaker.endPage > 0}">
                    <li><a href="/post/post_list${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
                </c:if>
            </ul>
        </div>

        <button id="toWritePost" class="btn btn-primary">Write</button>
        <script>
        $(function(){
            $("#toWritePost").click(function(e){
                e.preventDefault();
                $.ajax({
                    url:"/post/register",
                    type:"GET",
                    success : function(data) {
                        window.location = "/post/register"
                    }
                });
            });
        });
    </script>
    </body>
</html>
<%@ include file="./../include/adminLTEFooter.jsp"%>