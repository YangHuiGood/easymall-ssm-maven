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
	
	//��̨�����߼�
	@RequestMapping("/product/save")
	@ResponseBody
	public SysResult saveProduct(Product product){
		SysResult result = new SysResult();
		try {
			int success = productService.saveProduct(product);
			if(success == 1){
				result.setStatus(200);
				result.setMsg("�������Ʒ������");
				return result;
			}else{
				result.setStatus(201);
				result.setMsg(new String("����������ʲô�����".getBytes("iso8859-1")));
				return result;
			}
		} catch (Exception e) {
			result.setStatus(201);
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	//��̨��ҳ��ѯ
	@RequestMapping("/product/query")
	@ResponseBody
	public EasyUIResult queryManageproducts(int page,int rows){
		List<Product> pList = productService.queryProducts(page, rows);
		int total = productService.queryTotal();
		// ��װ����list���ϣ���������total��EasyUIResult�У���ajaxʹ��
		EasyUIResult result = new EasyUIResult();
		result.setTotal(total);
		result.setRows(pList);
		return result;
	}
	//��̨������Ʒ
	@RequestMapping("/product/update")
	@ResponseBody
	public SysResult updateProduct(Product product){
		SysResult result = new SysResult();
		//����service���·���
		int success = productService.updateProduct(product);
		if(success == 1){//�޸ĳɹ�
			result.setStatus(200);
			result.setMsg("���³ɹ�");
			return result;
		}else{
			result.setStatus(201);
			return result;
		}
	}
	//��̨ɾ����Ʒ
	@RequestMapping("/product/delete")
	@ResponseBody
	public SysResult deleteProduct(@RequestParam("ids")String ids ){
		String[] arrayId = ids.split(",");
		int success = 0;
		for(String id :arrayId){
			//����serviceɾ������
		    success = productService.deleteProduct(id);
		    success++;
		}
		SysResult result = new SysResult();
		if(success == arrayId.length){//�޸ĳɹ�
			result.setStatus(200);
			result.setMsg("ɾ���ɹ�");
			return result;
		}else{
			result.setStatus(201);
			result.setMsg("ɾ��ʧ��");
			return result;
		}
	}
}
