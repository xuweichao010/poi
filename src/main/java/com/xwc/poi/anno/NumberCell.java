package com.xwc.poi.anno;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  15:17
 * 功能：
 * 业务：
 */
@Target({ElementType.FIELD})  //只能使用在：类、接口、注解、枚举
@Retention(RetentionPolicy.RUNTIME)     //在运行时有效
@Documented
public @interface NumberCell {
    /**
     * 单元格所在列,默认是用对象变量定义的顺序，可以使用手动指定顺序，建议不要混合使用
     */
    int index() default -1;

    /**
     * 单元格标题
     */
    String titel();

    /**
     * 是否是必须参数，在解析单元格的时候回用到
     */
    boolean required() default false;

    /**
     * 设置单元格宽度
     */
    int  width() default 10;

    /**
     * 格式化小数点
     * @return
     */
    String format() default "";

    /**
     * 追加内容
     * @return
     */
    String append() default "";

    /**
     * 校验数据的合法性 正则校验(解析时有效)
     */
    String regex() default "";

    /**
     * 校验失败的提示语(解析时有效)
     */
    String regexMessage() default  "数据格式错误";

    /**
     * 映射  可以把Intger对象在Excel中显示字符串，必须预先声明类型
     */
    Mapper[] mapper() default {};


}
