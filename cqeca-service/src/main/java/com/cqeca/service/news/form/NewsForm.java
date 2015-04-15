package com.cqeca.service.news.form;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @ClassName: NewsForm
 * @Description:新闻页面展示字段
 * @author chenrui
 * @date 2015-4-1 下午4:18:01
 */
public class NewsForm {

	/**
	 * 新闻id
	 */
	private String link;

	/**
	 * 新闻标题
	 */
	private String title;

	/**
	 * 浏览次数
	 */
	private long browseTimes;

	/**
	 * 发布时间[年-月-日]
	 */
	private String date;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getBrowseTimes() {
		return browseTimes;
	}

	public void setBrowseTimes(long browseTimes) {
		this.browseTimes = browseTimes;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
