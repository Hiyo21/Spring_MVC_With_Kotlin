<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./../include/adminLTEHeader.jsp"%>
<!DOCTYPE html>
<head>
    <title>Post # ${post.postNo}</title>
</head>
<script src="/resources/node_modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script>
    $(document).ready(function (){
        var formObj = $("form[role='form']");
        console.log(formObj);

        $(".btn-warning").on("click", function () {
            formObj.attr("action", "/post/modify");
            formObj.attr("method", "get");
            formObj.submit();
        });

        $(".btn-danger").on("click", function(){
            formObj.attr("method", "post");
            formObj.attr("action", "/post/delete");
            formObj.submit();
        });

        $(".btn-primary").on("click", function(){
            formObj.attr("method","get");
            formObj.attr("action", "/post/post_list");
            formObj.submit();
        });

    });
</script>
<body>
    <form role="form" method="post">
        <input type="hidden" name="post_no" value="${post.postNo}">
        <input type="hidden" name="page" value="${criteria.page}">
        <input type="hidden" name="perPageNum" value="${criteria.perPageNum}">
    </form>

    <div class="box-body">
        <div class="form-group"><label for="postTitle">Title</label><input type="text" id="postTitle" name="postTitle" class="form-control" value="${post.postTitle}" readonly></div>
        <div class="form-group"><label for="content">Content</label><textarea name="content" id="content" rows="3" class="form-control" readonly>${post.content}</textarea></div>
        <div class="form-group"><label for="poster">Poster</label><input type="text" id="poster" name="poster" class="form-control" value="${post.poster}" readonly></div>
    </div>
    <div class="box-footer">
        <button class="btn btn-warning" type="submit">Modify</button>
        <button class="btn btn-danger" type="submit">Remove</button>
        <button class="btn btn-primary" type="submit">List All</button>
    </div>
</body>

</html>

<%@ include file="./../include/adminLTEFooter.jsp"%>
