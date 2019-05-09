package com.xwc.poi.model;

/**
 * 创建人：徐卫超
 * 创建时间：2018/10/16  11:34
 * 业务：
 * 功能：
 */
public enum JavaType {
    BYTE("字节"),
    SHORT("短整型"),
    INT("整数"),
    LONG("长整数"),
    DOUBLE("双精度小数"),
    FLOAT("小数"),
    DATE("时间"),
    CHAR("字符"),
    STRING("字符串"),
    BOOLEAN("布尔"),
    ARRAY("数组"),
    COLLECTION("数组集合"),
    MAP("集合"),
    OTHER("其他");

    private String msg;

    public String getMsg() {
        return msg;
    }


    JavaType(String msg) {
        this.msg = msg;
    }
}
