package com.cqeca.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqeca.service.login.session.SessionEntity;
import com.cqeca.service.news.NewsService;
import com.cqeca.util.constant.FiledsConstant;
import com.cqeca.util.enums.NewsTypeEnum;
import com.cqeca.web.annotation.FilterCheckUrl;
/**
* @ClassName: AdminNewsController 
* @Description:后台新闻管理控制器 
* @author chenrui
* @date 2015-4-10 下午4:53:06
 */
@Controller
@RequestMapping(value="/manager")
public class AdminNewsController {

	@Autowired
	private NewsService newsService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminNewsController.class);

//	@FilterCheckUrl(value = true)
	@RequestMapping(value="/publish")
	@ResponseBody
	public Object publishNews(String title,String content,NewsTypeEnum category,String tag, HttpServletRequest request,Model model) {
		logger.info("welcome to publish news page");
		HttpSession session = request.getSession();
		
//		SessionEntity sessionEntity = (SessionEntity)(session.getAttribute(FiledsConstant.SESSION_KEY));
//		String userId = sessionEntity.getUserId();
		String userId = "75870570-9d50-46ee-880f-b867133951d4";
		category = NewsTypeEnum.OTHER_NEWS;
		newsService.saveNews(userId, category, title, content, tag);

		return "/ok";
		
	}
}
