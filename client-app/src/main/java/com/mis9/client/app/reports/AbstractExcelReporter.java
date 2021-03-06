/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports;

import com.mis9.client.app.reports.config.CellVo;
import com.mis9.client.app.reports.config.DefaultExcelConfig;
import com.mis9.client.app.reports.config.FontVo;
import com.mis9.client.app.reports.config.ReportCfgVo;
import com.mis9.client.app.reports.config.StyleVo;
import com.mis9.client.app.reports.config.TableDataVo;
import com.mis9.client.app.reports.config.TripletVo;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author gdimitrova
 * @param <ColorType>
 * @param <FontType>
 */
public abstract class AbstractExcelReporter<ColorType, FontType> extends AbstractReporter {

    protected Map<Integer, ColorType> colors = new HashMap<>();
    protected Map<Integer, CellStyle> styles = new HashMap<>();
    protected Workbook wb;

    public AbstractExcelReporter(Workbook wb, ReportCfgVo cfg) {
        super(cfg);
        this.wb = wb;
    }

    @Override
    public void generateReport(TableDataVo data) {
        try {
            OutputStream out = null;

            writeExcelReport(data);
            wb.write(out);

        } catch (Exception ex) {
            Logger.getLogger(AbstractExcelReporter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void writeExcelReport(TableDataVo data) {
        int lastColumnIndex = 0;
        colors = exchangeColors(DefaultExcelConfig.instance.getColors());
        styles = exchangeStyles(DefaultExcelConfig.instance.getStyles());
        Sheet sheet = wb.createSheet();
        Map<Integer, Map<Integer, CellVo>> cells = moveToMap(data);
        for (Map.Entry<Integer, Map<Integer, CellVo>> row : cells.entrySet()) {
            Integer rowIndex = row.getKey();
            Row sRow = sheet.createRow(rowIndex);
            sRow.setHeight((short) 400);
            Map<Integer, CellVo> values = row.getValue();
            for (Map.Entry<Integer, CellVo> entry : values.entrySet()) {
                Integer colIndex = entry.getKey();
                CellVo cellVo = entry.getValue();
                Cell cell = sRow.createCell(colIndex);
                cell.setCellStyle(styles.get(cellVo.getStyleId()));
                lastColumnIndex = lastColumnIndex > colIndex ? lastColumnIndex : colIndex;
                if (cellVo.getValue() == null) {
                    cell.setCellValue("");
                    continue;
                }
                if (Number.class.isAssignableFrom(cellVo.getValue().getClass())) {
                    cell.setCellValue(Double.parseDouble(cellVo.getValue().toString()));
                    continue;
                }
                cell.setCellValue(cellVo.getValue().toString());
            }
        }
        autoSizeColumns(sheet, lastColumnIndex + 1);
    }

    public CellStyle makeStyle(StyleVo styleVo) {
        CellStyle style = wb.createCellStyle();
        int colorId = styleVo.getBgColorId();
        FontVo fontVo = DefaultExcelConfig.instance.getFonts().get(styleVo.getFontId());
        short fontColorIndex = getColorIndex(fontVo.getColorId());
        style.setFont(getFont("NewFont" + colorId, fontVo.getColorId(), fontColorIndex, fontVo.getSize()));
        setFillForegroundColor(style, colorId);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        setCellBorder(style, styleVo.isWithBlackBorders());
        style.setAlignment(getAlignment(styleVo.getAlign()));
        style.setVerticalAlignment(getVerticalAlignment(styleVo.getVerticalAlign()));
        if (styleVo.getNumberFormat() != null) {
            DataFormat numberDataFormat = wb.createDataFormat();
            short numFormat = numberDataFormat.getFormat(styleVo.getNumberFormat());
            style.setDataFormat(numFormat);
        }
        return style;
    }

    private VerticalAlignment getVerticalAlignment(String align) {
        switch (align) {
            case "VERTICAL_BOTTOM":
                return VerticalAlignment.BOTTOM;
            case "VERTICAL_CENTER":
                return VerticalAlignment.CENTER;
            case "VERTICAL_TOP":
                return VerticalAlignment.TOP;
            default:
                return VerticalAlignment.JUSTIFY;
        }
    }

    private void setCellBorder(CellStyle style, Boolean withBlackBorders) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        if (withBlackBorders) {
            setBorderColor(style, DefaultExcelConfig.BLACK_COLOR);
        } else {
            setBorderColor(style, DefaultExcelConfig.GREY_BORDER_COLOR);
        }
    }

    private Font getFont(String name, int colorId, short fontColorIndex, int sizeInPoints) {
        Font font = wb.findFont(false, fontColorIndex, (short) (sizeInPoints * 20),
                name, false, false, Font.SS_NONE, Font.U_NONE);

        if (font == null) {
            return makeFont(name, colorId, sizeInPoints);
        }
        return font;
    }

    private Font makeFont(String name, int colorId, int sizeInPoints) {
        Font font = wb.createFont();
        font.setBold(false);
        setFontColor(font, colorId);
        font.setFontHeightInPoints((short) sizeInPoints);
        font.setFontName(name);
        font.setItalic(false);
        font.setStrikeout(false);
        font.setTypeOffset(Font.SS_NONE);
        font.setUnderline(Font.U_NONE);
        return font;
    }

    private Map<Integer, CellStyle> exchangeStyles(List<StyleVo> styles) {
        Map<Integer, CellStyle> stylesMap = new HashMap<>();
        for (int i = 0; i < styles.size(); i++) {
            StyleVo style = styles.get(i);
            stylesMap.put(i, makeStyle(style));
        }
        return stylesMap;
    }

    private HorizontalAlignment getAlignment(String align) {
        switch (align) {
            case "ALIGN_LEFT":
                return HorizontalAlignment.LEFT;
            case "ALIGN_RIGHT":
                return HorizontalAlignment.RIGHT;
            case "ALIGN_CENTER":
                return HorizontalAlignment.CENTER;
            default:
                return HorizontalAlignment.JUSTIFY;
        }
    }

    protected abstract void setFillForegroundColor(CellStyle style, int colorId);

    protected abstract Map<Integer, ColorType> exchangeColors(List<TripletVo> colors);

    protected abstract short getColorIndex(int colorId);

    protected abstract void setFontColor(Font font, int colorId);

    protected abstract void setBorderColor(CellStyle style, int colorId);

    protected abstract void autoSizeColumns(Sheet sheet, int columnsCount);
}
