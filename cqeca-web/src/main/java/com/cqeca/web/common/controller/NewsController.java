package com.cqeca.web.common.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqeca.service.news.NewsService;
import com.cqeca.service.news.form.NewsDetailForm;
import com.cqeca.util.constant.FiledsConstant;

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
	public String getNewsDetail(String newsId,Model model) {
		logger.info("welcome to news detail page!");
		NewsDetailForm newsDetailForm = newsService.getNewsDetail(newsId);
		model.addAttribute("newsDetail", newsDetailForm);
		return "/news_detail";
	}
	
	@RequestMapping(value="/great_news")
	public String getGreatNews(Model model) {
		logger.info("welcome to great news page!");
		List<NewsDetailForm> greatNewsList = newsService.queryGreatNews();
		model.addAttribute("greatNews", greatNewsList);
		return "/great_news";
	}
	
	@RequestMapping(value="/find_news")
	public String findNews(int newsType,int start,int pageSize,Model model) {
		logger.info("welcome to find news page!");
		List<NewsDetailForm> newsList = newsService.queryNews(newsType, start, pageSize);
		model.addAttribute("newsList", newsList);
		return "/news_list";
	}
	
	@RequestMapping(value="/find_news_by_label")
	public String findNewsByLabel(HttpServletRequest request,HttpServletResponse response,String label,Model model) {
		logger.info("welcome to find news by label!");
		if(null == label || "".equals(label) || label.matches(FiledsConstant.SEARCH_REG_STR)) {
			
			try {
				request.getRequestDispatcher("/index").forward(request, response);
			} catch (ServletException e) {
				logger.error("重定向到首页发生异常",e);
			} catch (IOException e) {
				logger.error("搜索新闻跳转首页错误",e);
			}
		}
		List<NewsDetailForm> newsList = newsService.queryNewsByLabel(label);
		model.addAttribute("newsList", newsList);
		return "/news_list";
	}
}
