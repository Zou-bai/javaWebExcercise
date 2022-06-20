package com.atguigu.servlet;

import com.atguigu.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {

    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、创建cookie对象
        Cookie cookie = new Cookie("key1", "value1");
        //2、通知客户端保存cookie
        resp.addCookie(cookie);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("cookie创建成功！");
    }

    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        Cookie i = CookieUtils.findCookie("key1", cookies);
//        for (Cookie cookie : cookies) {
//            //getName()方法返回cookie的名称
//            //getValue（）方法返回cookie的value值
////            resp.getWriter().write("Cookie["+cookie.getName()+"="+cookie.getValue()+"]<br/>");
//            if("key1".equals(cookie.getName())){
//                i=cookie;
//                break;
//            }
//        }
        if (i != null) {
            resp.getWriter().write("key1的Cookie是" + i.getValue());
        }

    }

    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //方案一：
//        //1、先创建一个要修改的同名的Cookie对象
//        //2、在构造器，同时赋予新得值
//        Cookie cookie = new Cookie("key1","newValue1");
//        //3、调用respond.addCookie（Cookie）；通知客户端保存修改
//        resp.addCookie(cookie);
//
//        resp.getWriter().write("key1的值已经改好");
        //方案二：
        //1、先查找到需要修改的Cookie对象
        Cookie cookie = CookieUtils.findCookie("key2", req.getCookies());
        if (cookie != null) {
            //2、调用setValue（）方法赋予新的cookie值
            cookie.setValue("newValue2");
            //3、调用response.addCookie通知客户端保存修改
            resp.addCookie(cookie);
        }
    }

    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultLife", "defaultLife");
        cookie.setMaxAge(-1);//设置存活时间
        resp.addCookie(cookie);
    }

    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = CookieUtils.findCookie("key4", req.getCookies());
        if (cookie != null) {
            cookie.setMaxAge(0);
            resp.addCookie(cookie);

            resp.getWriter().write("key4的Cookie已经被删除");

        }
    }

    protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("life3600", "life3600");
        cookie.setMaxAge(60 * 60);//设置cookie一小时后无效
        resp.addCookie(cookie);
        resp.getWriter().write("已经创建了一个存活一小时的cookie");
    }

    protected void testPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("path1","path1");
        cookie.setPath(req.getContextPath()+"/abc");
        resp.addCookie(cookie);
        resp.getWriter().write("创建了一个带有path路径的Cookie");
    }



}
