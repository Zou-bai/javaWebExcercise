<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<!--<style>
    body{
        font-family:Arial,sans-serif;
    }
</style>-->
<head>
    <meta charset="UTF-8">
    <title>Document</title>
<%--    <base href="">--%><%--配置工程路径--%>
    <link rel="stylesheet" href="../static/css/style.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">

    <script type="text/javascript" src="../static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                alert("登录");
            })
            $("#forgot-password").click(function () {
                alert("忘记密码？");
            })
        })
    </script>
</head>
<body>
<form action="#" method="POST">
    <div id="login-box">
        <h2>登录!</h2>
        <div class="form">
            <div class="item">
                <i class="fa fa-user-circle"></i>
                <input type="text" placeholder="用户名" name="username" id="username">
            </div>

            <div class="item">
                <i class="fa fa-key"></i>
                <input type="password" placeholder="密码" name="password" id="password">
            </div>
        </div>
        <div class="btn-box">
            <label class="controller">
                <input type="checkbox" id="remember_psw"/>
                <span class="caption">记住密码</span>
            </label>
            <a href="#" class="forgot-pass" id="forgot-password">忘记密码?</a></span>
        </div>
        <div class="btn-sub">
            <button type="submit" id="sub_btn">登 录</button>
        </div>
    </div>
</form>
</body>

</html>