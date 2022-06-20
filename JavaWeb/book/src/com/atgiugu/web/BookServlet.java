package com.atgiugu.web;

import com.atgiugu.pojo.Book;
import com.atgiugu.pojo.Page;
import com.atgiugu.service.BookService;
import com.atgiugu.service.impl.BookServiceImpl;
import com.atgiugu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用BookService.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");
        //3、保存page对象到request域中
        req.setAttribute("page", page);
        //4、请求转发到/page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("paseNo"), 0);
        pageNo++;
        //1、获取请求的参数==封装成位Book参数
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2、调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3、跳到图书列表页面
        resp.sendRedirect(req.getContextPath() +
                "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的id，图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2、调用bookService.deleteBookById（）；删除图书
        bookService.deleteBookById(id);
        //3、重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() +
                "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1、获取请求的参数===封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2、调用Book Service.updateBook（book）；修改图书
        bookService.updateBook(book);
        //3、重定向回图书列表管理页面
        // 地址：/工程名/manager/bookServlet？action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page"
                + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2、调用bookService.queryBookById(id)
        Book book = bookService.queryBookById(id);
        //3、保存图书到request域中
        req.setAttribute("book", book);
        //4、请求转发到.page/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2、把全部图书保存到request域中
        req.setAttribute("books", books);
        //3、请求转发到/page/manage/book_manager.jsp页面
        System.out.println("all right");
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }


}

