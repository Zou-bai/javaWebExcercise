package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password = req.getParameter("password");
        if ("zou_bai".equals(username) && password.equals("123456")){
            //login success
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(100);//当前Cookie一周内有效
            resp.addCookie(cookie);
            System.out.println("登陆成功");
        }else {
            //fail in login
            System.out.println("登陆失败");
        }
    }
}
