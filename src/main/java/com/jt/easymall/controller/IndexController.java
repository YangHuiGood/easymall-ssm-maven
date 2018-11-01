package com.jt.easymall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {
	//�����ҳ��ӳ���ַ
	@RequestMapping("/")
	public String goIndex(){
		return "index";//WEB-INF/views/index.jsp
	}
	@RequestMapping("/page/{pageName}")
	public String goPage(@PathVariable String pageName){
		return pageName;
	}
}
