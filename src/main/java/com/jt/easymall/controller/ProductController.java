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
	//������Ʒ��ҳ��ѯ��Ĭ�϶���5��
	@RequestMapping("/product/page")
	public String queryProducts(Integer currentPage,Integer rows,Model model){
		//����ҵ�����󣬴���ҵ���߼������ó־ò��ȡ����
		List<Product> pList = productService.queryProducts(currentPage,rows);
		//��װһ��page����model("page",page)
		Page page = new Page();
		page.setProducts(pList);
		page.setCurrentPage(currentPage);
		int totalPage = productService.queryCount(rows);
		page.setTotalPage(totalPage);
		model.addAttribute("page", page);
		return "product_list";
	}
	//��Ʒ����
	@RequestMapping("/product/findProductById/{id}")
	public String findProductById(@PathVariable String id,Model model){
		Product product = productService.findProductById(id);
		model.addAttribute("product", product);
		return "product_info";
	}
	
}
