package com.xwc.poi.model;

import java.util.Map;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  14:56
 * 功能：
 * 业务：POI工具类
 */
public class NumberMate extends CellMate {

    /**
     * 格式化小数点,请参照String.format规范
     */
    String format;

    /**
     * 追加内容
     */
    String append;

    private Map<String,String> mappers;

    private Map<String,String> reversMappers;

    public Map<String, String> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, String> mappers) {
        this.mappers = mappers;
    }
    public Map<String, String> getReversMappers() {
        return reversMappers;
    }

    public void setReversMappers(Map<String, String> reversMappers) {
        this.reversMappers = reversMappers;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAppend() {
        return append;
    }

    public void setAppend(String append) {
        this.append = append;
    }
}
