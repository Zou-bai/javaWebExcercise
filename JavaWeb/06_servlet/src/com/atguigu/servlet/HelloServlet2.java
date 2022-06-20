package com.atguigu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet2 extends HttpServlet {
    /**
     * doGet方法在get请求时调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig);
        System.out.println("HelloServlet2 的doGet方法。");
        System.out.println("初始化username的值是"+servletConfig.getInitParameter("username"));
        System.out.println("初始化参数url的值是"+servletConfig.getInitParameter("url"));
    }

    /**
     * doPost方法在post请求时调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 的doPost方法。");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("重写了父类的init方法");
//        System.out.println("2 init初始化方法");
////        1、可以获取 Servlet 程序的别名 servlet-name 的值
//        System.out.println("HelloServlet程序的别名是"+config.getServletName());
////        2、获取初始化参数 init-param
//        System.out.println("初始化参数username的值是"+config.getInitParameter("username"));
//        System.out.println("初始化参数url的值是"+config.getInitParameter("url"));
////        3、获取 ServletContext 对象
//        System.out.println(config.getServletContext());

    }
}
