<%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    request.setAttribute("key","值");
%>
表达式脚本输出key的值是：<%=request.getAttribute("key")%><br>
EL表达式输出key的值是:${key}
</body>
</html>
