package com.atgiugu.test;

import com.atgiugu.dao.UserDao;
import com.atgiugu.dao.impl.UserDaoImpl;
import com.atgiugu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
       if ((userDao.queryUserByUsername("admin1")==null)){
            System.out.println("用户名可用");
        }else {
           System.out.println("用户名已存在");
       }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin1","admin")==null){
            System.out.println("用户名或密码错误，登陆失败");
        }else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"zoubai","123456","zoubai@qq.com")));
    }
}