package com.xwc.poi.anno;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  15:43
 * 功能：
 * 业务：
 */
@Target(ElementType.TYPE)  //只能使用在：类、接口、注解、枚举
@Retention(RetentionPolicy.RUNTIME)     //在运行时有效
@Documented
public @interface SheetX {

    short ALIGN_CENTER = 2;  //左右对其
    short VERTICAL_CENTER = 1;  //上下对其
    String EXCEL_XLS = ".xls";
    String EXCEL_XLSX = ".xlsx";

    /**
     * 校验文件名是否符合要求
     * @return
     */
    String validate() default "";
    /**
     * 是否生成标题栏
     *
     * @return
     */
    boolean showTitle() default true;

    /**
     * 跳过行数(默认一行,解析有效)
     *
     * @return
     */
    int skipRow() default 1;

    /**
     * 文件格式（默认格式：.xls）
     * @return
     */
    String ext() default EXCEL_XLS;

    /**
     * 使用默认大小
     * @return
     */
    short fontHeight() default 200;

    /**
     * 使用默认字体
     * @return
     */
    String fontName() default "Arial";

    /**
     * 左右对齐方式（默认左右居中）
     * @return
     */
    short alignment() default ALIGN_CENTER;

    /**
     * 上下对齐方式（默认上下居中）
     * @return
     */
    short verticalAlignment() default VERTICAL_CENTER;

    /**
     * 是否自动换行（默认不自动换行）
     * @return
     */
    boolean wrapText() default false;

}
