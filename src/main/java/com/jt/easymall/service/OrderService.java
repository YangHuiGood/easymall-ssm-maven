package com.jt.easymall.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.easymall.mapper.OrderMapper;
import com.jt.easymall.pojo.Order;
import com.jt.easymall.util.UUIDUtil;

@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;

	public void saveOrder(Order order) {
		//oder缺少id,支付状态（利用0来判断未支付超时的订单）
		order.setOrderId(UUIDUtil.getUUID());
		order.setOrderPaystate(0);
		order.setOrderTime(new Date());
		orderMapper.saveOrder(order);
	}

	public List<Order> queryMyorder(String userId) {
		return orderMapper.queryMyorder(userId);
	}

	public void deleteOrder(String orderId) {
		orderMapper.deleteOrder(orderId);
	}
}
