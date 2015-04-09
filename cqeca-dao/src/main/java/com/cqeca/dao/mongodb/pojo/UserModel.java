package com.cqeca.dao.mongodb.pojo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
* @ClassName: UserModel 
* @Description:用户表映射实体类 
* @author chenrui
* @date 2015-4-7 下午5:34:13
 */
public class UserModel {

	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 用户密码（MD5加密处理）
	 */
	private String password;
	
	/**
	 * 添加时间
	 */
	private Date insertTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public String  toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
}
