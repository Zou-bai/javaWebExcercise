# javaWebExcercise
直接看[BookShop: 通过运用JavaWeb三大组件组件之servlet,Filter进行JavaWeb项目开发。 (gitee.com)](https://gitee.com/fowner/bookshop)吧很详细
# 个人简单总结
总结的比较一般，算是操作流程？（大雾）
## 1、html

### 1、B/S软件的结构

C/S结构就是浏览器到服务器，b/s架构就是客户端到服务器

![image-20220415190932280](C:\Users\sdjkf\AppData\Roaming\Typora\typora-user-images\image-20220415190932280.png)

### 2、前端一般开发流程

![image-20220415191012408](C:\Users\sdjkf\AppData\Roaming\Typora\typora-user-images\image-20220415191012408.png)

### 3、网页的组成部分

> 页面由三部分内容组成！ 
>
> 分别是内容（结构）、表现、行为。 
>
> 内容（结构），是我们在页面中可以看到的数据。我们称之为内容。一般内容 我们使用 
>
> html 技术来展示。 
>
> 表现，指的是这些内容在页面上的展示形式。比如说。布局，颜色，大小等等。一般使用 
>
> CSS 技术实现行为，指的是页面中元素与输入设备交互的响应。一般使用 javascript 技术实现。 

### 4、html的书写规范

```html
<!DOCTYPE html><!--约束，申明 -->
<html lang="zh_CN"><!-- html标签表示html的开始 lang="zh_CN"表示中文  html标签中一般分为两部分，分别是head和body -->
<head><!-- 表示头部信息，一般包括三部分，title标签，css样式，js代码 -->
    <meta charset="UTF-8"><!-- 表示当前页面使用utf-8字符集 -->
    <title>标题</title><!-- 表示标题 -->
</head>
<!-- bgcolor是背景颜色属性标签
     onclik是点击时间
-->
<body bgcolor="green" ><!-- body标签是整个html页面显示的主体内容 -->
<button onclick="alert('芜湖起飞')">点击</button>
hello
</body>
</html>
```

### 5、HTML标签介绍

![image-20220415192548734](C:\Users\sdjkf\AppData\Roaming\Typora\typora-user-images\image-20220415192548734.png)



# 项目部分

概要：项目实现的是一个书城的demo，实现用户管理员登录和注册功能。用户可以在书城里订购图书，查看订单号。管理员可以修改库存，修改价格，增删图书，实现发货等。用到的工具类有阿里的druid、集成测试工具junit、数据库操作工具commons-dbutils等

##  基本环境说明

- Maven 3.6.1（jar管理工具）
- Tomcat 8.5.59（服务器）
- JDK 1.8
- JavaEE 8.0
- MySql 5.7.29（数据库管理系统）
- IDEA 2019免费版（开发工具）
- druid 1.2.3（数据库连接池）
- commons-dbutils 1.7 （数据库操作工具）
- kaptcha 2.3.2(验证码)
- Gson 2.8.6（Json支持）
  注：所有依赖参考pom.xml

## 第一阶段：表单验证

将static文件夹和script文件夹（包含css文件，图片，jquery文件）复制到web下的static文件夹里面

1、表单验证部分，新建模块，将书城的静态资源拷贝到05bookstatic工程下

可以看出来的是pages包下的基本都是页面，每个页面基本都按照不同的功能进行划分，cart就是购物车相关的页面，manager是与后台相关的页面，order是订单的，user是与用户相关的。一般来说，jquery放在static/script下

然后再regist.html页面引入jQuery文件

```html
   <link type="text/css" rel="stylesheet" href="../../static/css/style.css">
   <script type="text/javascript" src="../../static/script/jquery-1.7.2.js"></script>
   <script type="text/javascript">
```

页面加载完成之后，给页面绑定单击事件实现以下五个部分，验证用户名，密码，确认密码和密码一样，邮箱，验证码。因为验证码和服务器有关，暂时只做输入了验证码的部分

```java
//页面加载完成之后
$(function () {

   $("#sub_btn").click(function () {
      // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
      //1。获取用户名输入框的内容
      //2.创建正则表达式对象
      //3.使用test方法验证
      //4.提示用户结果
      var usernameText = $("#username").val();
      var usernamePatt = /^\w{5,12}$/
      if (!usernamePatt.test(usernameText)){
         $("span.errorMsg").text("用户名不合法");
         return false;
      }else {
         $("span.errorMsg").text("");
      }
      // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
      var passwordText = $("#password").val();
      var passwordPatt = /^\w{5,12}$/
      if (!passwordPatt.test(passwordText)){
         $("span.errorMsg").text("密码不合法");
         return false;
      }else {
         $("span.errorMsg").text("");
      }
      // 验证确认密码：和密码相同
      var repwdText = $("#repwd").val();
      if (repwdText!=passwordText){
         $("span.errorMsg").text("确认密码和密码不一致");
         return false;
      }else {
         $("span.errorMsg").text("");
      }
      // 邮箱验证：xxxxx@xxx.com
      //1.获取邮箱里的内容
      //2.创建正则表达式对象
      //3.使用test方法验证是否合法
      var emailtext = $("#email").val();
      var emailPatt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
      if (!emailPatt.test(emailtext)){
         $("span.errorMsg").text("邮箱不合法");
      }else {
         $("span.errorMsg").text("");
      }
      // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成
      var codetext = $("#code").val();
      codetext=$.trim(codetext);
      if (codetext ==""||codetext==null){
         $("span.errorMsg").text("验证码不能为空");
      }else {
         $("span.errorMsg").text("");
      }
   });

});
```

## 第二阶段：用户登录和注册

##### 前言：介绍三层架构

![image-20220416191612059](C:\Users\sdjkf\AppData\Roaming\Typora\typora-user-images\image-20220416191612059.png)

JavaEE三层架构就分为web（视图展示）层，service（业务）层和dao（持久）层。三层架构之上是客户端，三层架构之下就是数据库，servlet在框架里代表是springMVC，service是spring，dao是mybatis

分层的目的就是为了解耦。解耦就是为了降低代码的耦合度。方便项目后期的维护和升级

用到的包

web 层  com.atguigu.web/servlet/controller 

service 层  com.atguigu.service Service 接口包 

​				    com.atguigu.service.impl  Service 接口实现类 

dao 持久层 com.atguigu.dao Dao 接口包 

​					com.atguigu.dao.impl  Dao 接口实现类 

实体 bean 对象  com.atguigu.pojo/entity/domain/bean  JavaBean 类 

测试包  com.atguigu.test/junit  工具类 

​			  com.atguigu.utils 

##### 准备：搭建开发环境（创建相关的包）

如图

![image-20220416204318386](C:\Users\sdjkf\AppData\Roaming\Typora\typora-user-images\image-20220416204318386.png)

##### （一）创建数据库和表

1、准备数据库

```sql
drop database if exists book; 
create database book; 
use book; 
create table t_user( 
`id` int primary key auto_increment, 
`username` varchar(20) not null unique, 
`password` varchar(32) not null, 
`email` varchar(200) 
);
insert into t_user(`username`,`password`,`email`) values('admin','admin','admin@atguigu.com'); 
select * from t_user;
```

##### （二）创建user（用户）类

##### **编写数据库表对应的** **JavaBean** **对象**

```Java
public class User {
private Integer id; 
private String username; 
private String password; 
private String email;
//getandesertter，tostring，构造方法
```

##### （三）编写jdbcUtils工具类

```
package com.dream.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 匠人码农
 * @date 2020/11/11 8:40
 * 概要：
 *     jdbc工具类
 */

public class JdbcUtils {

    //数据库连接池
    public static DruidDataSource dataSource;

    /*
     * 创建数据库连接池静态代码块
     */
    static {
        try {
            //创建properti对象
            Properties properties = new Properties();
            //获取jsbc配置文件数据流
            InputStream inputSteam = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //导入jdbc配置文件
            properties.load(inputSteam);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中连接
     */
    /**
     * 获取数据库连接池中连接
     * @return 如果不为null，则获取数据库连接成功.<br/>
     *         如果为null,则获取数据库连接失败.<br/>
     */
    public static Connection getConnection(){

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void close(Connection conn){
        //连接不为null的话，关闭连接。
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

- 工具类JdbcUtils测试

  ```Java
  package com.dream.utils;
  
  import org.junit.Test;
  
  import java.sql.Connection;
  
  /**
   * @author 匠人码农
   * @date 2020/11/11 10:18
   * 概要：
   *     JdbcUtils测试类
   */
  
  public class JdbcUtilsTest {
  
      @Test
      public void testJdbcUtils() {
          for(int i = 0;i < 20 ;i++){
              //获取连接
              Connection conn = JdbcUtils.getConnection();
              //打印连接
              System.out.println(conn);
              //关闭连接
              JdbcUtils.close(conn);
          }
      }
  }
  ```

##### （四）编写BaseDao

- 导入sql操作工具包

  ```
  <!-- 操作数据库工具类 -->
  <dependency>
    <groupId>commons-dbutils</groupId>
    <artifactId>commons-dbutils</artifactId>
    <version>1.7</version>
  </dependency>
  ```

- 创建BaseDao.java类

  ```
  package com.dream.dao.impl;
  
  import com.dream.utils.JdbcUtils;
  import org.apache.commons.dbutils.QueryRunner;
  import org.apache.commons.dbutils.handlers.BeanHandler;
  import org.apache.commons.dbutils.handlers.BeanListHandler;
  import org.apache.commons.dbutils.handlers.ScalarHandler;
  
  import java.sql.Connection;
  import java.sql.SQLException;
  import java.util.List;
  
  /**
   * @author 匠人码农
   * @date 2020/11/11 10:55
   * 概要：
   *     BaseDao类
   *
   */
  
  public abstract class BaseDao {
  
      //使用apache的DbUtils操作数据库
      private final QueryRunner queryRunner = new QueryRunner();
  
      /**
       * 用来执行insert，update，delete语句
       * @param sql 要执行的sql文
       * @param args sql的参数
       * @return 如果查询到结果返回>1的值.<br/>
       *         返回-1 表示没有查询到结果。
       */
      public int update(String sql,Object ... args){
  
          //获取连接
          Connection conn = JdbcUtils.getConnection();
  
          //执行sql
          try {
              return queryRunner.update(conn,sql,args);
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              //关闭连接
              JdbcUtils.close(conn);
          }
          return -1;
      }
  
      /**
       * 查询返回一个JavaBean的sql
       * @param type JavaBean类型
       * @param sql  执行的sql文
       * @param args sql的参数
       * @param <T>  类型的泛型
       * @return     返回一个T类型的对象
       */
      public <T> T queryForOne(Class<T> type,String sql,Object ... args){
          //获取连接
          Connection conn = JdbcUtils.getConnection();
  
          //执行sql
          try {
              return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              //关闭连接
              JdbcUtils.close(conn);
          }
          return null;
      }
  
      /**
       * 返回一个多个JavaBean的List结果集合
       * @param type JavaBean类型
       * @param sql  执行的sql文
       * @param args sql的参数
       * @param <T>  类型的泛型
       * @return     返回一个List
       */
      public <T> List<T> queryForList(Class<T> type,String sql,Object ... args){
          //获取连接
          Connection conn = JdbcUtils.getConnection();
  
          //执行sql
          try {
              return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              //关闭连接
              JdbcUtils.close(conn);
          }
          return null;
      }
  
      /**
       * 返回一个一行一列的结果
       * @param sql 查询的sql
       * @param args  sql参数
       * @return 返回一个一行一列的结果
       */
      public Object queryForSingleValue(String sql,Object ... args){
          //获取连接
          Connection conn = JdbcUtils.getConnection();
  
          //执行sql
          try {
              return queryRunner.query(conn,sql,new ScalarHandler(),args);
          } catch (Exception e) {
              e.printStackTrace();
          }finally {
              JdbcUtils.close(conn);
          }
          return null;
      }
  
  }
  ```

- 编写UserDao和测试

  - 创建UserDao接口类

    ```
    package com.dream.dao;
    
    import com.dream.bean.User;
    
    /**
     * @author 匠人码农
     * @date 2020/11/11 13:34
     * 概要：
     *    UserDao接口类
     */
    
    public interface UserDao {
    
        /**
         * 根据用户名查询用户信息
         * @param userName  用户名
         * @return          如果为null则用户不存在，否则结果为查询的用户信息。
         */
        User queryUserByUserName(String userName);
    
        /**
         * 根据用户名和密码查询用户信息
         * @param userName  用户名
         * @param password  密码
         * @return          如果为null则用户名不存在或者密码错误，否则该用户存在。
         */
        User queryUserByUserNameAndPassword(String userName,String password);
    
        /**
         * 保存用户信息
         * @param user  用户信息
         * @return      如果为-1,保存失败。否则保存成功。
         */
        int saveUser(User user);
    
    }
    ```

  - 创建USerDao接口实现类

    ```
    package com.dream.dao.impl;
    
    import com.dream.bean.User;
    import com.dream.dao.UserDao;
    
    /**
     * @author 匠人码农
     * @date 2020/11/11 13:43
     * 概要：
     *    UserDao接口的实现类
     */
    
    public class UserDaoImpl extends BaseDao implements UserDao {
    
        @Override
        public User queryUserByUserName(String userName) {
    
            String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
    
            return queryForOne(User.class,sql,userName);
    
        }
    
        @Override
        public User queryUserByUserNameAndPassword(String userName, String password) {
            String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
    
            return queryForOne(User.class,sql,userName,password);
        }
    
        @Override
        public int saveUser(User user) {
            String sql = "insert into t_user(`username`,`password`,`email`,`registtime`) values(?,?,?,?)";
    
            return update(sql,user.getUserName(),user.getPassword(),user.getEmail(),user.getRegistTime());
        }
    }
    ```

  - UserDao测试类

    ```
    package com.dream.dao;
    
    import com.dream.bean.User;
    import com.dream.dao.impl.UserDaoImpl;
    import org.junit.Test;
    
    import java.text.SimpleDateFormat;
    import java.util.Date;
    
    /**
     * @author 匠人码农
     * @date 2020/11/11 14:08
     * 概要：
     *     UserDao测试类
     */
    
    public class UserDaoTest {
    
    
        @Test
        public void queryUserByUserName() {
            UserDao userDao = new UserDaoImpl();
    
            if(userDao.queryUserByUserName("admin") != null){
                System.out.println("admin用户存在！");
            }else{
                System.out.println("admin用户不存在！");
            }
    
            if(userDao.queryUserByUserName("gust") != null){
                System.out.println("gust用户存在！");
            }else{
                System.out.println("gust用户不存在！");
            }
    
        }
    
        @Test
        public void queryUserByUserNameAndPassword() {
            UserDao userDao = new UserDaoImpl();
    
            if(userDao.queryUserByUserNameAndPassword("admin","admin") != null){
                System.out.println("登录成功");
            }else{
                System.out.println("用户名或者密码错误！");
            }
    
            if(userDao.queryUserByUserNameAndPassword("admin","123456") != null){
                System.out.println("登录成功");
            }else{
                System.out.println("用户名或者密码错误！");
            }
        }
    
        @Test
        public void saveUser() {
    
            User user = new User();
            user.setUserName("gust");
            user.setPassword("gust");
            user.setEmail("gust@test.com");
    
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
            user.setRegistTime(dateFormat.format(date.getTime()));
    
            UserDao userDao = new UserDaoImpl();
    
            if(userDao.queryUserByUserName(user.getUserName()) != null){
                System.out.println("用户已经被注册，请更换用户名！");
            }else {
                if (-1 != userDao.saveUser(user)) {
                    System.out.println("用户注册成功！");
                } else {
                    System.out.println("用户注册失败！");
                }
            }
    
    
        }
    }
    ```

- UserService层

  - UserService接口

    ```
    package com.dream.service;
    
    import com.dream.bean.User;
    
    /**
     * @author 匠人码农
     * @date 2020/11/11 16:27
     * 概要：
     *    用户接口
     */
    
    public interface UserService {
    
        /**
         * 注册用户功能
         * @param user
         */
        void registUser(User user);
    
        /**
         * 登录
         * @param user
         * @return
        */
        User login(User user);
    
        /**
         * 检查用户是否已经存在
         * @param userName
         * @return true:表示用户已经存在<br/>
         *         false:表示用户不存在
         *
         */
        boolean existsUserName(String userName);
    
    }
    ```

  - UserService接口实现类

    ```
    package com.dream.service.impl;
    
    import com.dream.bean.User;
    import com.dream.dao.UserDao;
    import com.dream.dao.impl.UserDaoImpl;
    import com.dream.service.UserService;
    
    /**
     * @author 匠人码农
     * @date 2020/11/11 16:33
     * 概要：
     *     业务处理service
     */
    
    public class UserServiceImpl implements UserService {
    
        //UserDao
        UserDao userDao = new UserDaoImpl();
    
        @Override
        public void registUser(User user) {
            userDao.saveUser(user);
        }
    
        @Override
        public User login(User user) {
            return userDao.queryUserByUserNameAndPassword(user.getUserName(),user.getPassword());
        }
    
        @Override
        public boolean existsUserName(String userName) {
    
            if(null != userDao.queryUserByUserName(userName)){
                return true;
            }
            return false;
        }
    }
    ```

- 实现注册功能

  - 创建RegsitServlet.java

    ```
    package com.dream.servlet;
    
    import com.dream.bean.User;
    import com.dream.service.UserService;
    import com.dream.service.impl.UserServiceImpl;
    
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    
    /**
     * @author 匠人码农
     * @date 2020/11/11 17:11
     * 概要：
     *     注册用户功能
     */
    
    public class RegistServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
            User user = new User();
            UserService userService = new UserServiceImpl();
    
            //获取请求参数
            //用户名
            String userName = req.getParameter("username");
            //密码
            String password = req.getParameter("password");
            //邮件地址
            String email = req.getParameter("email");
            //验证码
            String code = req.getParameter("code");
    
            //检查验证码
            if(!"6n6np".equals(code)){
                //跳转回登录界面
                req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
            } else {
    
                //bean设定
                user.setUserName(userName);
                user.setPassword(password);
                user.setEmail(password);
                user.setRegistTime(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date()));
    
                //检查用户名是否存在
                //已经存在
                if(userService.existsUserName(userName)){
                    //跳转回登录页面
                    req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
                    //不存在
                } else {
                    //跳转到成功页面
                    userService.registUser(user);
                    req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);
                }
            }
    
        }
    }
    ```

  - 在Web.xml中配置RegistServlet

    ```
    <!-- 配置注册用户的servlet -->
    <servlet>
        <servlet-name>RegistServlet</servlet-name>
        <servlet-class>com.dream.servlet.RegistServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>RegistServlet</servlet-name>
        <url-pattern>/registServlet</url-pattern>
    </servlet-mapping>
    ```

  - 修改regist.html的表单action和method方法

    ```
    <form action="registServlet" method="post">
    ```

  - 在regsit.html，regsit_success.html中添加base标签

    ```
    <!--设定base路径-->
    <base href="http://localhost:8080/BookShop/"/>
    ```

- 编写LoginServlet实现登录功能

  - 创建LoginServlet.java

    ```
    package com.dream.servlet;
    
    import com.dream.bean.User;
    import com.dream.service.UserService;
    import com.dream.service.impl.UserServiceImpl;
    
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    
    /**
     * @author 匠人码农
     * @date 2020/11/11 20:34
     * 概要：
     *     登录功能实现
     */
    
    public class LoginServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
            //获取请求参数
            String userName = req.getParameter("username");
    
            String password = req.getParameter("password");
    
            //调用业务逻辑
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
    
            //创建service对象
            UserService userService = new UserServiceImpl();
    
            //用户和密码正确
            if(null != userService.login(user)){
                //跳转到登录成功页面
                req.getRequestDispatcher("/pages/user/login_success.html").forward(req,resp);
            }else{
                //跳转到登录页面
                req.getRequestDispatcher("pages/user/login.html").forward(req,resp);
            }
        }
    }
    ```

  - 在Web.xml中配置LoginServet

    ```
    <!-- 配置登录的servlet -->
    <servlet>
        <servlet-name>LoginServlet</servlet-name>
        <servlet-class>com.dream.servlet.LoginServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>LoginServlet</servlet-name>
        <url-pattern>/loginServlet</url-pattern>
    </servlet-mapping>
    ```

  - 修改login.html中表单中的action和method方法。

    ```
    <form action="loginServlet" method="post">
    ```

  - 在login.html和login_success.html中添加base标签

    ```
    <!--设定base路径-->
    <base href="http://localhost:8080/BookShop/"/>
    ```
