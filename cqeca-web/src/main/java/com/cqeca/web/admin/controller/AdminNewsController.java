package com.cqeca.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
* @ClassName: AdminNewsController 
* @Description:后台新闻管理控制器 
* @author chenrui
* @date 2015-4-10 下午4:53:06
 */
@Controller
@RequestMapping(value="/manager")
public class AdminNewsController {

	private static final Logger logger = LoggerFactory.getLogger(AdminNewsController.class);

	@RequestMapping(value="/publish")
	@ResponseBody
	public Object publishNews(HttpServletRequest request,Model model) {
		
		
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("status", "ok");
		return result;
		
	}
}
