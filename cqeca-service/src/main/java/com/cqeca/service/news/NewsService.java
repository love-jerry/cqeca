package com.cqeca.service.news;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cqeca.dao.mongodb.NewsDao;
import com.cqeca.dao.mongodb.pojo.NewsModel;
import com.cqeca.service.news.form.NewsDetailForm;
import com.cqeca.util.constant.FiledsConstant;
import com.cqeca.util.enums.NewsTypeEnum;
import com.cqeca.util.tools.DateFormatUtil;
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
	public void saveNews(String userId,NewsTypeEnum newsType,String title,String content,String label) {
		logger.info("进入【发布新闻】方法，入参[userId=" + userId + ",newsType=" + newsType.getMessage() + ",title=" + title + "]");
		Date nowDate = Calendar.getInstance().getTime();
		NewsModel newsModel = new NewsModel();
		newsModel.setNewsId(UUIDUtil.uuid());
		newsModel.setTitle(title);
		newsModel.setNewsType(newsType.getCode());
		newsModel.setContent(content);
		newsModel.setLabel(label);
		newsModel.setCreatorId(userId);
		newsModel.setEditorId(userId);
		newsModel.setBrowseTimes(0);
		newsModel.setPublishTime(nowDate);
		newsModel.setInsertTime(nowDate);
		newsModel.setUpdateTime(nowDate);
		
		newsDao.insert(newsModel);
		logger.info("退出【发布新闻】方法");
	}
	
	/**
	 * 首页新闻查询
	 * @return
	 */
	public Map<String,List<NewsDetailForm>> findIndexNews() {
		List<NewsModel> activityNews = findNewsByType(NewsTypeEnum.ACTIVITY_NEWS,6);
		List<NewsModel> dynamicNews = findNewsByType(NewsTypeEnum.DYNAMIC_NEWS,8);
		List<NewsModel> noticeNews = findNewsByType(NewsTypeEnum.NOTICE_NEWS,8);
		List<NewsModel> otherNews = findNewsByType(NewsTypeEnum.OTHER_NEWS,8);
		
		//处理成视图需要属性字段
		Map<String,List<NewsDetailForm>> newsMap = new HashMap<String,List<NewsDetailForm>>();
		newsMap.put(FiledsConstant.ACTIVITY_NEWS_KEY, changeViewData(activityNews));
		newsMap.put(FiledsConstant.DYNAMIC_NEWS_KEY, changeViewData(dynamicNews));
		newsMap.put(FiledsConstant.NOTICE_NEWS_KEY, changeViewData(noticeNews));
		newsMap.put(FiledsConstant.OTHER_NEWS_KEY, changeViewData(otherNews));
		
		return newsMap;
	}
	
	/**
	 * 查询新闻详情
	 * @param newsId
	 * @return
	 */
	public NewsDetailForm getNewsDetail(String newsId) {
		Criteria criteria =  Criteria.where("newsId").is(newsId);
		Query query = new Query(criteria);
		NewsModel currNews = newsDao.find(query);
		//更新浏览次数
		Update update = new Update();
		update.set("browseTimes", currNews.getBrowseTimes() +1);
		newsDao.updateFirst(query, update);
		//上一条新闻、下一条新闻
		int type = currNews.getNewsType();
		Date publishDate = currNews.getPublishTime();
		NewsModel beforeNews = queryBeforeNews(type,publishDate);
		NewsModel nextNews = queryNextNews(type,publishDate);
		
		//视图展示属性
		NewsDetailForm newsDetailForm = new NewsDetailForm();
		newsDetailForm.setTitle(currNews.getTitle());
		newsDetailForm.setDate(DateFormatUtil.dtSimpleFormat(currNews.getPublishTime()));
		newsDetailForm.setContent(currNews.getContent());
		if(null != beforeNews) {
			newsDetailForm.setBeforeLink(FiledsConstant.NEWS_DETAIL_URL + beforeNews.getNewsId());
			newsDetailForm.setBeforeTitle(beforeNews.getTitle());
		}
		if(null != nextNews) {
			newsDetailForm.setNextLink(FiledsConstant.NEWS_DETAIL_URL + nextNews.getNewsId());
			newsDetailForm.setNextTitle(nextNews.getTitle());
		}
		
		return newsDetailForm;
	}
	
	public NewsModel queryBeforeNews(int type,Date publishDate) {
		Criteria criteria =  Criteria.where("newsType").is(type).and("publishTime").gt(publishDate);
		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.ASC, "publishTime"));
		query.limit(1);
		return newsDao.find(query);
	}
	
	public NewsModel queryNextNews(int type,Date publishDate) {
		Criteria criteria =  Criteria.where("newsType").is(type).and("publishTime").lt(publishDate);
		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.DESC, "publishTime"));
		query.limit(1);
		return newsDao.find(query);
	}
	
	/**
	 * 根据类型查询新闻
	 * @param newsTypeEnum
	 * @param limite
	 * @return
	 */
	public List<NewsModel> findNewsByType(NewsTypeEnum newsTypeEnum,int limite) {
		Criteria criteria =  Criteria.where("newsType").is(newsTypeEnum.code());
		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.DESC, "publishTime"));
		query.limit(limite);
		return newsDao.findMany(query);
	}
	
	/**
	 * 封装视图需要新闻字段
	 * @param news
	 * @return
	 */
	private List<NewsDetailForm> changeViewData(List<NewsModel> news) {
		List<NewsDetailForm> viewNews = new ArrayList<NewsDetailForm>();
		NewsDetailForm newsForm = null;
		for(NewsModel newsModel : news) {
			newsForm = new NewsDetailForm();
			newsForm.setLink(FiledsConstant.NEWS_DETAIL_URL + newsModel.getNewsId());
			newsForm.setTitle(newsModel.getTitle());
			newsForm.setDate(DateFormatUtil.dtSimpleFormat(newsModel.getPublishTime()));
			viewNews.add(newsForm);
		}
		return viewNews;
	}
}
