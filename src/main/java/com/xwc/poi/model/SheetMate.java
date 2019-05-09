package com.xwc.poi.model;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  16:18
 * 功能：存放样式文件和workBook格式对象
 * 业务：POI工具类
 */
public class SheetMate {

    public static final short ALIGN_CENTER = 2;  //左右对其
    public static final short VERTICAL_CENTER = 1;  //上下对其
    public static final String EXCEL_XLS = ".xls";
    public static final String EXCEL_XLSX = ".xlsx";

    /**
     * 校验文件名是否符合法
     */
    private String validate;
    /**
     * 是否生成标题栏
     */
    private boolean showTitle;

    /**
     * 跳过行数(默认一行)
     */
    private int skipRow;

    /**
     * 文件格式（默认格式：.xls）
     */
    private String ext;

    /**
     * 使用默认大小
     *
     * @return
     */
    private short fontHeight;

    /**
     * 使用默认字体
     */
    private String fontName;

    /**
     * 左右对齐方式（默认左右居中）
     */
    private short alignment;

    /**
     * 上下对齐方式（默认上下居中）
     */
    private short verticalAlignment;

    /**
     * 是否自动换行（默认不自动换行）
     */
    private boolean wrapText;

    /**
     * 样式
     */
    private CellStyle cellStyle;

    /**
     * Sheet
     * @return
     */
    private Sheet sheet;
    /**
     * 名字
     */
    private String sheetName;

    /**
     * WorkBook
     * @return
     */
    private Workbook workbook;

    private Map<Integer,ObjectMate> map;

    public Map<Integer, ObjectMate> getMap() {
        return map;
    }

    public void setMap(Map<Integer, ObjectMate> map) {
        this.map = map;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public int getSkipRow() {
        return skipRow;
    }

    public void setSkipRow(int skipRow) {
        this.skipRow = skipRow;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public short getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(short fontHeight) {
        this.fontHeight = fontHeight;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public short getAlignment() {
        return alignment;
    }

    public void setAlignment(short alignment) {
        this.alignment = alignment;
    }

    public short getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(short verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public boolean isWrapText() {
        return wrapText;
    }

    public void setWrapText(boolean wrapText) {
        this.wrapText = wrapText;
    }

    public boolean validation(String fileName) {
        if(validate.isEmpty()){
            return true;
        }else{
            return fileName.indexOf(validate)>-1?true:false;
        }
    }
}
