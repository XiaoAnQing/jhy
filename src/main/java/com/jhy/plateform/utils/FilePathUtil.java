package com.jhy.plateform.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
/**
 * 文件路径工具类
 * @author Administrator
 *
 */
public class FilePathUtil {
	private final static Logger logger = LoggerFactory.getLogger(FilePathUtil.class);
	
	private static Properties properties;
	static {
		properties = new Properties();
		InputStream is = FilePathUtil.class.getClassLoader().getResourceAsStream("filePath.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.toString());
				}
			}
		}
	}

	/**
	 * 
	 * @Description:    获取文件相对路径
	 * @param fileType  文件类别
	 * @return String     
	 */
	public static String fileRelativePath(String fileType){
	    return (String) properties.get(fileType);
	}
}