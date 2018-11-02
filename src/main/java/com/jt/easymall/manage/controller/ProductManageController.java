package com.jt.easymall.manage.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.easymall.pojo.Product;
import com.jt.easymall.service.ProductService;
import com.jt.easymall.vo.EasyUIResult;
import com.jt.easymall.vo.SysResult;
@Controller
public class ProductManageController {
	@Autowired
	private ProductService productService;
	
	//后台新增逻辑
	@RequestMapping("/product/save")
	@ResponseBody
	public SysResult saveProduct(Product product){
		SysResult result = new SysResult();
		try {
			int success = productService.saveProduct(product);
			if(success == 1){
				result.setStatus(200);
				result.setMsg("你这个商品还不赖");
				return result;
			}else{
				result.setStatus(201);
				result.setMsg(new String("你新增的是什么玩意儿".getBytes("iso8859-1")));
				return result;
			}
		} catch (Exception e) {
			result.setStatus(201);
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	//后台分页查询
	@RequestMapping("/product/query")
	@ResponseBody
	public EasyUIResult queryManageproducts(int page,int rows){
		List<Product> pList = productService.queryProducts(page, rows);
		int total = productService.queryTotal();
		// 封装数据list集合，和总数量total到EasyUIResult中，该ajax使用
		EasyUIResult result = new EasyUIResult();
		result.setTotal(total);
		result.setRows(pList);
		return result;
	}
	//后台更新商品
	@RequestMapping("/product/update")
	@ResponseBody
	public SysResult updateProduct(Product product){
		SysResult result = new SysResult();
		//调用service更新方法
		int success = productService.updateProduct(product);
		if(success == 1){//修改成功
			result.setStatus(200);
			result.setMsg("更新成功");
			return result;
		}else{
			result.setStatus(201);
			return result;
		}
	}
	//后台删除商品
	@RequestMapping("/product/delete")
	@ResponseBody
	public SysResult deleteProduct(@RequestParam("ids")String ids ){
		String[] arrayId = ids.split(",");
		int success = 0;
		for(String id :arrayId){
			//调用service删除方法
		    success = productService.deleteProduct(id);
		    success++;
		}
		SysResult result = new SysResult();
		if(success == arrayId.length){//修改成功
			result.setStatus(200);
			result.setMsg("删除成功");
			return result;
		}else{
			result.setStatus(201);
			result.setMsg("删除失败");
			return result;
		}
	}
}
