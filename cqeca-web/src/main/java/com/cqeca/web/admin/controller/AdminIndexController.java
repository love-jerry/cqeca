package com.cqeca.web.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqeca.service.news.NewsService;
import com.cqeca.util.constant.FiledsConstant;

/**
 * @ClassName: AdminIndexController
 * @Description:后台管理首页控制器
 * @author chenrui
 * @date 2015-3-31 上午11:17:44
 */
@Controller
@RequestMapping(value="/manager")
public class AdminIndexController {
	
	@Autowired
	private NewsService newsService;

	private static final Logger logger = LoggerFactory.getLogger(AdminIndexController.class);

	/**
	 * 新闻管理页面
	 * @param start
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/main")
	public String toManagerIndex(Integer start,Integer pageSize,Model model) {
		logger.info("welcome to manager index page!");
		if(start == null ) {
			start = 1;
		}
		if(pageSize == null) {
			pageSize = FiledsConstant.DEFAULT_PAGE_SIZE;
		}
		
		model.addAllAttributes(newsService.findAllNews(start, pageSize));
		
		return "backManager";
	}

}
