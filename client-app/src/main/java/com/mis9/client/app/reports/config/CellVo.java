/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports.config;

/**
 *
 * @author gdimitrova
 */
public interface CellVo<T> {

    public T getValue();

    public void setValue(T value);

    public int getRowNumber();

    public int getColNumber();

    public void setRowNumber(int row);

    public void setColNumber(int col);

    public void setStyleId(int styleId);

    public int getStyleId();

    public boolean match(int row, int column);
}
