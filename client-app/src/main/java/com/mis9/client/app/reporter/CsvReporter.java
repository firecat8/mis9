/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reporter;

import com.mis9.client.app.reports.config.CellVo;
import com.mis9.client.app.reports.config.DelimiterVo;
import static com.mis9.client.app.reports.config.DelimiterVo.COMMA;
import static com.mis9.client.app.reports.config.DelimiterVo.SEMICOLON;
import com.mis9.client.app.reports.config.ReportCfgVo;
import com.mis9.client.app.reports.config.StringValueCellVo;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author gdimitrova
 */
public class CsvReporter<S> extends AbstractReporter<S, String> {

    public CsvReporter(BiFunction<Integer, S, String> writeRow) {
        super(writeRow);
    }

    public CsvReporter(ReportCfgVo cfg) {
        super(cfg);
    }

    @Override
    public void generateReport(FileOutputStream outputStream, TableView<S> table) {
        try {
            try (OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                Integer prevRowIndex = -1, rowI = 0;
                ObservableList<TableColumn<S, ?>> columns = table.getColumns();
                for (S row : table.getItems()) {
                    StringBuilder sb = new StringBuilder();
                    if (prevRowIndex + 1 < rowI) {
                        sb.append("\n".repeat(rowI - (prevRowIndex + 1)));
                    }
                    sb.append(writeRow.apply(rowI, row));
                    writer.write(sb.append(",").append("\n").toString());
                    rowI++;
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

    private Object getValue(Object val) {
        if (val == null) {
            return "";
        }
        if (val instanceof String) {
            String value = (String) val;
            return value.contains(",") ? "\"" + value + "\"" : value;
        }
        return val;
    }

    @Override
    public String getFileExtention() {
        return "*.csv";
    }

    @Override
    public String getReportType() {
        return "CSV files (*.csv)";
    }
}
