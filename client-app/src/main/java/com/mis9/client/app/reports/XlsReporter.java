/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import com.mis9.client.app.reports.config.ReportCfgVo;
import com.mis9.client.app.reports.config.TripletVo;

/**
 *
 * @author gdimitrova
 */
public class XlsReporter extends AbstractExcelReporter<HSSFColor, HSSFFont> {

    private HSSFPalette palette;

    public XlsReporter(ReportCfgVo cfg) {
        super(new HSSFWorkbook(), cfg);
        HSSFWorkbook workbook = (HSSFWorkbook) wb;
        palette = workbook.getCustomPalette();
    }

    @Override
    protected Map<Integer, HSSFColor> exchangeColors(List<TripletVo> colors) {
        Map<Integer, HSSFColor> colorsMap = new HashMap();
        short step = 8;
        for (int i = 0; i < colors.size(); i++) {
            TripletVo color = colors.get(i);
            palette.setColorAtIndex((short) (i + step),
                    (byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
            colorsMap.put(i, palette.getColor(i + step));
        }
        return colorsMap;
    }

    @Override
    protected short getColorIndex(int colorId) {
        return colors.get(colorId).getIndex();
    }

    @Override
    protected void setFillForegroundColor(CellStyle style, int colorId) {
        style.setFillForegroundColor(getColorIndex(colorId));
    }

    @Override
    protected void setFontColor(Font font, int colorId) {
        font.setColor(getColorIndex(colorId));
    }

    @Override
    protected void setBorderColor(CellStyle style, int colorId) {
        short colorIndex = getColorIndex(colorId);
        style.setBottomBorderColor(colorIndex);
        style.setLeftBorderColor(colorIndex);
        style.setRightBorderColor(colorIndex);
        style.setTopBorderColor(colorIndex);
    }

    @Override
    protected void autoSizeColumns(Sheet sheet, int columnsCount) {
        int width = 40 * 250;
        for (int i = 0; i < columnsCount; i++) {
            sheet.setColumnWidth(i, width);
        }
    }

}
