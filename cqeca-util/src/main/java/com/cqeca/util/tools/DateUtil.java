package com.cqeca.util.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
* @ClassName: DateUtil 
* @Description:日期，时间工具类 
* @author chenrui <chenrui@cstonline.cn> 
* @date 2014-4-23 下午5:23:09 
*
 */
public class DateUtil {

	/**
	 * 获取指定时间的前一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeforeDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取指定时间的后一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getAfterDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取一周前的日期(当前日期往前推7天)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWeekdayBeforeDate(Date date) {
		long beforeTime = (date.getTime() / 1000) - 24 * 60 * 60 * 7;
		date.setTime(beforeTime * 1000);
		return date;
	}

	/**
	 * 获取一周前的日期(当前日期往前推7天)
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekdayBeforeNow(Date date) {
		java.text.Format formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		long beforeTime = (date.getTime() / 1000) - 24 * 60 * 60 * 7;
		date.setTime(beforeTime * 1000);
		return formatter.format(date);
	}

	/**
	 * 获取一月前的日期(当前日期往前推7天)
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthdayBeforeNow(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		date = calendar.getTime();
		
		java.text.Format formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		return formatter.format(date);
	}	
	
	/**
	 * 返回日期时间
	 * 
	 * @param stringDate
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static final Date string2DateTimeByAutoZero(String stringDate) throws ParseException {
		if (stringDate == null) {
			return null;
		}
		if (stringDate.length() == 11)
			stringDate = stringDate + "00:00:00";
		else if (stringDate.length() == 13)
			stringDate = stringDate + ":00:00";
		else if (stringDate.length() == 16)
			stringDate = stringDate + ":00";
		else if (stringDate.length() == 10)
			stringDate = stringDate + " 00:00:00";
		return DateFormatUtil.getFormat(DateFormatUtil.simple).parse(stringDate);
	}
	
	/**
	 * 返回日期时间(时分秒：23:59:59)
	 * 
	 * @param stringDate String 型
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static final Date string2DateTimeBy23(String stringDate) throws ParseException {
		if (stringDate == null) {
			return null;
		}
		if (stringDate.length() == 11)
			stringDate = stringDate + "23:59:59";
		else if (stringDate.length() == 13)
			stringDate = stringDate + ":59:59";
		else if (stringDate.length() == 16)
			stringDate = stringDate + ":59";
		else if (stringDate.length() == 10)
			stringDate = stringDate + " 23:59:59";
		return DateFormatUtil.getFormat(DateFormatUtil.simple).parse(stringDate);
	}
	
	/**
	 * 计算日期差值
	 * 
	 * @param String
	 * @param String
	 * @return int（天数）
	 */
	public static final int calculateDecreaseDate(String beforDate, String afterDate)
																						throws ParseException {
		Date date1 = DateFormatUtil.getFormat(DateFormatUtil.dtSimple).parse(beforDate);
		Date date2 = DateFormatUtil.getFormat(DateFormatUtil.dtSimple).parse(afterDate);
		long decrease = getDateBetween(date1, date2) / 1000 / 3600 / 24;
		int dateDiff = (int) decrease;
		return dateDiff;
	}
	
	/**
	 * 计算时间差
	 * 
	 * @param dBefor 首日
	 * @param dAfter 尾日
	 * @return 时间差(毫秒)
	 */
	public static final long getDateBetween(Date dBefor, Date dAfter) {
		long lBefor = 0;
		long lAfter = 0;
		long lRtn = 0;
		/** 取得距离 1970年1月1日 00:00:00 GMT 的毫秒数 */
		lBefor = dBefor.getTime();
		lAfter = dAfter.getTime();
		lRtn = lAfter - lBefor;
		return lRtn;
	}
	
	
	public static Date getDayStart(long time) throws Exception {
		Date date = new Date(time);
		String str = DateFormatUtil.getFormat(DateFormatUtil.dtSimple).format(date);
		return DateFormatUtil.getFormat(DateFormatUtil.simple).parse(str + " 00:00:00");
	}
	
