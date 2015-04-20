package com.cqeca.service.user;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cqeca.dao.mongodb.UserDao;
import com.cqeca.dao.mongodb.pojo.UserModel;
import com.cqeca.util.tools.UUIDUtil;

/**
 * @ClassName: UserService
 * @Description:用户服务实现类
 * @author chenrui
 * @date 2015-4-7 下午6:13:46
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	private final static Logger logger = LoggerFactory.getLogger(UserService.class);

	/**
	 * 添加用户（admin用户sql初始化，其他用户由管理员创建）
	 * @param userName
	 * @param nickName
	 * @param password
	 */
	public void saveUser(String userName, String nickName, String password) {
		logger.info("进入【添加用户】方法，入参[userName=" + userName + ",nickName=" + nickName + "]");

		Date nowDate = Calendar.getInstance().getTime();
		UserModel userModel = new UserModel();
		userModel.setUserId(UUIDUtil.uuid());
		userModel.setUserName(userName);
		userModel.setNickName(nickName);
		userModel.setPassword(password);
		userModel.setInsertTime(nowDate);
		userModel.setUpdateTime(nowDate);

		userDao.insert(userModel);
		logger.info("退出【添加用户】方法");
	}
	
	/**
	 * 修改密码
	 * @param userName
	 * @param password
	 */
	public void updatePwd(String userName,String password) {
		Criteria criteria =  Criteria.where("userName").is(userName);
		Query query = new Query(criteria);
		//更新用户密码
		Update update = new Update();
		update.set("password", password);
		userDao.updateFirst(query, update);
	}
}
