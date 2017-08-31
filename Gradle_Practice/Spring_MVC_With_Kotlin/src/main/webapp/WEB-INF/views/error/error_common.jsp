<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./../include/adminLTEHeader.jsp"%>
<!DOCTYPE html>
<head>
    <title>Error - Common</title>
</head>
<body>
    <h4>${exception.getMessage()}</h4>
    <ul>
        <c:forEach items="${exception.getStackTrace()}" var="stack">
            <li>${stack.toString()}</li>
        </c:forEach>
    </ul>
</body>
</html>
<%@ include file="./../include/adminLTEFooter.jsp"%>