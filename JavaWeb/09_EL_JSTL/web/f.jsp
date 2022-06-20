<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("a.a.a", "aaaValue");
    map.put("bbb", "bbbValue");
    map.put("+c.c-c", "cccValue");

    request.setAttribute("map", map);
%>

${map['a.a.a']}
${map.bbb}
${map["+c.c-c"]}
</body>
</html>
