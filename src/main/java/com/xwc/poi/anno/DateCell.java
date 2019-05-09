package com.xwc.poi.anno;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  15:17
 * 功能：
 * 业务：
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateCell {

    /**
     * 单元格所在列,默认是用对象变量定义的顺序，可以使用手动指定顺序，建议不要混合使用
     */
    int index() default -1;

    /**
     * 单元格标题
     *
     * @return
     */
    String titel();

    /**
     * 是否是必须参数，在解析单元格的时候回用到
     *
     * @return
     */
    boolean required() default false;

    /**
     * 设置单元格宽度
     *
     * @return
     */
    int width() default 10;

    /**
     * 指定日期格式(默认使用时间戳)  自定义格式时有效
     *
     * @return
     */
    String format() default "yyyy/MM/dd";
}
