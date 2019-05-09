package com.xwc.poi.core;

import com.xwc.poi.anno.*;
import com.xwc.poi.model.DateMate;
import com.xwc.poi.model.NumberMate;
import com.xwc.poi.model.SheetMate;
import com.xwc.poi.model.StringMate;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  16:23
 * 功能：对工具类中的注解提供一个解析方法
 * 业务：POI工具类
 */
public class POIAnnotation {

    /**
     * 把SheeX注解的内容封装到一个对象中
     *
     * @param sheetX  注解对象
     * @return 注解信息
     */
    public static SheetMate getSheetXContent(SheetX sheetX) {
        SheetMate model = new SheetMate();
        model.setValidate(sheetX.validate());
        model.setShowTitle(sheetX.showTitle());
        model.setSkipRow(sheetX.skipRow());
        model.setExt(sheetX.ext());
        model.setFontHeight(sheetX.fontHeight());
        model.setFontName(sheetX.fontName());
        model.setAlignment(sheetX.alignment());
        model.setVerticalAlignment(sheetX.verticalAlignment());
        model.setWrapText(sheetX.wrapText());
        return model;
    }

    /**
     * 把StringCell注解的内容封装到一个对象中
     * @param cell 注解对象
     * @return 注解信息
     */
    static StringMate getCellContent(StringCell cell) {
        StringMate model = new StringMate();
        model.setIndex(cell.index());
        model.setRequired(cell.required());
        model.setTitel(cell.titel());
        model.setWidth(cell.width());
        if(!cell.regex().isEmpty()){
            model.setPattern(Pattern.compile(cell.regex()));
        }
        model.setRegexMessage(cell.regexMessage());
        return model;
    }

    /**
     * 把StringCell注解的内容封装到一个对象中
     * @param cell 注解对象
     * @return 注解信息
     */
    static DateMate getCellContent(DateCell cell) {
        DateMate model = new DateMate();
        model.setIndex(cell.index());
        model.setRequired(cell.required());
        model.setTitel(cell.titel());
        model.setWidth(cell.width());
        model.setFormat(cell.format());
        return model;
    }

    /**
     * 把NumberCell注解的内容封装到一个对象中
     * @param cell 注解对象
     * @return 注解信息
     */
    static NumberMate getCellContent(NumberCell cell) {
        NumberMate model = new NumberMate();
        model.setIndex(cell.index());
        model.setRequired(cell.required());
        model.setTitel(cell.titel());
        model.setWidth(cell.width());
        model.setAppend(cell.append());
        model.setFormat(cell.format());
        Mapper[] mappers = cell.mapper();
        if (mappers.length > 0) {
            HashMap<String, String> map = new HashMap<>();
            HashMap<String, String> reversMap = new HashMap<>();
            for (Mapper mapper : mappers) {
                map.put(String.valueOf(mapper.key()), mapper.val());
                reversMap.put(mapper.val(),String.valueOf(mapper.key()));
            }
            model.setMappers(map);
            model.setReversMappers(reversMap);
        }
        if(!cell.regex().isEmpty()){
            model.setPattern(Pattern.compile(cell.regex()));
        }
        model.setRegexMessage(cell.regexMessage());
        return model;
    }

}
