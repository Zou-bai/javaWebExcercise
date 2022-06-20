<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>

    <script type="text/javascript">


        //页面加载完成之后
        $(function () {

            $("#username").blur(function () {
                //1、获取用户名
                var username = this.value;

                $.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistsUsername&username="+username,function (data) {
                    if (data.existsUsername){
                        $("span.errorMsg").text("用户名已存在");
                    }else {
                        $("span.errorMsg").text("用户名可用");
                    }
                });
            })

            //给验证码图片绑定单击事件
            $("#code_img").click(function () {
                //在事件响应的fuction中有一个this对象，是当前相应事件的dom对象
                //src属性表示验证码图片img标签的图片路径，可读可写
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            });
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                //1。获取用户名输入框的内容
                //2.创建正则表达式对象
                //3.使用test方法验证
                //4.提示用户结果
                var usernameText = $("#username").val();
                var usernamePatt = /^\w{5,12}$/
                if (!usernamePatt.test(usernameText)) {
                    $("span.errorMsg").text("用户名不合法");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }
                // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                var passwordText = $("#password").val();
                var passwordPatt = /^\w{5,12}$/
                if (!passwordPatt.test(passwordText)) {
                    $("span.errorMsg").text("密码不合法");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }
                // 验证确认密码：和密码相同
                var repwdText = $("#repwd").val();
                if (repwdText != passwordText) {
                    $("span.errorMsg").text("确认密码和密码不一致");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }
                // 邮箱验证：xxxxx@xxx.com
                //1.获取邮箱里的内容
                //2.创建正则表达式对象
                //3.使用test方法验证是否合法
                var emailtext = $("#email").val();
                var emailPatt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
                if (!emailPatt.test(emailtext)) {
                    $("span.errorMsg").text("邮箱不合法");
                } else {
                    $("span.errorMsg").text("");
                }
                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成
                var codetext = $("#code").val();
                codetext = $.trim(codetext);
                if (codetext == "" || codetext == null) {
                    $("span.errorMsg").text("验证码不能为空");
                } else {
                    $("span.errorMsg").text("");
                }
            });

        });

    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        ${requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               value="${requestScope.username}"
                               autocomplete="off" tabindex="1"
                               name="username" id="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               value="${requestScope.email}"
                               autocomplete="off" tabindex="1"
                               name="email" id="email"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 80px;" name="code" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg" style="float: right;
                         margin-right: 40px;width: 100px;height: 32px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>