/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports.config;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public class TableDataVo {

    private List<DoubleValueCellVo> doubleValues = new ArrayList<>();
    private List<StringValueCellVo> stringValues = new ArrayList<>();

    public List<DoubleValueCellVo> getDoubleValues() {
        return doubleValues;
    }

    public void setDoubleValues(List<DoubleValueCellVo> doubleValues) {
        this.doubleValues = doubleValues;
    }

    public List<StringValueCellVo> getStringValues() {
        return stringValues;
    }

    public void setStringValues(List<StringValueCellVo> stringValues) {
        this.stringValues = stringValues;
    }

    public CellVo find(int row, int column) {
        for (DoubleValueCellVo cell : doubleValues) {
            if (cell.match(row, column)) {
                return cell;
            }
        }
        for (StringValueCellVo cell : stringValues) {
            if (cell.match(row, column)) {
                return cell;
            }
        }
        return null;
    }
}
