package com.atgiugu.service.impl;

import com.atgiugu.dao.BookDao;
import com.atgiugu.dao.OrderDao;
import com.atgiugu.dao.OrderItemDao;
import com.atgiugu.dao.impl.BookDaoImpl;
import com.atgiugu.dao.impl.OrderDaoImpl;
import com.atgiugu.dao.impl.OrderItemImpl;
import com.atgiugu.pojo.*;
import com.atgiugu.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号：唯一性
        String orderId =System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

        //遍历购物车每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry :cart.getItems().entrySet()){
            //获取每一个购物车的商品项
            CartItem cartItem = entry.getValue();
            //转化为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();

        return orderId;
    }
}
