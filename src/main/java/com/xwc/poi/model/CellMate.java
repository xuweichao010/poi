package com.xwc.poi.model;

import java.util.regex.Pattern;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  14:51
 * 功能：单元格基类
 * 业务： POI工具类
 */
@SuppressWarnings("unused")
public class CellMate {
    //必须参数
    private boolean required;
    //宽度
    private int width;
    //标题
    private String titel;
    //所在列
    private int index;
    //数据校验正则
    private Pattern pattern;
    //校验失败提示消息
    private String regexMessage;
    //当前单元格数据类型
    private JavaType type;

    //自动生成序列号
    public void autoIndex(int index) {
        if (this.index == -1) {
            this.index = index;
        }
    }

    public JavaType getType() {
        return type;
    }

    public void setType(JavaType type) {
        this.type = type;
    }

    public String getRegexMessage() {
        return regexMessage;
    }

    public void setRegexMessage(String regexMessage) {
        this.regexMessage = regexMessage;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
