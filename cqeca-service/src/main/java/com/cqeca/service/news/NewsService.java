package com.cqeca.service.news;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqeca.dao.mongodb.NewsDao;
import com.cqeca.dao.mongodb.pojo.NewsModel;
import com.cqeca.util.enums.NewsTypeEnum;
import com.cqeca.util.tools.UUIDUtil;

/**
* @ClassName: NewsService 
* @Description:新闻服务实现类 
* @author chenrui
* @date 2015-4-7 下午6:32:03
 */
@Service
public class NewsService {

	@Autowired
	private NewsDao newsDao;

	private final static Logger logger = LoggerFactory.getLogger(NewsService.class);
	
	/**
	 * 发布新闻方法
	 * @param userId
	 * @param newsType
	 * @param title
	 * @param content
	 */
	public void saveNews(String userId,NewsTypeEnum newsType,String title,String content) {
		logger.info("进入【发布新闻】方法，入参[userId=" + userId + ",newsType=" + newsType.getMessage() + ",title=" + title + "]");
		Date nowDate = Calendar.getInstance().getTime();
		NewsModel newsModel = new NewsModel();
		newsModel.setNewsId(UUIDUtil.uuid());
		newsModel.setTitle(title);
		newsModel.setNewsType(newsType.getCode());
		newsModel.setContent(content);
		newsModel.setCreatorId(userId);
		newsModel.setEditorId(userId);
		newsModel.setBrowseTimes(0);
		newsModel.setPublishTime(nowDate);
		newsModel.setInsertTime(nowDate);
		newsModel.setUpdateTime(nowDate);
		
		newsDao.insert(newsModel);
		logger.info("退出【发布新闻】方法");
	}
	
}
