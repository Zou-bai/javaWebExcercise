<%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setAttribute("key","request");
    session.setAttribute("key","session");
    application.setAttribute("key","application");
    pageContext.setAttribute("key","pageContext");
%>
${key}
</body>
</html>
