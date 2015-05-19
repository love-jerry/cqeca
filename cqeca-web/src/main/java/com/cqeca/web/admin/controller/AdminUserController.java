package com.cqeca.web.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqeca.service.user.UserService;
import com.cqeca.util.tools.MD5Util;
import com.cqeca.web.annotation.FilterCheckUrl;

/**
 * @ClassName: AdminUserController
 * @Description:后台管理用户控制器
 * @author chenrui
 * @date 2015-3-31 上午11:17:44
 */
@Controller
@RequestMapping(value="/manager")
public class AdminUserController {
	
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	
	@FilterCheckUrl(value = false)
	@RequestMapping(value = "/addUser")
	public String addUser(Model model) {
		logger.info("welcome to index page!");
		
		String userName = "admin";
		String nickName = "管理员";
		String password = MD5Util.encoderByMd5("admin");
		
		userService.saveUser(userName, nickName, password);
		
		return "ok";
	}
	
	@FilterCheckUrl(value = false)
	@RequestMapping(value = "/updatePassword")
	public String updatePwd(String userName,String password,Model model) {
		logger.info("welcome to update password page!");
		
		password = MD5Util.encoderByMd5(password);
		
		userService.updatePwd(userName, password);
		
		return "ok";
	}

}
