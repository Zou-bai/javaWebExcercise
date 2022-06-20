package com.atguigu.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数（办事的材料）
        String username = req.getParameter("username");
        System.out.println("在servlet1中查看"+username);
    //给材料标记并传递到servlet2查看
        req.setAttribute("key","柜台1的章");
        //问路servlet2该怎么走
        /**
         * 请求转发必须要以斜杠开始，“/”斜杠表示：http：//ip：port：/工程名/，映射到IDEA代码的web目录
         *
         */
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/servlet2");

        //转发到servlet2
        requestDispatcher.forward(req,resp);
    }
}
