package com.cqeca.dao.mongodb.pojo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @ClassName: NewsModel
 * @Description:新闻表映射实体类
 * @author chenrui
 * @date 2015-4-1 下午4:18:01
 */
public class NewsModel {

	/**
	 * 新闻id
	 */
	private String newsId;

	/**
	 * 新闻标题
	 */
	private String title;

	/**
	 * 新闻内容
	 */
	private String content;

	/**
	 * 新闻类型（NewsTypeEnum）
	 */
	private int newsType;
	
	/**
	 * 浏览次数
	 */
	private long browseTimes;

	/**
	 * 发布时间
	 */
	private Date publishTime;

	/**
	 * 添加时间
	 */
	private Date insertTime;
	
	/**
	 * 创建者id
	 */
	private String creatorId;
	
	/**
	 * 更新者id
	 */
	private String editorId;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNewsType() {
		return newsType;
	}

	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}

	public long getBrowseTimes() {
		return browseTimes;
	}

	public void setBrowseTimes(long browseTimes) {
		this.browseTimes = browseTimes;
	}
	
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getEditorId() {
		return editorId;
	}

	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String  toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
}
