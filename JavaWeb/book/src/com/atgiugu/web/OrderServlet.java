package com.atgiugu.web;

import com.atgiugu.pojo.Cart;
import com.atgiugu.pojo.User;
import com.atgiugu.service.OrderService;
import com.atgiugu.service.impl.OrderServiceImpl;
import com.atgiugu.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //需要先获取cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        System.out.println("OrderServlet程序在["+Thread.currentThread().getName()+"]中");

        Integer userId = loginUser.getId();
        // 结账需要调用orderService.createOrder（cart，userid）方法
        String orderId = orderService.createOrder(cart, userId);



//        req.setAttribute("orderId",orderId);
        //请求转发到/pages/cart/checked。jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
