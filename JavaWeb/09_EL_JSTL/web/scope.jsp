<%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    pageContext.setAttribute("key1","pageContext1");
    pageContext.setAttribute("key2","pageContext2");
    session.setAttribute("key2","session");
    application.setAttribute("key2","application");
    request.setAttribute("key2","request");

%>
${pageScope.key2}
${key2}
${sessionScope.key2}
</body>
</html>
