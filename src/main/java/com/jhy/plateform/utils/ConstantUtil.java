package com.jhy.plateform.utils;
/**
 * 
  * @ClassName: ConstantUtil
  * @Company: 麦子科技
  * @Description: 常量类
  * @author 技术部-刘强峰
  * @date 2016-2-22 下午2:25:14
  *
 */
public class ConstantUtil {

    //session中的key
    public static final String SESSION_KEY="user";

    // "验证结果"参数名称
    public static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";
    
    // "瞬时消息"属性名称 
    public static final String FLASH_MESSAGE_ATTRIBUTE_NAME = ".FLASH_MESSAGE";
    
    // 错误视图 
    public static final String ERROR_VIEW = "common/error";
    // 错误消息
    public static final Message ERROR_MESSAGE = Message.error("admin.message.error");
	 
	//系统繁忙提示
	public static final String SERVER_ERROR="系统繁忙,请稍后在!";
	
	//暂无数据提示
	public static final String NO_DATA_TIP="暂无数据";
	
	//默认密码
	public static final String DEFAULT_PASSWORD=MD5Util.digest("123456");

	public static final String CHARSET="UTF-8";

}