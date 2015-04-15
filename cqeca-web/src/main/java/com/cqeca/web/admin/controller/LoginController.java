package com.cqeca.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqeca.service.login.LoginService;
import com.cqeca.service.login.session.SessionEntity;
import com.cqeca.util.constant.FiledsConstant;
import com.cqeca.web.annotation.FilterCheckUrl;

/***
* @ClassName: LoginController 
* @Description:用户登录控制器 
* @author chenrui
* @date 2015-4-8 上午11:36:50
 */
@Controller
@RequestMapping(value="/manager")
public class LoginController {
	
	@Autowired
	private LoginService loginService; 

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	
	/**
	 * 用户后台登录
	 * @param userName
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@FilterCheckUrl(value=false)
	@RequestMapping(value="/login")
	public String loginIn(String userName,String password,HttpServletRequest request,Model model) {
		try {
			SessionEntity sessionEntity = loginService.loginIn(userName, password);
			if(null == sessionEntity) {
				model.addAttribute("error", "用户名/密码错误");
				return "manager_login";	
			}
			request.getSession().setAttribute(FiledsConstant.SESSION_KEY, sessionEntity);
			return "manager_index";
		} catch (Exception e) {
			logger.error("登录失败");
			return "error";
		}
	}
	
	/**
	 * 用户退出登录
	 * @return
	 */
	@RequestMapping(value="/loginOut")
	public String loginOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(null != session) {			
			session.removeAttribute(FiledsConstant.SESSION_KEY);
			session.invalidate();
		}
		return "manager_login";
	}
	
}
