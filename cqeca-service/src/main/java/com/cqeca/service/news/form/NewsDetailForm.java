package com.cqeca.service.news.form;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @ClassName: NewsDetailForm
 * @Description:新闻详情展示字段
 * @author chenrui
 * @date 2015-4-1 下午4:18:01
 */
public class NewsDetailForm {

	/**
	 * 新闻链接
	 */
	private String link;

	/**
	 * 新闻标题
	 */
	private String title;

	/**
	 * 新闻内容
	 */
	private String content;

	/**
	 * 发布时间[年-月-日]
	 */
	private String date;

	/**
	 * 前一篇新闻链接
	 */
	private String beforeLink;

	/**
	 * 前一篇新闻标题
	 */
	private String beforeTitle;

	/**
	 * 后一篇新闻链接
	 */
	private String nextLink;

	/**
	 * 后一篇新闻标题
	 */
	private String nextTitle;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBeforeLink() {
		return beforeLink;
	}

	public void setBeforeLink(String beforeLink) {
		this.beforeLink = beforeLink;
	}

	public String getBeforeTitle() {
		return beforeTitle;
	}

	public void setBeforeTitle(String beforeTitle) {
		this.beforeTitle = beforeTitle;
	}

	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}

	public String getNextTitle() {
		return nextTitle;
	}

	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
