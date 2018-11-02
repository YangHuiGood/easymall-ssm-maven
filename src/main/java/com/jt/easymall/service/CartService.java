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
		//��һ�����⣬�����Ƿ��Ѿ��ڹ��ﳵ�д����ˣ�userId,productId
		//��һ����ѯ�Ķ���_cart
		Cart _cart = new Cart();
		_cart.setUserId(userId);
		_cart.setProductId(productId);
		// ��ѯһ�����ﳵ��Ʒ����
		Cart exists = cartMapper.findOne(_cart);
		if(null == exists){
			//�ڶ������⣬�������ֶ�
			//��Ʒ��
			Product product = productMapper.findProductById(productId);
			_cart.setNum(num);
			_cart.setProductImage(product.getProductImgurl());
			_cart.setProductName(product.getProductName());
			_cart.setProductPrice(product.getProductPrice());
			success = cartMapper.addCart(_cart);
		}else{//�Ѵ��ڵĹ��ﳵ��Ʒ��ִ�и�����������
			exists.setNum(exists.getNum()+num);
			success = cartMapper.updateCartNum(exists);
		}
		return success;
	}
	
	public void updateCartNum(String userId,String productId,int num){
		//��װһ��������cart
		Cart cart= new Cart();
		cart.setUserId(userId);
		cart.setProductId(productId);
		cart.setNum(num);
		cartMapper.updateCartNum(cart);
	}
	public void deletCart(String productId, String userId) {
		// ��װһ��������cart
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setProductId(productId);
		cartMapper.deletCart(cart);
	}

	
	
	
	
	
	
	
	
}
