package com.xwc.poi;


import com.xwc.poi.anno.SheetX;
import com.xwc.poi.core.POIAnnotation;
import com.xwc.poi.core.PoiCore;
import com.xwc.poi.exception.ValidationException;
import com.xwc.poi.model.SheetMate;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  17:54
 * 功能：POI工具类
 * 业务：POI
 */
public class POIUtils {
    /**
     * 解析excel文件
     * @param clazz 数据模型
     * @param in 文件流
     * @param fileName 文件名称
     * @param <T> 对象类型
     * @return 返回解析的数据集合
     * @throws IOException
     */
    public static <T> List<T> analysis(Class<T> clazz, InputStream in, String fileName) throws IOException {
        SheetX sheetX = clazz.getAnnotation(SheetX.class);
        if (null == sheetX) {
            throw new ValidationException("找不到@SheetX注解信息");
        }
        SheetMate sheetModel = POIAnnotation.getSheetXContent(sheetX);
        if (!sheetModel.validation(fileName)) {
            throw new ValidationException("文件名不能为空");
        }
        Workbook workbook = PoiCore.readerWorkBook(in, fileName);
        Sheet sheet = workbook.getSheetAt(0);
        sheetModel.setSheet(sheet);
        return PoiCore.analysisSheet(sheetModel, clazz);
    }

    /**
     *
     * 生成一个excel 文件
     * @param t 需要封装的excel数据
     * @param clazz 集合的泛型对象
     * @param sheetName 工作薄名称
     * @return Excel的WorkBook对象
     */
    public static Workbook generate(List<?> t, Class<?> clazz,String sheetName) {
        SheetX sheetX = clazz.getAnnotation(SheetX.class);
        if (null == sheetX) {
            throw new ValidationException("找不到@SheetX注解信息");
        }
        SheetMate sheetModel = POIAnnotation.getSheetXContent(sheetX);
        sheetModel.setSheetName(sheetName);
        PoiCore.createWorkBook(sheetModel);
        PoiCore.createCellStyle(sheetModel);
        PoiCore.generate(sheetModel,clazz,t);
        return sheetModel.getWorkbook();
    }

    /**
     * <pre>
     *     生成一个excel 前提条件是需要封装的excel数据不能为空
     * </pre>
     * @param t 需要封装的excel数据
     * @param sheetName 工作薄名称
     * @return Excel的WorkBook对象
     */
    public static Workbook generate(List<?> t,String sheetName) {
        if(t.isEmpty()){
            throw new ValidationException("集合数据为空");
        }
        Class<?> clazz = t.get(0).getClass();
        return generate(t,clazz,sheetName);
    }
}
