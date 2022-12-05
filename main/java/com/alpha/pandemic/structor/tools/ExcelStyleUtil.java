package com.alpha.pandemic.structor.tools;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.*;

@SuppressWarnings("unused")
public class ExcelStyleUtil implements IExcelExportStyler
{
    private static final short STRING_FORMAT = (short) BuiltinFormats.getBuiltinFormat("TEXT");
    private static final short FONT_SIZE_TEN = 10;
    private static final short FONT_SIZE_ELEVEN = 11;
    private static final short FONT_SIZE_TWELVE = 12;

    private CellStyle headerStyle;
    private CellStyle titleStyle;
    private CellStyle styles;

    public ExcelStyleUtil(Workbook workbook) {
        this.init(workbook);
    }


    private void init(Workbook workbook)
    {
        this.headerStyle = initHeaderStyle(workbook);
        this.titleStyle = initTitleStyle(workbook);
        this.styles = initStyles(workbook);
    }


    /**
     * @param color color
     * @return CellStyle
     */
    @Override
    public CellStyle getHeaderStyle(short color) {
        return headerStyle;
    }

    /**
     * @param color color
     * @return CellStyle
     */
    @Override
    public CellStyle getTitleStyle(short color) {
        return titleStyle;
    }

    @Override
    public CellStyle getStyles(boolean parity, ExcelExportEntity entity) {
        return styles;
    }

    /**
     * @param dataRow 数据行
     * @param obj     对象
     * @param data    数据
     */
    @Override
    public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data)
    {
        return getStyles(true, entity);
    }

    @Override
    public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams) {
        return null;
    }

    /**
     * @param workbook workbook
     * @return CellStyle
     */
    private CellStyle initHeaderStyle(Workbook workbook)
    {
        CellStyle style = getBaseCellStyle(workbook);
        style.setFont(getFont(workbook, FONT_SIZE_TWELVE, true));
        return style;
    }

    /**
     * @param workbook workbook
     * @return CellStyle
     */
    private CellStyle initTitleStyle(Workbook workbook)
    {
        CellStyle style = getBaseCellStyle(workbook);
        style.setFont(getFont(workbook, FONT_SIZE_ELEVEN, false));
        //background color
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    /**
     * @param workbook workbook
     * @return CellStyle
     */
    private CellStyle initStyles(Workbook workbook)
    {
        CellStyle style = getBaseCellStyle(workbook);
        style.setFont(getFont(workbook, FONT_SIZE_TEN, false));
        style.setDataFormat(STRING_FORMAT);
        return style;
    }

    /**
     * Borders
     * @return CellStyle
     */
    private CellStyle getBaseCellStyle(Workbook workbook)
    {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }

    /**
     * font size
     * @param size   字体大小
     * @param isBold 是否加粗
     * @return Font
     */
    private Font getFont(Workbook workbook, short size, boolean isBold)
    {
        Font font = workbook.createFont();
        font.setFontName("arial");
        font.setBold(isBold);
        font.setFontHeightInPoints(size);
        return font;
    }
}
