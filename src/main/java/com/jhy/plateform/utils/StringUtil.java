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

	//导出文件名中文乱码处理
	public static String toUtf8String(String s){
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if (c >= 0 && c <= 255){sb.append(c);}
			else{
				byte[] b;
				try { b = Character.toString(c).getBytes("utf-8");}
				catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
