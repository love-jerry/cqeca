package com.cqeca.util.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: NewsTypeEnum
 * @Description:新闻类型枚举
 * @author chenrui
 * @date 2015-4-1 下午4:29:18
 */
public enum NewsTypeEnum {

	// 动态
	DYNAMIC_NEWS(1, "业内动态"),
	
	// 活动
	ACTIVITY_NEWS(2, "协会活动"),

	// 公告
	NOTICE_NEWS(3, "协会会刊"),
	
	// 其他
	OTHER_NEWS(4, "其它信息"),
	
	// 其他
	GREAT_NEWS(5, "协会大事记");

	/** 枚举值 */
	private final int code;

	/** 枚举描述 */
	private final String message;

	/**
	 * 构造一个枚举对象
	 * 
	 * @param code
	 * @param message
	 */
	private NewsTypeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @return Returns the code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return Returns the code.
	 */
	public int code() {
		return code;
	}

	/**
	 * @return Returns the message.
	 */
	public String message() {
		return message;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return NewsTypeEnum
	 */
	public static NewsTypeEnum getByCode(int code) {
		for (NewsTypeEnum _enum : values()) {
			if (_enum.getCode() == code) {
				return _enum;
			}
		}
		return null;
	}

	/**
	 * 通过枚举<code>message</code>获得枚举
	 * 
	 * @param message
	 * @return NewsTypeEnum
	 */
	public static NewsTypeEnum getByMessage(String message) {
		for (NewsTypeEnum _enum : values()) {
			if (_enum.getMessage().equals(message)) {
				return _enum;
			}
		}
		return null;
	}

	/**
	 * 获取全部枚举
	 * 
	 * @return List<NewsTypeEnum>
	 */
	public List<NewsTypeEnum> getAllEnum() {
		List<NewsTypeEnum> list = new ArrayList<NewsTypeEnum>();
		for (NewsTypeEnum _enum : values()) {
			list.add(_enum);
		}
		return list;
	}

	/**
	 * 获取全部枚举值
	 * 
	 * @return List<String>
	 */
	public List<Integer> getAllEnumCode() {
		List<Integer> list = new ArrayList<Integer>();
		for (NewsTypeEnum _enum : values()) {
			list.add(_enum.code());
		}
		return list;
	}

}
