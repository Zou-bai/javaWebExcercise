<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //1、值为null的时候，为空
    request.setAttribute("emptyNull", null);
    //2、值为空串的时候，为空
    request.setAttribute("emptyStr", "");
    //3、值是Object类型的数组，长度为零
    request.setAttribute("emptyArr", new Object[]{});
    //4、list集合，元素个数为零
    request.setAttribute("emptyList", new ArrayList<>());
    //5、map集合，元素个数为零
    request.setAttribute("emptyMap", new HashMap<>());
%>${empty emptyNull}<br>
${empty emptyStr}<br>
${empty emptyArr}<br>
${empty emptyList}<br>
${empty emptyMap}<br>

${12==12 ? "zou_bai" : "QWQ"}
</body>
</html>
