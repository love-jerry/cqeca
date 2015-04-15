package com.cqeca.web.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqeca.service.news.NewsService;
import com.cqeca.service.news.form.NewsDetailForm;

/**
* @ClassName: NewsController 
* @Description:新闻控制器 
* @author chenrui
* @date 2015-4-10 下午4:54:08
 */
@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	
	@RequestMapping(value="/news_detail")
	public String getNews(String newsId,Model model) {
		logger.info("welcome to news page!");
		NewsDetailForm newsDetailForm = newsService.getNewsDetail(newsId);
		model.addAttribute("newsDetail", newsDetailForm);
		return "/news_detail";
	}
	
}
