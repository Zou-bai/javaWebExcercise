<%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${pageContext.servletContext}
<%--
request.getScheme()它可以获取请求的协议
request.getServerName()他可以获取请求的服务器ip或者域名
request.getServerPort()获取请求的服务器端口号
request.getContextPath()获取当前工程路径
request.getMethod()获取请求的参数（get或者host）
${pageContext.request.contextPath}<br>获取客户端的ip地址
session.getId()获取会话的id
--%>
<%=session.getId()%>
1. 协议：${pageContext.request.scheme}<br>
2. 服务器 ip：${pageContext.request.serverName}<br>
3. 服务器端口：${pageContext.request.serverPort}<br>
4. 获取工程路径：${pageContext.request.contextPath}<br>
5. 获取请求方法：${pageContext.request.method}<br>
6. 获取客户端 ip 地址：${pageContext.request.remoteHost}<br>
7. 获取会话的 id 编号：${pageContext.session.id}<br>
</body>
</html>
