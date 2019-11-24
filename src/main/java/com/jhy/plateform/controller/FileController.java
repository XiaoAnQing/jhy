package com.jhy.plateform.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jhy.plateform.utils.FilePathUtil;
import com.jhy.plateform.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * 
  * @ClassName: FileController
  * @Company: 麦子科技
  * @Description: 文件控制器
  * @author 技术部-刘强峰
  * @date 2016-2-1 下午4:36:20
  *
 */
@Controller
@RequestMapping("/files/")
public class FileController{
 
	private final static Logger logger = LoggerFactory.getLogger(FileController.class);
	/**
	 * 
	 * @Title: upload 
	 * @Description:    单个上传文件
	 * @param @param request
	 * @param @return     
	 * @return JsonModel     
	 * @throws
	 */
    @RequestMapping(value="upload",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> upload(@RequestParam(required = false,defaultValue="file") String paramName,@RequestParam(required = false,defaultValue="docFile") String fileType,HttpServletRequest request){
    	
    	String docPath = null;
		MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest)request;
		MultipartFile file = fileRequest.getFile(paramName);
		String baseFilePath =FilePathUtil.fileRelativePath(fileType);
		
		Map<String,Object> response = new HashMap<String,Object>();
		try {
			docPath = FileUtil.uploadFile(file.getInputStream(), file.getOriginalFilename(), baseFilePath,null);
			String fileName = docPath.substring(docPath.lastIndexOf(File.separatorChar));
			response.put("fileName", fileName);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("error","文件上传失败");
			response.put("errorkeys",new int[]{0});
		}
    	return response;
    }
    
    /**
     * 
     * @Title: delete 
     * @Description:    删除文件
     * @param @param request
     * @param @return     
     * @return JsonModel     
     * @throws
     */
    @RequestMapping(value="delete")
    public @ResponseBody Map<String,Object> delete(@RequestParam String key,@RequestParam(required = false,defaultValue="image") String fileType){
    	Map<String,Object> response = new HashMap<String,Object>();
    	int last_Index = key.lastIndexOf("_");
    	if(last_Index>0){
    		String baseFilePath = FilePathUtil.fileRelativePath(fileType);
    		String path = key.substring(0,last_Index);
    		path = path.replace("_","/");
    	    new File(baseFilePath+File.separatorChar+path+File.separatorChar+key).delete();
    	}else{
    		response.put("error","删除失败");
			response.put("errorkeys",new String[]{key});
    	}
    	return response;
    }
}