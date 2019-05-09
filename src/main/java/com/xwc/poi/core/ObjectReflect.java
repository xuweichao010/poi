package com.xwc.poi.core;


import com.xwc.poi.model.JavaType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  20:15
 * 功能：
 * 业务：
 */
@SuppressWarnings("all")
public class ObjectReflect {
    private static final String SETTER = "set";
    private static final String GETTER = "get";

    public static JavaType getFieldType(Class<?> type) {
        if (Byte.class == type) {
            return JavaType.BYTE;
        } else if (Short.class == type) {
            return JavaType.SHORT;
        } else if (Integer.class == type) {
            return JavaType.INT;
        } else if (Long.class == type) {
            return JavaType.LONG;
        } else if (Double.class == type) {
            return JavaType.DOUBLE;
        } else if (Float.class == type) {
            return JavaType.FLOAT;
        } else if (Character.class == type) {
            return JavaType.CHAR;
        } else if (String.class == type) {
            return JavaType.STRING;
        } else if (Date.class == type) {
            return JavaType.DATE;
        } else if (Boolean.class == type) {
            return JavaType.BOOLEAN;
        } else if (type.isArray()) {
            return JavaType.ARRAY;
        } else if (Collection.class.isAssignableFrom(type)) {
            return JavaType.COLLECTION;
        } else {
            return JavaType.OTHER;
        }
    }

    public static <T> Method getter(Field field, Class<T> clazz) throws NoSuchMethodException {
        String fieldName = field.getName();
        String mthodeName = GETTER + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return clazz.getMethod(mthodeName);
    }

    public static <T> Method setter(Field field, Class<T> clazz) throws NoSuchMethodException {
        String fieldName = field.getName();
        String mthodeName = SETTER + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return clazz.getMethod(mthodeName, field.getType());
    }


}
