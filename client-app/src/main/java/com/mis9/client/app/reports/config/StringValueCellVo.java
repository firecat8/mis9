/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports.config;

/**
 *
 * @author gdimitrova
 */
public class StringValueCellVo extends AbstractCellVo<String> {

    private String value;

    public StringValueCellVo() {
        super();
    }

    public StringValueCellVo(String value, int rowNumber, int colNumber, int styleId) {
        super(rowNumber, colNumber, styleId);
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
