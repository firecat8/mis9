/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mis9.client.app.reports.config.ReportCfgVo;
import com.mis9.client.app.reports.config.TripletVo;

/**
 *
 * @author gdimitrova
 */
public class XlsxReporter extends AbstractExcelReporter<XSSFColor, XSSFFont> {

    public XlsxReporter(ReportCfgVo cfg) {
        super(new XSSFWorkbook(), cfg);
    }

    @Override
    protected Map<Integer, XSSFColor> exchangeColors(List<TripletVo> colors) {
        Map<Integer, XSSFColor> colorsMap = new HashMap<>();
        for (int i = 0; i < colors.size(); i++) {
            TripletVo c = colors.get(i);
            colorsMap.put(i, new XSSFColor(new Color(c.getRed(), c.getGreen(), c.getBlue())));
        }
        return colorsMap;
    }

    @Override
    protected short getColorIndex(int colorId) {
        return colors.get(colorId).getIndexed();
    }

    @Override
    protected void setFillForegroundColor(CellStyle style, int colorId) {
        XSSFCellStyle xssfStyle = (XSSFCellStyle) style;
        xssfStyle.setFillForegroundColor(colors.get(colorId));
    }

    @Override
    protected void setFontColor(Font font, int colorId) {
        XSSFFont xssfFont = (XSSFFont) font;
        xssfFont.setColor(colors.get(colorId));
    }

    @Override
    protected void setBorderColor(CellStyle style, int colorId) {
        XSSFCellStyle cellStyle = (XSSFCellStyle) style;
        XSSFColor color = colors.get(colorId);
        cellStyle.setTopBorderColor(color);
        cellStyle.setRightBorderColor(color);
        cellStyle.setBottomBorderColor(color);
        cellStyle.setLeftBorderColor(color);
    }

    @Override
    protected void autoSizeColumns(Sheet sheet, int columnsCount) {
        for (int i = 0; i < columnsCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}
