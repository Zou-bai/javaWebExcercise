<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--1.遍历 1 到 10，输出
begin 属性设置开始的索引
end 属性设置结束的索引
var 属性表示循环的变量(也是当前正在遍历到的数据)
for (int i = 1; i < 10; i++) --%>
<c:forEach begin="1" end="10" var="i">
    <tr>
         <td>${i}</td>
    </tr>
</c:forEach>
<%--遍历Object数组
item表示遍历的数据源（遍历的集合）
--%>
<%
    request.setAttribute("array",new String[]{"18610541354","18688886666","18699998888"});
%>
<c:forEach items="${requestScope.array}" var="it">${it}</c:forEach>
<% Map<String,Object>
        map = new HashMap<String, Object>();
map.put("key1", "value1");
map.put("key2", "value2");
map.put("key3", "value3");
// for ( Map.Entry<String,Object> entry : map.entrySet()) {
// } request.setAttribute("map", map);
%>
<c:forEach items="${ requestScope.map }" var="entry">
    <h1>${entry.key} = ${entry.value}</h1>
</c:forEach>
<form action="" enctype="multipart/form-data" ></form>
</body>
</html>
