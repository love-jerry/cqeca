package com.cqeca.dao.mongodb;

import org.springframework.stereotype.Repository;

import com.cqeca.dao.mongodb.pojo.LoginModel;

/**
* @ClassName: LoginDao 
* @Description:登陆表操作 
* @author chenrui
* @date 2015-4-7 下午6:03:03
 */
@Repository
public class LoginDao extends BaseMongodbDao<LoginModel>{

	/**
	 * 登陆表名
	 */
	public final static String USER_LOGIN_TABLE_NAME = "login";
	
	@Override
	public Class<LoginModel> getEntityClass() {
		return LoginModel.class;
	}

	@Override
	public String getCollectionName() {
		return USER_LOGIN_TABLE_NAME;
	}
	
}
