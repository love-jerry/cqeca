package com.cqeca.util.tools;

import java.util.UUID;

public class UUIDUtil {

	/**
	* @Title: uuid 
	* @Description: 获取一个默认生成的uuid
	* @@return String
	* @throws
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}
	
	/**
	* @Title: getNewUUId 
	* @Description: 获取一个把指定字符替换的uuid
	* @@param str
	* @@return String
	* @throws
	 */
	public static String getNewUUId(String uuid,char oldChar,char newChar) {
		return uuid.replace(oldChar, newChar);
	}
	
	/**
	* @Title: getNewUUId 
	* @Description: 获取一个把指定字符串替换的uuid
	* @@param str
	* @@return String
	* @throws
	 */
	public static String getNewUUId(String uuid,String oldStr,String newStr) {
		return uuid.replace(oldStr, newStr);
	}
	
	/**
	 * uuid带"-"
	 * @return
	 */
	public static String getUUId() {
		return uuid().replace("-", "");
	}
	
	public static void main(String[] args) {
		
		System.out.println(uuid());
		System.out.println(getUUId());
		
	}
}
