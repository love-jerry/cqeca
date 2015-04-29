package com.cqeca.web.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		Map<String, Map<String,Object>> newsMap = newsService.findIndexNews();
		model.addAllAttributes(newsMap);
		return "index";
	}

}
