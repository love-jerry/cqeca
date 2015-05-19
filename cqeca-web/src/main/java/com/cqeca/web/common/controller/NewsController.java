package com.cqeca.web.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqeca.service.news.NewsService;
import com.cqeca.service.news.form.NewsDetailForm;
import com.cqeca.service.news.form.NewsForm;
import com.cqeca.util.constant.FiledsConstant;
import com.cqeca.util.enums.NewsTypeEnum;

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
		
		model.addAttribute("newsDetail", JSONObject.fromObject(newsDetailForm).toString());
		model.addAllAttributes(newsService.getActivityNews());
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("moduleH", NewsTypeEnum.getByCode(newsDetailForm.getNewsType()).getMessage());
		model.addAttribute("data", jsonObj.toString());
		return "post";
	}
	
	@RequestMapping(value="/great_news")
	public String getGreatNews(Model model) {
		logger.info("welcome to great news page!");
		List<NewsForm> greatNewsList = newsService.queryGreatNews();
		model.addAttribute("greatNews", greatNewsList);
		return "great_news";
	}
	
	@RequestMapping(value="/find_news")
	public String findNews(Integer newsType,Model model) {
		logger.info("welcome to find news page!");
		NewsTypeEnum newsTypeEnum = NewsTypeEnum.getByCode(newsType);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("moduleH", newsTypeEnum.getMessage());
		jsonObj.put("totalNo", newsService.findNewsByType(newsTypeEnum.code()));
		jsonObj.put("cPage", 0);
		jsonObj.put("perNo", 15);
		model.addAttribute("data", jsonObj.toString());
		model.addAllAttributes(newsService.getActivityNews());
		return "module";
	}
	
	@RequestMapping(value="/find_news_data")
	@ResponseBody
	public Object findNewsData(String moduleH,Integer page,Model model) {
		logger.info("welcome to find news page!moduleH=" + moduleH);
		int pageSize = 15;
		if(null == page) {
			page = 0;
		}
		List<NewsForm> newsList = newsService.queryNews(NewsTypeEnum.getByMessage(moduleH).code(), page, pageSize);
		return newsList;
	}
	
	@RequestMapping(value="/search_news")
	public String findNewsByLabel(HttpServletRequest request,HttpServletResponse response,String searchText,Model model) {
		logger.info("welcome to find news by label!label=" + searchText);
		if(null == searchText || "".equals(searchText) || searchText.matches(FiledsConstant.SEARCH_REG_STR)) {
			try {
				response.sendRedirect("/cqeca/index");
//				request.getRequestDispatcher("/index").forward(request, response);
			}catch (Exception e) {
				logger.error("搜索新闻跳转首页错误",e);
			}
		}
		NewsTypeEnum newsTypeEnum = NewsTypeEnum.DYNAMIC_NEWS;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("moduleH", newsTypeEnum.getMessage());
		//搜索条数，需要一个搜索数据url请求
		jsonObj.put("totalNo", newsService.findNewsByType(newsTypeEnum.code()));
		jsonObj.put("cPage", 0);
		jsonObj.put("perNo", 15);
		model.addAttribute("data", jsonObj.toString());
		model.addAllAttributes(newsService.getActivityNews());
		return "module";
	}
}
