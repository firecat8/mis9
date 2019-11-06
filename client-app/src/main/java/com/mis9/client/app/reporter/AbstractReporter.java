/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reporter;

import com.mis9.client.app.reports.config.CellVo;
import com.mis9.client.app.reports.config.DoubleValueCellVo;
import com.mis9.client.app.reports.config.ReportCfgVo;
import com.mis9.client.app.reports.config.StringValueCellVo;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import javafx.scene.control.TableView;

/**
 *
 * @author gdimitrova
 */
public abstract class AbstractReporter<S, R> implements Reporter<S, R> {

    protected ReportCfgVo cfg;

    protected BiFunction<Integer, S, R> writeRow;

    public AbstractReporter(BiFunction<Integer, S, R> writeRow) {
        this.writeRow = writeRow;
    }

    public AbstractReporter(ReportCfgVo cfg) {
        this.cfg = cfg;
    }

    protected void setCell(Map<Integer, Map<Integer, CellVo>> cells, CellVo cellVo) {
        Map<Integer, CellVo> rowValues = cells.get(cellVo.getRowNumber());
        if (rowValues == null) {
            rowValues = new HashMap<>();
            cells.put(cellVo.getRowNumber(), rowValues);
        }
        rowValues.put(cellVo.getColNumber(), cellVo);
    }
}
