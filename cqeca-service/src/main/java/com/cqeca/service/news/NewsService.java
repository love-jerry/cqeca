package com.cqeca.service.news;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.cqeca.service.news.form.NewsForm;
import com.cqeca.util.constant.FiledsConstant;
import com.cqeca.util.enums.NewsTypeEnum;
import com.cqeca.util.tools.DateFormatUtil;
import com.cqeca.util.tools.PageUtil;
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
		logger.info("进入【发布新闻】方法，入参[userId=" + userId + ",newsType=" + newsType + ",title=" + title + ",content=" + content + "]");
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
	public Map<String,String> findIndexNews() {
		List<NewsModel> activityNews = findNewsByType(NewsTypeEnum.ACTIVITY_NEWS,6);
		List<NewsModel> dynamicNews = findNewsByType(NewsTypeEnum.DYNAMIC_NEWS,8);
		List<NewsModel> noticeNews = findNewsByType(NewsTypeEnum.NOTICE_NEWS,8);
		List<NewsModel> otherNews = findNewsByType(NewsTypeEnum.OTHER_NEWS,8);
		
		//处理成视图需要属性字段
		Map<String,	String> newsMap = new HashMap<String,String>();
		
		Map<String,Object> activity_news_map = new HashMap<String,Object>();
		activity_news_map.put("boxH", "协会活动");
		activity_news_map.put("boxLink", "活动新闻");
		activity_news_map.put("list", JSONArray.fromObject(changeViewData(activityNews)));
		newsMap.put(FiledsConstant.ACTIVITY_NEWS_KEY, JSONObject.fromObject(activity_news_map).toString());
		
		Map<String,Object> dynamic_news_map = new HashMap<String,Object>();
		dynamic_news_map.put("boxH", "协会动态");
		dynamic_news_map.put("boxLink", "动态新闻");
		dynamic_news_map.put("list", JSONArray.fromObject(changeViewData(dynamicNews)));
		newsMap.put(FiledsConstant.DYNAMIC_NEWS_KEY, JSONObject.fromObject(dynamic_news_map).toString());
		
		Map<String,Object> notice_news_map = new HashMap<String,Object>();
		notice_news_map.put("boxH", "协会公告");
		notice_news_map.put("boxLink", "公告新闻");
		notice_news_map.put("list", JSONArray.fromObject(changeViewData(noticeNews)));
		newsMap.put(FiledsConstant.NOTICE_NEWS_KEY, JSONObject.fromObject(notice_news_map).toString());
		
		Map<String,Object> other_news_map = new HashMap<String,Object>();
		other_news_map.put("boxH", "其他新闻");
		other_news_map.put("boxLink", "其他新闻");
		other_news_map.put("list", JSONArray.fromObject(changeViewData(otherNews)));
		newsMap.put(FiledsConstant.OTHER_NEWS_KEY, JSONObject.fromObject(other_news_map).toString());
		
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
	
	/**
	 * 查询所有大事记新闻(默认查询1000条)
	 * @return
	 */
	public List<NewsForm> queryGreatNews() {
		List<NewsModel> greatNews = findNewsByType(NewsTypeEnum.OTHER_NEWS,1000);
		return changeViewData(greatNews);
	}
	/***
	 *  分页查询新闻
	 * @param newsType
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<NewsForm> queryNews(int newsType,int start,int pageSize) {
		List<NewsModel> greatNews = findNewsByPage(newsType,start,pageSize);
		return changeViewData(greatNews);
	}
	
	/**
	 * 根据标签查询新闻
	 * @param label
	 * @return
	 */
	public List<NewsForm> queryNewsByLabel(String label) {
		Pattern pattern = Pattern.compile("^label$", Pattern.CASE_INSENSITIVE);
		Criteria criteria =  Criteria.where("label").regex(pattern);
		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.DESC, "publishTime"));
		List<NewsModel> newsList =  newsDao.findMany(query);
		
		return changeViewData(newsList);
	}
	
	/**
	 * 查询上一条新闻
	 * @param type
	 * @param publishDate
	 * @return
	 */
	public NewsModel queryBeforeNews(int type,Date publishDate) {
		Criteria criteria =  Criteria.where("newsType").is(type).and("publishTime").gt(publishDate);
		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.ASC, "publishTime"));
		query.limit(1);
		return newsDao.find(query);
	}
	
	/**
	 * 查询下一条新闻
	 * @param type
	 * @param publishDate
	 * @return
	 */
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
	 * 根据类型分页查询新闻
	 * @param newsTypeEnum
	 * @param limite
	 * @return
	 */
	public List<NewsModel> findNewsByPage(int newsType,int start,int pageSize) {
		Criteria criteria =  Criteria.where("newsType").is(newsType);
		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.DESC, "publishTime"));
		query.skip((start-1)*pageSize);
		query.limit(pageSize);
		return newsDao.findMany(query);
	}

	/**
	 * 查询所有新闻，按发布时间排序
	 * @return
	 */
	public Map<String,Object> findAllNews(int start,int pageSize) {
		Criteria criteria = new Criteria();
		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.DESC, "publishTime"));
		query.skip((start-1)*pageSize);
		query.limit(pageSize);
		List<NewsModel> newsList = newsDao.findMany(query);
		JSONArray jsonArray = changeJsonData(newsList);
		List<NewsModel> all = newsDao.findAll();
		Map<String,Object> dataMap = new HashMap<String,Object>();

		dataMap.put("page", start);
		dataMap.put("pageSize", pageSize);
		dataMap.put("list", jsonArray.toString().replaceAll("\"", "'"));
		dataMap.put("totalPage", PageUtil.calulatPageCount(all.size(), pageSize));
		
		return dataMap;
	}
	
	/**
	 * 根据新闻id删除新闻
	 * @param newsId
	 */
	public void deleteNewsById(String newsId) {
		newsDao.remove(newsId);
	}
	
	/*
	 * 封装视图需要新闻字段
	 * @param news
	 * @return
	 */
	private List<NewsForm> changeViewData(List<NewsModel> news) {
		List<NewsForm> viewNews = new ArrayList<NewsForm>();
		NewsForm newsForm = null;
		for(NewsModel newsModel : news) {
			newsForm = new NewsForm();
			newsForm.setLink(FiledsConstant.NEWS_DETAIL_URL + newsModel.getNewsId());
			newsForm.setTitle(newsModel.getTitle());
			newsForm.setDate(DateFormatUtil.dtSimpleFormat(newsModel.getPublishTime()));
			viewNews.add(newsForm);
		}
		return viewNews;
	}
	
	/**
	 * chang json data
	 * @param news
	 * @return
	 */
	private JSONArray changeJsonData(List<NewsModel> news) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = null;
		for(NewsModel newsModel : news) {
			jsonObj = new JSONObject();
			jsonObj.accumulate("id", newsModel.getNewsId());
			jsonObj.accumulate("title", newsModel.getTitle());
			jsonObj.accumulate("cate", NewsTypeEnum.getByCode(newsModel.getNewsType()).getMessage());
			jsonObj.accumulate("date", DateFormatUtil.dtSimpleFormat(newsModel.getPublishTime()));
			jsonArray.add(jsonObj);
		}
		return jsonArray;
	}
	
	
}
