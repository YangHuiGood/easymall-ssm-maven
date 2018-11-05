package com.jt.easymall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.easymall.pojo.Cart;
import com.jt.easymall.pojo.Order;
import com.jt.easymall.service.CartService;
import com.jt.easymall.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	
	//�ռ����ﳵ��ǰ�û�����Ʒ������order_add.jsp��չʾ
	@RequestMapping("order/order-cart")
	public String collectOrderInfo(HttpSession session,Model model){
		//��session�л�ȡuserId
		String userId = (String) session.getAttribute("userId");
		List<Cart> cartList = cartService.queryMycart(userId);
		model.addAttribute("cartList",cartList);
		return "order_add";
	}
	
	//���ռ�������Ϣ��ҳ�棬�����ݴ��ݽ��ж���������
	@RequestMapping("order/addOrder")
	public String saveOrder(Order order,HttpSession session){
		//���ݵ�orderû��userId����Ҫ��session�л�ȡ��װ
		order.setUserId(session.getAttribute("userId")+"");
		//û�гɹ������ҳ�棬���ж������ɰ�
		orderService.saveOrder(order);
		return "index";
	}
	
	//��ѯ�ҵĶ���
	@RequestMapping("order/list")
	public String queryMyorder(HttpSession session,Model model){
		String userId = (String) session.getAttribute("userId");
		List<Order> orderList = orderService.queryMyorder(userId);
		model.addAttribute("orderList",orderList);
		return "order_list";
	}
	
	//ɾ������
	@RequestMapping("order/deleteOrder/{orderId}")
	public String deleteOrder(@PathVariable String orderId){
		orderService.deleteOrder(orderId);
		return "redirect:/order/list";
	}
	
}
