/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports;

import com.mis9.client.app.reports.config.CellVo;
import com.mis9.client.app.reports.config.DoubleValueCellVo;
import com.mis9.client.app.reports.config.ReportCfgVo;
import com.mis9.client.app.reports.config.StringValueCellVo;
import com.mis9.client.app.reports.config.TableDataVo;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import com.mis9.client.app.reports.Reporter;

/**
 *
 * @author gdimitrova
 */
public abstract class AbstractReporter implements Reporter {

    protected ReportCfgVo cfg;

    public AbstractReporter(ReportCfgVo cfg) {
        this.cfg = cfg;
    }

    protected Map<Integer, Map<Integer, CellVo>> moveToMap(TableDataVo data) {
        Map<Integer, Map<Integer, CellVo>> cells = new HashMap<>();
        data.getDoubleValues().sort(Comparator.comparing(DoubleValueCellVo::getRowNumber));
        data.getStringValues().sort(Comparator.comparing(StringValueCellVo::getRowNumber));
        data.getDoubleValues().forEach((val) -> {
            setCell(cells, val);
        });
        data.getStringValues().forEach((val) -> {
            setCell(cells, val);
        });
        return cells;
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
