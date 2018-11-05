package com.jt.easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jt.easymall.mapper.ProductMapper;
import com.jt.easymall.pojo.Product;
import com.jt.easymall.util.UUIDUtil;

@Service
public class ProductService{
	@Autowired
	private ProductMapper productMapper;
	public List<Product> queryProducts(Integer currentPage, Integer rows) {
		//��ǰҳ����ʼ������
		int start = currentPage*rows-rows;
		return productMapper.queryProducts(start, rows);
	}
	public int queryCount(Integer rows) {
		//��Ʒ����
		int total = productMapper.queryCount();
		//��ҳ��
		int totalPage = total % rows == 0 ? (total/rows) : ((total/rows)+1);
		return totalPage;
	}
	public Product findProductById(String id) {
		Product product = productMapper.findProductById(id);
		return product;
	}
	@Transactional
	public int saveProduct(Product product) {
		//ȱ��id,ʹ��uuid������
		String id = UUIDUtil.getUUID();
		product.setProductId(id);
		int success = productMapper.saveProduct(product);
		return success;
	}
	public int queryTotal() {
		return productMapper.queryCount();
	}
	public int updateProduct(Product product) {
        int success = productMapper.updateProduct(product);
		return success;
	}
	public int deleteProduct(String ids) {
		int success = productMapper.deleteProduct(ids);
		return success;
	}

}
