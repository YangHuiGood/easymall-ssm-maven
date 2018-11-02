package com.jt.easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.easymall.mapper.CartMapper;
import com.jt.easymall.mapper.ProductMapper;
import com.jt.easymall.pojo.Cart;
import com.jt.easymall.pojo.Product;

@Service
public class CartService {
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private ProductMapper productMapper;
	
	public List<Cart> queryMycart(String userId) {
		return cartMapper.queryMycart(userId);
	}
	public int addCart(String userId, String productId, int num) {
		int success = 0;
		//第一个问题，数据是否已经在购物车中存在了，userId,productId
		//做一个查询的对象，_cart
		Cart _cart = new Cart();
		_cart.setUserId(userId);
		_cart.setProductId(productId);
		// 查询一个购物车商品数据
		Cart exists = cartMapper.findOne(_cart);
		if(null == exists){
			//第二个问题，差三个字段
			//商品表
			Product product = productMapper.findProductById(productId);
			_cart.setNum(num);
			_cart.setProductImage(product.getProductImgurl());
			_cart.setProductName(product.getProductName());
			_cart.setProductPrice(product.getProductPrice());
			success = cartMapper.addCart(_cart);
		}else{//已存在的购物车商品，执行更新数量操作
			exists.setNum(exists.getNum()+num);
			success = cartMapper.updateCartNum(exists);
		}
		return success;
	}
	
	public void updateCartNum(String userId,String productId,int num){
		//封装一个变量，cart
		Cart cart= new Cart();
		cart.setUserId(userId);
		cart.setProductId(productId);
		cart.setNum(num);
		cartMapper.updateCartNum(cart);
	}
	public void deletCart(String productId, String userId) {
		// 封装一个变量，cart
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setProductId(productId);
		cartMapper.deletCart(cart);
	}

	
	
	
	
	
	
	
	
}
