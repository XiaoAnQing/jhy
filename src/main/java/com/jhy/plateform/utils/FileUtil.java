package com.jhy.plateform.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
/**
 * 文件工具类
 * @author Administrator
 *
 */
public class FileUtil {
	private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 删除文件
	 * @param file
	 */
    public static void deleteFile(File file) {
        if (file.isFile()) {
            file.delete();
        } else {
            // 递归删除文件
            File[] files = file.listFiles();
            if(files.length==0){
                file.delete();
            }
            for (File tempFile : files) {
                deleteFile(tempFile);
            }
        }
    }
  
    /**
     * 上传文件
     * @param fileName           文件名字
     * @param baseFilePath       相对应应用的真实路径
     * @param extraPathFactor    额外路径因子(一般为用户的唯一登录名)
     * @return
     * @throws Exception
     */
	public static String uploadFile(InputStream is,String fileName,String baseFilePath,String extraPathFactor) {
		if(extraPathFactor==null){
			extraPathFactor="";  
		}  
		// 打散文件夹
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String extendFilePath = simpleDateFormat.format(new Date());
		File temp = new File(baseFilePath + File.separatorChar + extendFilePath+File.separatorChar+extraPathFactor);
		if (!temp.exists()) {
			temp.mkdirs();
		}
		
	
		String relativeSystemFilePath = extendFilePath + File.separatorChar + extraPathFactor + File.separatorChar + extendFilePath.replace("/","_")+"_"+UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
		String realFilePath = baseFilePath + File.separatorChar + relativeSystemFilePath;
		
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(realFilePath);
			byte[] tempByte = new byte[1024];
			int len = 0;
			while ((len = is.read(tempByte)) != -1) {
				os.write(tempByte, 0, len);
			}
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}finally{
			try {
				if (is != null) {
				is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
			}
		}
		// 直接在页面上显示用的路径
		return relativeSystemFilePath;
	}
	
	/**
	 * 根据UUID创建两个目录
	 * @param basePath
	 * @param uuid
	 */
	private static String createDirectory(String basePath,String uuid){
		int id = uuid.hashCode();
		int firstDirectory = id & 0xf;
		int sencodDirectory = id>>2  & 0xf;
		String result = basePath+File.separator+firstDirectory+File.separator+sencodDirectory;
		File file = new File(result);
		if(!file.exists()){
			file.mkdirs();
		}
		return result;
	}
	
	/**
	 * 根据UUID计算目录
	 * @param uuid
	 * @return
	 */
	public static String getPathByUUID(String uuid){
		int id = uuid.hashCode();
		int firstDirectory = id & 0xf;
		int sencodDirectory = id>>2  & 0xf;
		return File.separator+firstDirectory+File.separator+sencodDirectory;
	}
}
