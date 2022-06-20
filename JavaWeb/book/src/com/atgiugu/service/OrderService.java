package com.atgiugu.service;

import com.atgiugu.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
