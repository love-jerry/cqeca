package com.cqeca.util.tools;
/***
* @ClassName: PageUtil 
* @Description: page
* @author chenrui
* @date 2015年4月29日 下午9:03:34
 */
public class PageUtil {

	public static int calulatPageCount(int size,int pageSize) {
		if(size % pageSize == 0) {
			return size / pageSize;
		} else {
			return size / pageSize + 1;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(calulatPageCount(55,5));
	}
}
