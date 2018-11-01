package com.jt.easymall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.easymall.pojo.Product;
import com.jt.easymall.service.ProductService;
import com.jt.easymall.vo.Page;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	//处理商品分页查询，默认都是5条
	@RequestMapping("/product/page")
	public String queryProducts(Integer currentPage,Integer rows,Model model){
		//调用业务层对象，处理业务逻辑，调用持久层获取数据
		List<Product> pList = productService.queryProducts(currentPage,rows);
		//封装一个page对象，model("page",page)
		Page page = new Page();
		page.setProducts(pList);
		page.setCurrentPage(currentPage);
		int totalPage = productService.queryCount(rows);
		page.setTotalPage(totalPage);
		model.addAttribute("page", page);
		return "product_list";
	}
	//商品详情
	@RequestMapping("/product/findProductById/{id}")
	public String findProductById(@PathVariable String id,Model model){
		Product product = productService.findProductById(id);
		model.addAttribute("product", product);
		return "product_info";
	}
	
}
