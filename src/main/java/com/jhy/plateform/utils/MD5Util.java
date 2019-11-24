package com.jhy.plateform.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	static char [] cache= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	
	public static String digest (String param){
		MessageDigest messageDigest=null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	 
		byte [] result = messageDigest.digest(param.getBytes());
		
		StringBuffer SB = new StringBuffer();
		for(int temp : result) {
			if(temp<0) {
				//负数 --->整数
				temp = temp+256;
			}
			
			int height = temp/16;
			int lower = temp%16;
			SB.append(cache[height]).append(cache[lower]);
		}
		return SB.toString();
	}
}
