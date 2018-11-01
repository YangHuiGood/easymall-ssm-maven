package com.jt.easymall.vo;

import java.io.Serializable;
import java.util.List;

import com.jt.easymall.pojo.Product;



public class Page implements Serializable{
	private Integer currentPage;
	private Integer totalPage;
	private List<Product> products;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
