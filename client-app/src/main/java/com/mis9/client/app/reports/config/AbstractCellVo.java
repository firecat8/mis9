/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports.config;

/**
 *
 * @author gdimitrova
 */
public abstract class AbstractCellVo<T> implements CellVo<T> {

    private int rowNumber;
    private int colNumber;
    private int styleId;

    public AbstractCellVo() {
    }

    public AbstractCellVo(int rowNumber, int colNumber, int styleId) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.styleId = styleId;
    }

    @Override
    public int getRowNumber() {
        return rowNumber;
    }

    @Override
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Override
    public int getColNumber() {
        return colNumber;
    }

    @Override
    public void setColNumber(int colNumber) {
        this.colNumber = colNumber;
    }

    @Override
    public int getStyleId() {
        return styleId;
    }

    @Override
    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    @Override
    public boolean match(int row, int column) {
        return rowNumber == row && colNumber == column;
    }

}
