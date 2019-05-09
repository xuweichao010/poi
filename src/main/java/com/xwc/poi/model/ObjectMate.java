package com.xwc.poi.model;

import java.lang.reflect.Method;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  20:15
 * 功能：
 * 业务：
 */
public class ObjectMate {
    private JavaType type;
    private Method setter;
    private Method getter;
    private CellMate cellMate;

    public JavaType getType() {
        return type;
    }

    public void setType(JavaType type) {
        this.type = type;
    }

    public CellMate getCellMate() {
        return cellMate;
    }

    public void setCellMate(CellMate cellMate) {
        this.cellMate = cellMate;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }
}
