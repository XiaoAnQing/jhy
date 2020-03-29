package com.jhy.plateform.utils;
/**
 * 
  * @ClassName: StatusUtil
  * @Company: 麦子科技
  * @Description: 状态常量类
  * @author 技术部-刘强峰
  * @date 2016-2-22 下午2:25:14
  *
 */
public class StatusUtil {

    //订单的状态
    public static final byte STATUS_BOOK_WAITING=0;      //审核中
    public static final byte STATUS_BOOK_PASSED=1;       //审核通过
    public static final byte STATUS_BOOK_UNPASSED=2;     //审核未通过
    public static final byte STATUS_BOOK_EXCEPTION=3;    //异常  [生产异常]
    public static final byte STATUS_BOOK_FINISHED=4;     //已完成
    public static final byte STATUS_BOOK_INVALIDATED=5;  //无效[终止]

    //采购订单的状态
    public static final byte STATUS_PURCHASE_WAITING=0;      //审核中
    public static final byte STATUS_PURCHASE_PASSED=1;       //审核通过
    public static final byte STATUS_PURCHASE_UNPASSED=2;     //审核未通过
    public static final byte STATUS_PURCHASE_EXCEPTION=3;    //异常  [生产异常]
    public static final byte STATUS_PURCHASE_FINISHED=4;     //已完成
    public static final byte STATUS_PURCHASE_INVALIDATED=5;  //无效[终止]

}