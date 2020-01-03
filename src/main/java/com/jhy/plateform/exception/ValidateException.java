package com.jhy.plateform.exception;

/**
 * 
  * @ClassName: ValidateException
  * @Company: 麦子科技
  * @Description: 自定义异常
  * @author 技术部-刘强峰
  * @date 2016年4月2日 下午5:19:30
  *
 */
public class ValidateException extends RuntimeException {

	// 异常信息
	public String message;

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 *
	 * @param message
	 */
	public ValidateException( String message) {
		super(message);
		this.message = message;
	}
}