package com.cqeca.web.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqeca.service.news.NewsService;

/**
 * @ClassName: IndexController
 * @Description:首页控制器
 * @author chenrui
 * @date 2015-3-31 上午11:17:44
 */
@Controller
public class IndexController {

	@Autowired
	private NewsService newsService;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/index")
	public String toIndex(ModelMap model) {
		logger.info("welcome to index page!");
		Map<String, String> newsMap = newsService.findIndexNews();
		model.addAllAttributes(newsMap);
		logger.info("model:" + model);
		return "index";
	}

	@RequestMapping(value="/invite")
	public String intoInvatePage(Model model) {
		logger.info("welcome to invate page!");
		model.addAllAttributes(newsService.getActivityNews());
		return "invite";
	}
	
	@RequestMapping(value="/about")
	public String intoAboutPage(Model model) {
		logger.info("welcome to invate page!");
		model.addAllAttributes(newsService.getActivityNews());
		return "about";
	}
	
	@RequestMapping(value="/rules")
	public String getNewsDetail(Model model) {
		logger.info("welcome to invate page!");
		model.addAllAttributes(newsService.getActivityNews());
		return "rules";
	}
	
	@RequestMapping(value="/join")
	public String intoJoinPage(Model model) {
		logger.info("welcome to invate page!");
		model.addAllAttributes(newsService.getActivityNews());
		return "join";
	}
	
	@RequestMapping(value="/contact")
	public String intoContactPage(Model model) {
		logger.info("welcome to invate page!");
		model.addAllAttributes(newsService.getActivityNews());
		return "contact";
	}
	
	@RequestMapping(value="/error")
	public String toError(Model model) {
		logger.info("welcome to invate page!");
		return "index";
	}
}
