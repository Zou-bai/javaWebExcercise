package com.atgiugu.web;

import com.atgiugu.pojo.Book;
import com.atgiugu.pojo.Cart;
import com.atgiugu.pojo.CartItem;
import com.atgiugu.service.BookService;
import com.atgiugu.service.impl.BookServiceImpl;
import com.atgiugu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("商品编号="+req.getParameter("id"));
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id)得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化为cartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(cartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称
        req.getSession().setAttribute("LastName",cartItem.getName());

        //重定向回商品列表页面
        //重定向回原来商品的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车选项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //删除了购物车商品项
            cart.deleteItem(id);
            //重定向回原来商品所在购物车展示页
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车选项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //删除了购物车商品项
            cart.clear();
            //重定向回原来商品所在购物车展示页
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号、商品数量
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),1);
        //获取cart购物车对象
        Cart cart =(Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //修改商品数量
            cart.updateCount(id,count);
            //重定向回原来商品所在购物车展示页
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("商品编号="+req.getParameter("id"));
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id)得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化为cartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(cartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称
        req.getSession().setAttribute("LastName",cartItem.getName());

        //6、返回购物车总的商品数量和最后一个添加的商品名称

        Map<String ,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("LastName",cartItem.getName());

        Gson gson=new Gson();
        String resultMapJsonString = gson.toJson(resultMap);

        resp.getWriter().write(resultMapJsonString);
//        //重定向回商品列表页面
//        //重定向回原来商品的地址页面
//        resp.sendRedirect(req.getHeader("Referer"));
    }
}
