package com.cqeca.util.constant;

import com.cqeca.util.tools.PropertiesUtil;

/** 
* @ClassName: FiledsConstant 
* @Description:属性常量类 
* @author chenrui
* @date 2015-4-8 下午4:02:27
 */
public class FiledsConstant {

	//后台管理url前缀
	public static final String MANAGER_PREX_URL = "manager";
	
	//用户session保存key
	public static final String SESSION_KEY = "session_key";
	
	public static String BASE_URL = PropertiesUtil.readValue("/config.properties", "net_base_url");
//	public static String BASE_URL = "http://192.168.1.103:8090";
//	public static String BASE_URL = "http://125.85.120.28:8090";
//	public static String BASE_URL = "http://192.168.251.65:8090";
	
	public static final String NEWS_DETAIL_URL = BASE_URL + "/cqeca/news_detail?newsId=";
	
	public static final String MODULE_NEWS_DETAIL_URL = BASE_URL + "/cqeca/find_news?newsType=";
	
	public static final String ACTIVITY_NEWS_KEY = "dataLayer1";
	public static final String DYNAMIC_NEWS_KEY = "dataLayer2";
	public static final String NOTICE_NEWS_KEY = "dataLayer3";
	public static final String OTHER_NEWS_KEY = "dataLayer4";
	public static final String ACTIVITY_NEWS_PIC_KEY = "dataLayer5";
	
	public static final String UPLOAD_PERFIX_PATH = "/news/images/" ;
	public static final String DOWNLOAD_PERFIX_PATH = "/news/doc/" ;

	public static final String SEARCH_REG_STR = "([\u4e00-\u9fa5] |[0-9]|[a-z]|[A-Z]){1,20}";
	
	public static final String IMG_REG_STR = "<img.*?>";
	
	public static final String IMG_SRC_REG = "src=\".*?\"";

	public static final String IMG_SRC_REG_STR = "src=\"data:image.*?\"";
	
	public static final String IMG_TITLE_REG_STR = "title=\".*?\"";
	
	public static final int DEFAULT_PAGE_SIZE = 10;
	
}
