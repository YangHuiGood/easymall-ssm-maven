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
	
	//收集购物车当前用户的商品，放入order_add.jsp中展示
	@RequestMapping("order/order-cart")
	public String collectOrderInfo(HttpSession session,Model model){
		//从session中获取userId
		String userId = (String) session.getAttribute("userId");
		List<Cart> cartList = cartService.queryMycart(userId);
		model.addAttribute("cartList",cartList);
		return "order_add";
	}
	
	//从收集订单信息的页面，将数据传递进行订单的新增
	@RequestMapping("order/addOrder")
	public String saveOrder(Order order,HttpSession session){
		//传递的order没有userId，需要从session中获取封装
		order.setUserId(session.getAttribute("userId")+"");
		//没有成功或错误页面，不判断新增成败
		orderService.saveOrder(order);
		return "index";
	}
	
	//查询我的订单
	@RequestMapping("order/list")
	public String queryMyorder(HttpSession session,Model model){
		String userId = (String) session.getAttribute("userId");
		List<Order> orderList = orderService.queryMyorder(userId);
		model.addAttribute("orderList",orderList);
		return "order_list";
	}
	
	//删除订单
	@RequestMapping("order/deleteOrder/{orderId}")
	public String deleteOrder(@PathVariable String orderId){
		orderService.deleteOrder(orderId);
		return "redirect:/order/list";
	}
	
}
