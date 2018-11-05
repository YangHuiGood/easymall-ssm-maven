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
		//oderȱ��id,֧��״̬������0���ж�δ֧����ʱ�Ķ�����
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
