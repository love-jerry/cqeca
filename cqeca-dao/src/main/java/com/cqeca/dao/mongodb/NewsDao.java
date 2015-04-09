package com.cqeca.dao.mongodb;

import org.springframework.stereotype.Repository;

import com.cqeca.dao.mongodb.pojo.NewsModel;

/**
* @ClassName: NewsDao 
* @Description:新闻表操作 
* @author chenrui
* @date 2015-4-7 下午6:03:03
 */
@Repository
public class NewsDao extends BaseMongodbDao<NewsModel>{

	/**
	 * 新闻表名
	 */
	public final static String NEWS_TABLE_NAME = "news";
	
	@Override
	public Class<NewsModel> getEntityClass() {
		return NewsModel.class;
	}

	@Override
	public String getCollectionName() {
		return NEWS_TABLE_NAME;
	}
	
}
