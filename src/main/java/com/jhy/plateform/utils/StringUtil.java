package com.jhy.plateform.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串工具类
 * @author Administrator
 *
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param param
	 * @return
	 */
	public static boolean isEmpty(String param){
		return param == null || "".equals(param);
	}

	/**
	 * 格式化日期
	 * @param date
	 * @param formate
	 * @return
	 */
	public static String formateDate(Date date, String formate){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isEmpty(formate) ? "yyyy-MM-dd" :formate);
		return simpleDateFormat.format(date);
	}
}
