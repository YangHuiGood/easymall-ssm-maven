package com.jt.easymall.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.easymall.pojo.User;
import com.jt.easymall.service.UserService;
import com.jt.easymall.vo.SysResult;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	//�û���У��
	@RequestMapping("/user_ajax/checkUserName")
	@ResponseBody
	public SysResult checkUserName(String userName){
		//ҵ��㷵��1�����ڣ�����0��������
		int exists = userService.checkUserName(userName);
		SysResult result = new SysResult();
		result.setStatus(exists);
		return result;
	}
	
	//�û�ע��
	@RequestMapping("/user_ajax/regist")
	@ResponseBody
	public SysResult regist(User user) throws Exception{
		System.out.println(user.toString());
		SysResult result = new SysResult();
		int success = userService.regist(user);
		result.setStatus(success);
		if(success == 1){
			result.setStatus(success);
			//����ajax����
			String msg = "��ǰ�û���"+user.getUserName()+"ע��ɹ�";
			result.setMsg(msg);
		}else{
			
			String msg = "��ǰ�û���"+user.getUserName()+"ע��ʧ��";
			result.setMsg(msg);
		}
		return result;
	}
	
	//�û���¼
	@RequestMapping("/user_ajax/login")
	@ResponseBody
	public SysResult login(User user,HttpSession session){
		User exists = userService.login(user);
		SysResult result = new SysResult();
		//���ص�user�Ѵ��ڣ����Ϊ�գ�˵����¼ʧ��
		if(null == exists){
			//��¼ʧ��
			result.setStatus(0);
			result.setMsg(" �û������������");
			return result;
		}else{
			/*
			 * ��ǰһ�λỰ�洢��session����������
			 * ����session
			 * 
			 */
			session.setAttribute("userName", exists.getUserName());
			session.setAttribute("userId", exists.getUserId());
			result.setStatus(1);
			return result;
		}
	}
	
	//ע����¼
	@RequestMapping("/user_ajax/logout")
	public String logout(HttpSession session){
		//���session�е�����
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		return "redirect:/";
	}
}
