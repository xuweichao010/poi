package com.xwc.poi.model;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  14:56
 * 功能：
 * 业务：POI工具类
 */
public class DateMate extends CellMate {
    /**
     * 指定日期格式(默认使用POI时间方法)
     * @return
     */
    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
