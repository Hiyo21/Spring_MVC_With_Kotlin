<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./../include/adminLTEHeader.jsp"%>
<!DOCTYPE html>
<head>
    <title>Modifying Post # ${post.postNo}</title>
</head>
<script src="/resources/node_modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script>
    $(document).ready(function (){
        var formObj = $("form[role='form']");
        console.log(formObj);

        $(".btn-primary").on("click", function () {
            formObj.submit();
        });

        $(".btn-warning").on("click", function(){
            self.location = "/post/post_list?page=${criteria.page}&perPageNum=${criteria.perPageNum}";
        });
    })
</script>
<body>
<form role="form" action="/post/modify" method="post">
    <input type="hidden" id="page" value="${criteria.page}">
    <input type="hidden" id="perPageNum" value="${criteria.perPageNum}">

    <div class="box-body">
        <div class="form-group"><label for="postTitle">Title</label><input type="text" id="postTitle" name="postTitle" class="form-control" value="${post.postTitle}"></div>
        <div class="form-group"><label for="content">Content</label><textarea name="content" id="content" rows="3" class="form-control" >${post.content}</textarea></div>
        <div class="form-group"><label for="poster">Poster</label><input type="text" id="poster" name="poster" class="form-control" value="${post.poster}" readonly></div>
        <input type="hidden" id="postNo" name="postNo" value="${post.postNo}">
    </div>
</form>
    <div class="box-footer">
        <button class="btn btn-primary" type="submit">Save</button>
        <button class="btn btn-warning" type="submit">Cancel</button>
    </div>
</body>
</html>
<%@ include file="./../include/adminLTEFooter.jsp"%>
