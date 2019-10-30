/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports;

import com.mis9.client.app.reports.config.CellVo;
import com.mis9.client.app.reports.config.DelimiterVo;
import static com.mis9.client.app.reports.config.DelimiterVo.COMMA;
import static com.mis9.client.app.reports.config.DelimiterVo.SEMICOLON;
import com.mis9.client.app.reports.config.ReportCfgVo;
import com.mis9.client.app.reports.config.StringValueCellVo;
import com.mis9.client.app.reports.config.TableDataVo;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gdimitrova
 */
public class CsvReporter extends AbstractReporter {

    public CsvReporter(ReportCfgVo cfg) {
        super(cfg);
    }

    @Override
    public void generateReport(TableDataVo data) {
        try {
            try (OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream())) {
                Map<Integer, Map<Integer, CellVo>> cells = moveToMap(data);
                String separator = getSeparator(cfg.getDelimiter());
                String emptyCell = "".concat(separator);
                Integer prevRowIndex = -1;
                for (Map.Entry<Integer, Map<Integer, CellVo>> row : cells.entrySet()) {
                    Integer rowIndex = row.getKey();
                    Map<Integer, CellVo> values = row.getValue();
                    Integer prevColIndex = -1;
                    StringBuilder sb = new StringBuilder();
                    if (prevRowIndex + 1 < rowIndex) {
                        sb.append("\n".repeat(rowIndex - (prevRowIndex + 1)));
                    }
                    for (Map.Entry<Integer, CellVo> column : values.entrySet()) {
                        Integer colIndex = column.getKey();
                        if (prevColIndex + 1 < colIndex) {
                            sb.append(emptyCell.repeat(colIndex - (prevColIndex + 1)));
                        }
                        CellVo cellVo = column.getValue();
                        sb.append(
                                getValue(cellVo)
                        // cellVo.getValue() == null ? "" : cellVo.getValue()
                        ).append(separator);
                        prevColIndex = colIndex;
                    }
                    writer.write(sb.append(separator).append("\n").toString());
                    prevRowIndex = rowIndex;
                }
                writer.flush();
            }
        } catch (Exception ex) {
            Logger.getLogger(CsvReporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getSeparator(DelimiterVo delimiter) {
        switch (delimiter) {
            case COMMA:
                return ",";
            case SEMICOLON:
                return ";";
            default:
                throw new AssertionError(delimiter.name());
        }
    }

    private Object getValue(CellVo cellVo) {
        if (cellVo.getValue() == null) {
            return "";
        }
        if (cellVo instanceof StringValueCellVo) {
            String value = (String) cellVo.getValue();
            return value.contains(",") ? "\"" + value + "\"" : value;
        }
        return cellVo.getValue();
    }
}
