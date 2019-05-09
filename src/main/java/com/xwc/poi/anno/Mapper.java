package com.xwc.poi.anno;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/29  15:14
 * 功能：
 * 业务：
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface Mapper {
    int key();
    String val();
}
