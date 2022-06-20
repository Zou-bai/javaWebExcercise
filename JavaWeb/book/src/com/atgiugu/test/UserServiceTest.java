package com.atgiugu.test;

import com.atgiugu.pojo.User;
import com.atgiugu.service.UserService;
import com.atgiugu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"bbj168","shuangji666","bbj168@qq.com"));
        userService.registUser(new User(null,"abc168","shuangji666","abc168@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"bbj168","shuangji666",null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("zoubai")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}