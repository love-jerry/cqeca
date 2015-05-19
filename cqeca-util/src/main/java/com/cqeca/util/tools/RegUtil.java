package com.cqeca.util.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @ClassName: RegUtil 
* @Description: 正则表达式工具类
* @author chenrui
* @date 2015年5月2日 上午12:56:08
 */
public class RegUtil {

	/**
	 * 匹配满足正则表达式的字符串集合
	 * @param str
	 * @param reg
	 * @return
	 */
	public static List<String> matchStr(String str,String reg){
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str); 
		List<String> strs = new ArrayList<String>();
		while(matcher.find()) {
			strs.add(matcher.group());
		}
		
		return strs;
	}
	
	/**
	 * 匹配满足正则表达式的字符串
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String matchChliedStr(String str,String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str); 
		while(matcher.find()) {
			return matcher.group();
		}
		
		return null;
	}
	
	/**
	 * 转义正则特殊字符 （$()*+.[]?\^{},|）
	 * 
	 * @param keyword
	 * @return
	 */
	public static String escapeExprSpecialWord(String keyword) {
		if (null != keyword && !"".equals(keyword)) {
			String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
			for (String key : fbsArr) {
				if (keyword.contains(key)) {
					keyword = keyword.replace(key, "\\" + key);
				}
			}
		}
		return keyword;
	}
}
