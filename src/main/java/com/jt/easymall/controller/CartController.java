package com.jt.easymall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.easymall.pojo.Cart;
import com.jt.easymall.service.CartService;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("cart/mycart")
	public String showMyCart(HttpSession session,Model model){
		//判断当前请求对应的session是否保存了userId，有就登录
		String userId = (String) session.getAttribute("userId");
		//登录成功，调用service查询数据
		 List<Cart> cartList = cartService.queryMycart(userId);
		 model.addAttribute("cartList", cartList);
		 return "cart";
	}
	
	//新增购物车
	@RequestMapping("cart/addCart/{productId}/{num}")
	public String adCart(@PathVariable String productId,@PathVariable int num,HttpSession session){
		String userId = (String) session.getAttribute("userId");
		int success = cartService.addCart(userId,productId,num);
		if(success == 1){//新增成功，update,insert
			return "redirect:/cart/mycart";// 重定向到我的购物车，做重定向查询
		}else{
			return "index";
		}
	}
	
	//更新商品数量
	@RequestMapping("cart/editCart/{productId}/{num}")
	public String updateCartNum(@PathVariable String productId,@PathVariable int num,HttpSession session){
		String userId = (String) session.getAttribute("userId");
		cartService.updateCartNum(userId, productId, num);
		return "redirect:/cart/mycart";
	}
	
	
	//删除购物车中的商品信息
	@RequestMapping("cart/deleteCart/{productId}")
	public String deleteCart(@PathVariable String productId,HttpSession session){
		String userId = (String) session.getAttribute("userId");
		cartService.deletCart(productId,userId);
		return "redirect:/cart/mycart";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
