package com.atgiugu.test;

import com.atgiugu.pojo.Cart;
import com.atgiugu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"Java从入门到入土",1,new BigDecimal(1000),
                new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门到入土",1,new BigDecimal(1000),
                new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"算法与数据结构",
                1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"Java从入门到入土",1,new BigDecimal(1000),
                new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门到入土",1,new BigDecimal(1000),
                new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"算法与数据结构",
                1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"Java从入门到入土",1,new BigDecimal(1000),
                new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门到入土",1,new BigDecimal(1000),
                new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"算法与数据结构",
                1,new BigDecimal(100),new BigDecimal(100)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
    }
}