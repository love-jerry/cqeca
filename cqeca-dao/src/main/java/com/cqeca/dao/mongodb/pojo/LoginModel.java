package com.cqeca.dao.mongodb.pojo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @ClassName: LoginModel
 * @Description: 登陆表映射实体类
 * @author chenrui
 * @date 2015-4-7 下午5:52:19
 */
public class LoginModel {

	/**
	 * 登陆id
	 */
	private String id;

	/**
	 * 登陆用户id
	 */
	private String userId;

	/**
	 * 登陆时间
	 */
	private Date loginTime;

	/**
	 * 添加时间
	 */
	private Date insertTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
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

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
