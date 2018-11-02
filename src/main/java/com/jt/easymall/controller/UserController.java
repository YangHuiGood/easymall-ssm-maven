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
	
	//用户名校验
	@RequestMapping("/user_ajax/checkUserName")
	@ResponseBody
	public SysResult checkUserName(String userName){
		//业务层返回1，存在，返回0，不存在
		int exists = userService.checkUserName(userName);
		SysResult result = new SysResult();
		result.setStatus(exists);
		return result;
	}
	
	//用户注册
	@RequestMapping("/user_ajax/regist")
	@ResponseBody
	public SysResult regist(User user) throws Exception{
		System.out.println(user.toString());
		SysResult result = new SysResult();
		int success = userService.regist(user);
		result.setStatus(success);
		if(success == 1){
			result.setStatus(success);
			//处理ajax乱码
			String msg = "当前用户："+user.getUserName()+"注册成功";
			result.setMsg(msg);
		}else{
			
			String msg = "当前用户："+user.getUserName()+"注册失败";
			result.setMsg(msg);
		}
		return result;
	}
	
	//用户登录
	@RequestMapping("/user_ajax/login")
	@ResponseBody
	public SysResult login(User user,HttpSession session){
		User exists = userService.login(user);
		SysResult result = new SysResult();
		//返回的user已存在，如果为空，说明登录失败
		if(null == exists){
			//登录失败
			result.setStatus(0);
			result.setMsg(" 用户名或密码错误");
			return result;
		}else{
			/*
			 * 当前一次会话存储的session域对象的数据
			 * 设置session
			 * 
			 */
			session.setAttribute("userName", exists.getUserName());
			session.setAttribute("userId", exists.getUserId());
			result.setStatus(1);
			return result;
		}
	}
	
	//注销登录
	@RequestMapping("/user_ajax/logout")
	public String logout(HttpSession session){
		//清空session中的数据
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		return "redirect:/";
	}
}
