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
		//�жϵ�ǰ�����Ӧ��session�Ƿ񱣴���userId���о͵�¼
		String userId = (String) session.getAttribute("userId");
		//��¼�ɹ�������service��ѯ����
		 List<Cart> cartList = cartService.queryMycart(userId);
		 model.addAttribute("cartList", cartList);
		 return "cart";
	}
	
	//�������ﳵ
	@RequestMapping("cart/addCart/{productId}/{num}")
	public String adCart(@PathVariable String productId,@PathVariable int num,HttpSession session){
		String userId = (String) session.getAttribute("userId");
		int success = cartService.addCart(userId,productId,num);
		if(success == 1){//�����ɹ���update,insert
			return "redirect:/cart/mycart";// �ض����ҵĹ��ﳵ�����ض����ѯ
		}else{
			return "index";
		}
	}
	
	//������Ʒ����
	@RequestMapping("cart/editCart/{productId}/{num}")
	public String updateCartNum(@PathVariable String productId,@PathVariable int num,HttpSession session){
		String userId = (String) session.getAttribute("userId");
		cartService.updateCartNum(userId, productId, num);
		return "redirect:/cart/mycart";
	}
	
	
	//ɾ�����ﳵ�е���Ʒ��Ϣ
	@RequestMapping("cart/deleteCart/{productId}")
	public String deleteCart(@PathVariable String productId,HttpSession session){
		String userId = (String) session.getAttribute("userId");
		cartService.deletCart(productId,userId);
		return "redirect:/cart/mycart";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
