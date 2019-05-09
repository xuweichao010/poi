package com.xwc.poi.core;

import com.xwc.poi.exception.MatchException;
import com.xwc.poi.exception.ValidationException;
import com.xwc.poi.model.CellMate;
import com.xwc.poi.model.DateMate;
import com.xwc.poi.model.JavaType;
import com.xwc.poi.model.NumberMate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/3  16:25
 * 功能：
 * 业务：
 */
@SuppressWarnings("all")
class CellUtils {

    public static Object getStringCellValue(Cell cell, CellMate mate) {
        cell.setCellType(Cell.CELL_TYPE_STRING);
        String value = cell.getStringCellValue();
        if (value == null || value.isEmpty()) return null;
        if (mate.getType() == JavaType.CHAR) {
            return value.charAt(0);
        } else if (mate.getType() == JavaType.STRING) {
            return value;
        } else {
            throw new ValidationException(mate.getType().getMsg() + ":该类型不属于字符类型");
        }
    }

    public static void setStringCellValue(Cell cell, Object value, CellMate mate) {
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(value.toString());
    }


    public static Object getNumberCellValue(Cell cell, CellMate mate) {
        if (null == cell) {
            return null;
        }
        NumberMate numberMate = (NumberMate) mate;
        Map<String, String> reversMappers = numberMate.getReversMappers();
        String value;
        if (null == reversMappers) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            Double d = cell.getNumericCellValue();
            value = (d == null ? null : d.toString());
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String valueStr = cell.getStringCellValue();
            if (valueStr == null) return null;
            value = reversMappers.get(valueStr);
            if (value == null) throw new MatchException(valueStr + "匹配类型失败");
            if (!numberMate.getAppend().isEmpty()) {
                value = value.replace(numberMate.getAppend(), "");
            }
        }
        switch (mate.getType()) {
            case BYTE:
                return new Byte(value);
            case SHORT:
                return new Short(value);
            case INT:
                return new Integer(value);
            case LONG:
                return new Long(value);
            case FLOAT:
                return new Float(value);
            case DOUBLE:
                return new Double(value);
            default:
                throw new ValidationException(mate.getType().getMsg() + ":该类型不属于数字类型");
        }


    }

    public static void setNumberCellValue(Cell cell, Object value, CellMate mate) {
        NumberMate numberMate = (NumberMate) mate;
        Map<String, String> map = numberMate.getMappers();
        if (null == map) {
            if (!numberMate.getFormat().isEmpty() || !numberMate.getFormat().isEmpty()) {
                String formatValue = value.toString();
                if (!numberMate.getFormat().isEmpty()) {
                    formatValue = String.format(numberMate.getFormat(), value);
                }
                if (!numberMate.getAppend().isEmpty()) {
                    formatValue += numberMate.getAppend();
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(formatValue);
            } else {
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(value.toString());
            }
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String content = map.get(value.toString());
            if (null == content) throw new MatchException(value.toString() + "匹配类型失败");
            cell.setCellValue(content);
        }
    }

    public static Date getDateCellValue(Cell cell, CellMate mate) {
        if (null == cell) {
            return null;
        }
        DateMate dateModel = (DateMate) mate;
        String format = dateModel.getFormat();
        Date date;
        if (DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String dateStr = cell.getStringCellValue();
            if (format.isEmpty()) {
                return new Date(new Long(dateStr));
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                try {
                    date = dateFormat.parse(dateStr);
                } catch (ParseException e) {
                    throw new RuntimeException("日期转换错误！需要的日期格式是：" + dateModel.getFormat() + "实际数据是：" + dateStr);
                }
                return date;
            }
        }
    }

    public static void setDateCellValue(Cell cell, Object value, CellMate model) {
        Date dateValue = (Date) value;
        DateMate cellModel = (DateMate) model;
        String format = cellModel.getFormat();
        if (DateUtil.isCellDateFormatted(cell)) {
            cell.setCellValue(dateValue);
        } else {

            if (format.isEmpty()) {
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(dateValue.getTime());
            } else {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                cell.setCellValue(dateFormat.format(dateValue));
            }

        }

    }
}
