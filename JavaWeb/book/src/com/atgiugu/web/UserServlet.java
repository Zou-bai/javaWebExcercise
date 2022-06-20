package com.atgiugu.web;

import com.atgiugu.pojo.User;
import com.atgiugu.service.UserService;
import com.atgiugu.service.impl.UserServiceImpl;
import com.atgiugu.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数username
        String username = req.getParameter("username");
        //2、调用userservce.existsUsername（）；
        boolean existUsername = userService.existsUsername(username);
        //把返回的结果封装成为map对象
        Map<String ,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }


    /**
     * 处理注销的功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//1、销毁session中的用户登录信息（或者销毁session）
        req.getSession().invalidate();
        //2、重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                       /*
        1、获取请求的参数
        */
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //        2、调用UserService.login（）处理登录业务
        User login = userService.login(new User(null, username, password, null));
        if (login == null) {
            //把错误信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);
//            System.out.println("登录失败");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
//           登录成功；
            //保存用户登陆的信息到Session域中
            req.getSession().setAttribute("user", login);
            //
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
        /*
        userService.login（）登录
        3、根据login()方法返回结果判断登录是否成功
         */
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        ;
//        Map<String,String[]> parameterMap = req.getParameterMap();
//        for (Map.Entry<String,String[]> entry:parameterMap.entrySet()){
//            System.out.println(entry.getKey()+"="+ Arrays.asList(entry.getValue()));
//        }
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        System.out.println(code);
        //2、验证码是否正确 === 写死，要求验证码为abcde
        if (token != null && token.equalsIgnoreCase(code)) {
            //3、检查用户名是否可以使用
            if (userService.existsUsername(username)) {
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                System.out.println("用户名【" + username + "】已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //可用
                //          调用service保存到数据库
                //跳转到注册成功页面regist_success.html
                userService.registUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        } else {
            //把回显信息保存到request域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            System.out.println("验证码[" + code + "]错误");
            //不可用
            //跳回注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

}
