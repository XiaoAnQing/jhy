package com.jhy.plateform.anno;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
  * 
  * @ClassName: ClassInfoAnno
  * @Company: 麦子科技
  * @Description: 类级别的注解
  * @author 技术部-刘强峰
  * @date 2016-3-11 上午11:07:28
  *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerAnno {
	String listUI() default "";                      //列表页面地址
	String addUI()  default "";                       //添加页面地址
	String editUI()  default "";                      //编辑页面地址
	String detailUI()  default "";                    //详情页面地址
}