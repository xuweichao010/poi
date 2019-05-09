package com.xwc.poi.anno;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  14:51
 * 功能：
 * 业务：
 */
@Target(ElementType.FIELD)  //只能使用在：类、接口、注解、枚举
@Retention(RetentionPolicy.RUNTIME)     //在运行时有效
@Documented
public @interface StringCell{
    /**
     * 单元格所在列
     * @return
     */
    int index() default -1;

    /**
     * 单元格标题 解析文件时无效
     * @return
     */
    String titel();

    /**
     * 是否是必须参数，在解析单元格的时候回用到 解析文件是有效
     * @return
     */
    boolean required() default false;

    /**
     * 设置单元格宽度 生成单元格有效
     * @return
     */
    int  width() default 10;

    /**
     * 校验数据的合法性 正则校验(解析时有效)
     */
    String regex() default "";

    /**
     * 校验失败的提示语(解析时有效)
     */
    String regexMessage() default  "数据格式错误";
}
