package com.xwc.poi.core;

import com.xwc.poi.anno.DateCell;
import com.xwc.poi.anno.NumberCell;
import com.xwc.poi.anno.StringCell;
import com.xwc.poi.exception.ValidationException;
import com.xwc.poi.exception.PoiException;
import com.xwc.poi.model.CellMate;
import com.xwc.poi.model.JavaType;
import com.xwc.poi.model.ObjectMate;
import com.xwc.poi.model.SheetMate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * 创建人：徐卫超
 * 创建时间：2018/10/24  10:05
 * 业务：
 * 功能：
 */
public class PoiCore {

    /**
     * 创建WorkBook
     *
     * @param sheet SheetModel
     * @return SheetModel
     */
    public static SheetMate createWorkBook(SheetMate sheet) {
        Workbook workbook;
        if (SheetMate.EXCEL_XLS.equals(sheet.getExt())) {
            workbook = new HSSFWorkbook();
        } else if (SheetMate.EXCEL_XLSX.equals(sheet.getExt())) {
            workbook = new XSSFWorkbook();
        } else {
            throw new RuntimeException("unknown file extension");
        }
        sheet.setWorkbook(workbook);
        return sheet;
    }

    /**
     * 读取一个WorkBook的文件输入流
     *
     * @param in       文件流
     * @param fileName 文件的名字
     * @return Workbook
     * @throws IOException 文件异常
     */
    public static Workbook readerWorkBook(final InputStream in, final String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(SheetMate.EXCEL_XLS)) {
            workbook = new HSSFWorkbook(in);
        } else if (fileName.endsWith(SheetMate.EXCEL_XLSX)) {
            workbook = new XSSFWorkbook(in);
        } else {
            throw new ValidationException("未知的文件扩展名" + fileName);
        }
        return workbook;
    }

    /**
     * 创建样式
     *
     * @param sheet 样式对象
     * @return 返回 SheetModel
     */
    public static SheetMate createCellStyle(SheetMate sheet) {
        Workbook workbook = sheet.getWorkbook();
        if (null == workbook) {
            throw new RuntimeException("create Style dependence WorkBook, WorkBook is null");
        }
        Font font = workbook.createFont();
        font.setFontHeight(sheet.getFontHeight());
        font.setFontName(sheet.getFontName());
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setWrapText(sheet.isWrapText());
        cellStyle.setAlignment(sheet.getAlignment());
        cellStyle.setVerticalAlignment(sheet.getVerticalAlignment());
        sheet.setCellStyle(cellStyle);
        return sheet;
    }

    public static Map<Integer, ObjectMate> getObjectModel(Class<?> clazz) throws NoSuchMethodException {
        Field[] fields = clazz.getDeclaredFields();
        Map<Integer, ObjectMate> map = new HashMap<>();
        int count = 0;
        for (Field field : fields) {
            JavaType fieldType = ObjectReflect.getFieldType(field.getType());
            CellMate mate;
            switch (fieldType) {
                case BYTE:
                case SHORT:
                case INT:
                case LONG:
                case DOUBLE:
                case FLOAT:
                    NumberCell num = field.getAnnotation(NumberCell.class);
                    if (null == num) continue;
                    mate = POIAnnotation.getCellContent(num);
                    break;
                case CHAR:
                case STRING:
                    StringCell str = field.getAnnotation(StringCell.class);
                    if (null == str) continue;
                    mate = POIAnnotation.getCellContent(str);
                    break;
                case DATE:
                    DateCell date = field.getAnnotation(DateCell.class);
                    if (null == date) continue;
                    mate = POIAnnotation.getCellContent(date);
                    break;
                default:
                    throw new PoiException("不支持的类型：" + field.getType().getName());
            }
            mate.setType(fieldType);
            mate.autoIndex(count);
            count++;
            ObjectMate objectMate = new ObjectMate();
            objectMate.setCellMate(mate);
            objectMate.setType(fieldType);
            objectMate.setGetter(ObjectReflect.getter(field, clazz));
            objectMate.setSetter(ObjectReflect.setter(field, clazz));
            map.put(mate.getIndex(), objectMate);
        }
        return map;
    }

    public static void generate(SheetMate sheetModel, Class<?> clazz, List<?> list) {
        Map<Integer, ObjectMate> map;
        try {
            map = PoiCore.getObjectModel(clazz);
        } catch (NoSuchMethodException e) {
            throw new PoiException(e);
        }
        Workbook workbook = sheetModel.getWorkbook();
        Sheet sheet = workbook.createSheet(sheetModel.getSheetName());
        sheetModel.setSheet(sheet);
        sheetModel.setMap(map);
        PoiCore.generateTitle(sheetModel);
        list.forEach(item -> PoiCore.generate(sheetModel, item));
    }

    public static void generate(SheetMate sheetModel, Object obj) {
        Sheet sheet = sheetModel.getSheet();
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        sheetModel.getMap().forEach((Integer index, ObjectMate model) -> {
            Cell cell = row.createCell(index);
            cell.setCellStyle(sheetModel.getCellStyle());
            Object value = null;
            try {
                value = model.getGetter().invoke(obj);
                if (value == null) return;
            } catch (Exception e) {
                e.printStackTrace();
            }
            CellMate cellModel = model.getCellMate();
            switch (model.getType()) {
                case BYTE:
                case SHORT:
                case INT:
                case LONG:
                case DOUBLE:
                case FLOAT:
                    CellUtils.setNumberCellValue(cell, value, cellModel);
                    break;
                case STRING:
                    CellUtils.setStringCellValue(cell, value, cellModel);
                    break;
                case DATE:
                    CellUtils.setDateCellValue(cell, value, cellModel);
                    break;
                default:
                    throw new ValidationException("类型错误");
            }
        });
    }

    public static void generateTitle(SheetMate sheetModel) {
        Sheet sheet = sheetModel.getSheet();
        Row row = sheet.createRow(0);
        if (sheetModel.isShowTitle()) {
            sheetModel.getMap().forEach((Integer index, ObjectMate model) -> {
                sheet.setColumnWidth(index, 256 * model.getCellMate().getWidth());
                Cell cell = row.createCell(index);
                cell.setCellStyle(sheetModel.getCellStyle());
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(model.getCellMate().getTitel());
            });
        }
    }

    public static <T> List<T> analysisSheet(SheetMate model, Class<T> clazz) {
        Map<Integer, ObjectMate> map = null;
        try {
            map = PoiCore.getObjectModel(clazz);
        } catch (NoSuchMethodException e) {
            throw new PoiException(e);
        }
        ArrayList<T> list = new ArrayList<>();
        int skip = model.getSkipRow();
        for (Row row : model.getSheet()) {
            if (skip > 0) {
                --skip;
                continue;
            }
            T obj = analysisRow(row, clazz, map);
            list.add(obj);
        }
        return list;
    }

    public static <T> T analysisRow(Row row, Class<T> clazz, Map<Integer, ObjectMate> map) {
        T t;
        try {
            t = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("实例化" + clazz.getName() + "对象失败，请检查构造函数");
        }
        int lineSize = row.getLastCellNum();
        map.forEach((Integer index, ObjectMate model) -> {
            CellMate mate = model.getCellMate();
            if (index < lineSize) {
                Object value;
                Cell cell = row.getCell(index);
                switch (mate.getType()) {
                    case BYTE:
                    case SHORT:
                    case INT:
                    case LONG:
                    case DOUBLE:
                    case FLOAT:
                        value = CellUtils.getNumberCellValue(cell, mate);
                        break;
                    case CHAR:
                    case STRING:
                        value = CellUtils.getStringCellValue(cell, mate);
                        break;
                    case DATE:
                        value = CellUtils.getDateCellValue(cell, mate);
                        break;
                    default:
                        throw new ValidationException("类型错误");
                }
                if (mate.isRequired() && null == value) {
                    throw new ValidationException("第" + (row.getRowNum() + 1) + "行," + mate.getTitel() + ":为必要填内容");
                }
                if (mate.getPattern() != null && value != null) {
                    Matcher matcher = mate.getPattern().matcher(value.toString());
                    boolean matches = matcher.matches();
                    if (!matches) {
                        throw new ValidationException("第" + (row.getRowNum() + 1) + "行," + mate.getTitel() + ":" + mate.getRegexMessage());
                    }
                }
                Method setter = model.getSetter();
                try {
                    setter.invoke(t, value);
                } catch (Exception e) {
                    throw new ValidationException("类型异常");
                }
            } else if (mate.isRequired()) {
                throw new ValidationException("第" + (row.getRowNum() + 1) + "行," + mate.getTitel() + ":内容不能为空");
            }

        });
        return t;
    }
}
