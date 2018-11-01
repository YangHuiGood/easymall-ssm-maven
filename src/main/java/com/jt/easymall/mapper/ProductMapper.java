package com.jt.easymall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.easymall.pojo.Product;

public interface ProductMapper {
	List<Product> queryProducts(@Param("start")int start,@Param("rows")int rows);

	int queryCount();

	Product findProductById(String id);

	int saveProduct(Product product);

	int updateProduct(Product product);

	int deleteProduct(String ids);
}
