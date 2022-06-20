package com.atguigu.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1、获取 web.xml 中配置的上下文参数 context-param
        ServletContext context = getServletConfig().getServletContext();
        String username = context.getInitParameter("username");
        System.out.println("context-Param参数username的值是"+username);
        String password = context.getInitParameter("password");
        System.out.println("context-Param参数password的值是"+password );
//        2、获取当前的工程路径，格式: /工程路径
        System.out.println("当前工程路径"+context.getContextPath());
//        3、获取工程部署后在服务器硬盘上的绝对路径
        /**
         * / 斜杠被服务器解析地址为http://ip:port/工程名/ 映射到idea代码的web目录 <br/>
         */
        System.out.println(context);
        System.out.println("工程部署的路径是"+context.getRealPath("/"));
        System.out.println("工程下css目录的绝对路径是"+context.getRealPath("/css"));
    }
}
