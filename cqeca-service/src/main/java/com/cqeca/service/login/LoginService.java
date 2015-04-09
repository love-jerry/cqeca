package com.cqeca.service.login;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqeca.dao.mongodb.LoginDao;
import com.cqeca.dao.mongodb.UserDao;
import com.cqeca.dao.mongodb.pojo.LoginModel;
import com.cqeca.dao.mongodb.pojo.UserModel;
import com.cqeca.service.login.session.SessionEntity;
import com.cqeca.util.tools.UUIDUtil;

/**
* @ClassName: LoginService 
* @Description:后台管理用户登录服务 
* @author chenrui
* @date 2015-4-8 上午10:00:05
 */
@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private UserDao userDao;
	
	private final static Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Transactional
	public SessionEntity loginIn(String userName,String password) throws Exception {
		logger.info("进入【用户登录】方法，入参[userName=" + userName + "]");
		Criteria criteria =  Criteria.where("userName").is(userName).and("password").is(password);
		Query query = new Query(criteria);
		UserModel userModel = userDao.find(query);
		if(null == userModel) {
			throw new Exception("用户名/密码错误");
		}
		Date nowDate = Calendar.getInstance().getTime();
		LoginModel loginModel = new LoginModel();
		loginModel.setId(UUIDUtil.uuid());
		loginModel.setUserId(userModel.getUserId());
		loginModel.setLoginTime(nowDate);
		loginModel.setInsertTime(nowDate);
		loginModel.setUpdateTime(nowDate);
		
		loginDao.save(loginModel);
		
		SessionEntity sessionEntity = new SessionEntity();
		sessionEntity.setSessionId(UUIDUtil.uuid());
		sessionEntity.setUserId(userModel.getUserId());
		sessionEntity.setUserName(userName);
		sessionEntity.setNickName(userModel.getNickName());
		sessionEntity.setLoginDate(nowDate);
		
		logger.info("退出【用户登录】方法");
		return sessionEntity;
	}
}
