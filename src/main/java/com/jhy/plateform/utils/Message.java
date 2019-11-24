package com.jhy.plateform.utils;
/**
 * 
   * @ClassName: Message
   * @Company: 麦子科技
   * @Description: 消息类
   * @author 技术部-刘强峰
   * @date 2016年6月21日下午9:17:19
   *
 */
public class Message {

	/**
	 * 类型
	 */
	public enum Type {

		/** 成功 */
		success,

		/** 警告 */
		warn,

		/** 错误 */
		error
	}

	/** 类型 */
	private Type type;

	/** 内容 */
	private String content;

	/**
	 * 初始化一个新创建的 Message 对象，使其表示一个空消息。
	 */
	public Message() {

	}

    /**
     * 初始化一个新创建的 Message 对象
     * @param type
     * @param content
     */
	public Message(Type type, String content) {
		this.type = type;
		this.content = content;
	}

    /**
     * 构造方法
     * @param type
     * @param content
     * @param args
     */
	public Message(Type type, String content, Object... args) {
		this.type = type;
		this.content = content;
	}


	/**
	 * 返回成功消息
	 * @param content
	 * @param args
	 * @return
	 */
	public static Message success(String content, Object... args) {
		return new Message(Type.success, content, args);
	}

	/**
	 * 返回警告消息
	 * @param content
	 * @param args
	 * @return
	 */
	public static Message warn(String content, Object... args) {
		return new Message(Type.warn, content, args);
	}
    
	
	/**
	 * 返回错误消息
	 * @param content
	 * @param args
	 * @return
	 */
	public static Message error(String content, Object... args) {
		return new Message(Type.error, content, args);
	}

	public Type getType() {
		return type;
	}
 
	public void setType(Type type) {
		this.type = type;
	}

 
	public String getContent() {
		return content;
	}
 
	public void setContent(String content) {
		this.content = content;
	}
}