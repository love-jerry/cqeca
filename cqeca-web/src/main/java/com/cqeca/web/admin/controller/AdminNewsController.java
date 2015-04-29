package com.cqeca.web.admin.controller;

import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqeca.service.login.session.SessionEntity;
import com.cqeca.service.news.NewsService;
import com.cqeca.util.constant.FiledsConstant;
import com.cqeca.util.enums.NewsTypeEnum;
import com.cqeca.util.tools.DateFormatUtil;
import com.cqeca.util.tools.DateUtil;
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

	@RequestMapping(value="/add_article")
	public String addNews() {
		return "add_article";
	}
	
//	@FilterCheckUrl(value = true)
	@RequestMapping(value="/publish",method=RequestMethod.POST)
	@ResponseBody
	public Object publishNews(String title,String content,String category,String tag, HttpServletRequest request,Model model) {
		logger.info("welcome to publish news page");
		
		logger.info("tile=" + title + ",category=" + category + ",tag=" + tag + ",content=" + content);
		HttpSession session = request.getSession();
		
//		SessionEntity sessionEntity = (SessionEntity)(session.getAttribute(FiledsConstant.SESSION_KEY));
//		String userId = sessionEntity.getUserId();
		String userId = "75870570-9d50-46ee-880f-b867133951d4";
		NewsTypeEnum newsType = NewsTypeEnum.getByMessage(category);
		//handle img
		
		
		
		newsService.saveNews(userId, newsType, title, content, tag);
		Map<String,String> result = new HashMap<String,String>();
		result.put("code", "200");
		return result;
		
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object deleteNews(String newsId) {
		newsService.deleteNewsById(newsId);
		Map<String,String> result = new HashMap<String,String>();
		result.put("code", "1");
		result.put("msg", "删除成功");
		return result;
	}
	
	@RequestMapping(value="/preview",method=RequestMethod.POST)
	public String deleteNews(String title,String content,String date,Model model) {
		logger.info("welcome to preview page");
		model.addAttribute("title", title);
		model.addAttribute("date", DateFormatUtil.dtSimpleFormat(Calendar.getInstance().getTime()));
		model.addAttribute("content", content);
		
		return "preview_article";
	}
}
