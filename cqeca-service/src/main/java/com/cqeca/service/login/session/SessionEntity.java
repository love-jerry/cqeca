package com.cqeca.service.login.session;

import java.util.Date;

/**
* @ClassName: SessionEntity 
* @Description:用户登录后session实体 
* @author chenrui
* @date 2015-4-8 上午10:46:41
 */
public class SessionEntity {

	private String sessionId;
	private String userId;
	private String userName;
	private String nickName;
	private Date loginDate;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
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
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	
}
