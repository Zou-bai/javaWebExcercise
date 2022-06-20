<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
i.<c:set />
作用：set标签可以往域里保存数据
域对象.setAttribute(key,value);
scope属性设置保存到哪个域 page表示pagecontext
var属性设置key是多少
value属性设置value是多少
--%>
保存之前${requestScope.abc}<br>
<c:set scope="request" var="abc" value="abcValue"/>
保存之后${requestScope.abc}<br>

<%--
ii:<c:if>
if标签用来做if判断
test属性，表示判断的条件（使用EL表达式输出）
--%>
<c:if test="${12==12}"><h1>十二等于十二</h1></c:if>
<%--
iii.<c:choose> <c:when> <c:oterwise>标签
作用：多路判断。跟switch。。。 case。。。 default 非常接近

choose标签开始选择判断
when标签表示每一种判断情况
test属性表示当前这种判断情况的值
--%>
<%
    request.setAttribute("height",178);
%>
<c:choose>
<%--
1在choose里面，注释要使用jsp注释
2when标签的父标签一定是choose标签
--%>
    <c:when test="${requestScope.height>190}"><h2>巨人</h2></c:when>
    <c:when test="${requestScope.height>180}"><h2>很高</h2></c:when>
    <c:when test="${requestScope.height>170}"><h2>还行</h2></c:when>
    <c:otherwise><h2>剩下的小于170的情况 </h2></c:otherwise>
</c:choose>
</body>
</html>
