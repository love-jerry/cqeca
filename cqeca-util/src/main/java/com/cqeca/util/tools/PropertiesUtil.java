package com.cqeca.util.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @ClassName: PropertiesUtil
 * @Description: 配置文件读取工具类
 * @author chenrui
 * @date 2015年5月9日 上午8:58:04
 */
public class PropertiesUtil {
	
	private static Map<String,String> map = new TreeMap<String,String>();

	// 根据key读取value
	public static String readValue(String filePath, String key) {
		if(map.containsKey(key)) {
			return map.get(key);
		} else {
			Properties props = new Properties();
			try {
				InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
				props.load(in);
				String value = props.getProperty(key);
				System.out.println(key + value);
				map.put(key, value);
				return value;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	// 读取properties的全部信息
	public static void readProperties(String filePath) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				System.out.println(key + Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 写入properties信息
	public static void writeProperties(String filePath, String parameterName,
			String parameterValue) {
		Properties prop = new Properties();
		try {
			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(parameterName, parameterValue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "Update '" + parameterName + "' value");
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating "
					+ parameterName + " value error");
		}
	}

	public static void main(String[] args) {
		
		String url = readValue("config.properties", "net_base_url");
		System.out.println(url);
	}
}
