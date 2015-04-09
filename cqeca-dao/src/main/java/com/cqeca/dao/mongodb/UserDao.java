package com.cqeca.dao.mongodb;

import org.springframework.stereotype.Repository;

import com.cqeca.dao.mongodb.pojo.UserModel;

/**
* @ClassName: UserDao 
* @Description:用户表操作 
* @author chenrui
* @date 2015-4-7 下午6:03:03
 */
@Repository
public class UserDao extends BaseMongodbDao<UserModel>{

	/**
	 * 用户表名
	 */
	public final static String USER_TABLE_NAME = "user";
	
	@Override
	public Class<UserModel> getEntityClass() {
		return UserModel.class;
	}

	@Override
	public String getCollectionName() {
		return USER_TABLE_NAME;
	}

	
	
}
