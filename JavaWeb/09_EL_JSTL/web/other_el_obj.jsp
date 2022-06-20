<%--
  Created by IntelliJ IDEA.
  User: sdjkf
  Date: 2022/2/12
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
输出请求参数username的值${param.username}<br>
输出请求参数password的值${param.password}<br>
${param.hobby}
输出请求参数username的值${paramValues.username[0]}
输出请求参数hobbies的值${paramValues.hobby[0]}
输出请求参数hobbies的值${paramValues.hobby[1]}
<hr>
输出请求头user-Agent的值${header['user-Agent']}
输出请求头connection的值${header['connection']}
输出请求头user-Agent的值${headerValues['user-Agent'][0]}
<hr>
获取cookie的名称：${cookie.JESSIONID.name}<br>
获取cookie的值：${cookie.JESSIONID.value}<br>
<hr>
输出&lt;Context-Param&gt;username的值：${initParam.username}<br>
输出&lt;Context-Param&gt;url：${initParam.url}<br>
</body>
</html>