	public static Date getDayEnd(long time) throws Exception {
		Date date = new Date(time);
		String str = DateFormatUtil.getFormat(DateFormatUtil.dtSimple).format(date);
		return DateFormatUtil.getFormat(DateFormatUtil.simple).parse(str + " 23:59:59");
	}
	
	public static Date getMonthStart(long time) throws Exception {
		Date date = new Date(time);
		String str = DateFormatUtil.getFormat(DateFormatUtil.dtSimpleYm).format(date);
		return DateFormatUtil.getFormat(DateFormatUtil.simple).parse(str + "-01 00:00:00");
	}
	
	public static Date getMonthEnd(long time) throws Exception {
		Date date = new Date(time);
		String str = DateFormatUtil.getFormat(DateFormatUtil.dtSimpleYm).format(date);
		Calendar calendar = Calendar.getInstance();
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return DateFormatUtil.getFormat(DateFormatUtil.simple).parse(str + "-" + maxDay +" 23:59:59");
	}
	
	public static Date getTomorrowHour(Date date,int hour,int minute) {
		Date tomorrowDate = getAfterDay(date);
		Calendar c = Calendar.getInstance();
		c.setTime(tomorrowDate);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		return c.getTime();
	}
	
	/**
	 * 比较两个时间间隔是否大于给定毫秒数
	 * @param date1
	 * @param date2）
	 * @param miliseconds
	 * @return 1 大于,0 等于，-1小于
	 */
	public static int compareDate(Date date1,Date date2,long miliseconds) {
		long d1 = date1.getTime();
		long d2 = date2.getTime();
		
		long d = d1 -d2;
		
		if(d > miliseconds) {
			return 1;
		} else if(d == miliseconds) {
			return 0;
		} else {
			return -1;
		}
	}
	/**
	 * 指定时间推一年
	 * @param date
	 * @return
	 */
	public static Date getNextYearDate(Date date) {
		//
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		return calendar.getTime();
	}
	
	/**
	 * 指定时间推一个月
	 * @param date
	 * @return
	 */
	public static Date getNextMonthDate(Date date) {
		//
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间时分秒的秒数和
	 * @param date
	 * @return
	 */
	public static long getHMS(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		
		return (h * 60 + m)*60 + s;
	}

	/**
	 * 比较两个日期（转换成年月日后）大小
	 * @param d1
	 * @param d2
	 * @return 1 大于 0 等于 -1 小于
	 * @author
	 * @throws ParseException
	 */
	public static int compareDateByDay(Date d1, Date d2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = sdf.format(d1);
		String s2 = sdf.format(d2);
		if (s1.equals(s2)) { return 0; }
		Date date1 = sdf.parse(s1);
		Date date2 = sdf.parse(s2);
		if (date1.getTime() > date2.getTime()) { return 1; }
		return -1;
	}
	
	/**
	 * 返回两个时间间隔秒数绝对值
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getSeconds(Date date1,Date date2) {
		long d1 = date1.getTime();
		long d2 = date2.getTime();
		
		return (d2 - d1) / 1000; 
	}
	
	//获取没有时分秒时间
	public static Date getDateYMD(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}
	//获取指定时间当天最大时分秒时间
	public static Date getDateYMDhms(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间小时数
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	
	public static void main(String[] args) throws Exception {
//		System.out.println(DateUtil.getMonthEnd(System.currentTimeMillis()));
//		System.out.println(DateUtil.getAfterDay(new Date()));
//		
//		Date date = getAfterDay(new Date());
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.set(Calendar.HOUR_OF_DAY, 4);
//		System.out.println(c.getTime());
		
//		System.out.println(getNextYearDate(new Date()));
		
//		System.out.println( new Date((1411920000 +  getHMS(new Date())) * 1000L));
		
//		System.out.println(getDateYMDhms(new Date()));
		
		int date = Integer.valueOf("1396022400");
		date -=  DateUtil.getHMS(new Date(date*1000L));
		
		System.out.println(new Date(date * 1000L));
		System.out.println(new Date((date  + getHMS(new Date())) * 1000L));
		
	}
}
