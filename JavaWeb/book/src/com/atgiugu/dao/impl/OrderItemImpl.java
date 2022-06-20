package com.atgiugu.dao.impl;

import com.atgiugu.dao.OrderItemDao;
import com.atgiugu.pojo.OrderItem;

public class OrderItemImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalprice(),orderItem.getOrderId());
    }
}
