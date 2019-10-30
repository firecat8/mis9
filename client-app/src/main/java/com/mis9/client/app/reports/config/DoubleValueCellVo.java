/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports.config;

/**
 *
 * @author gdimitrova
 */
public class DoubleValueCellVo extends AbstractCellVo<Double> {

    private Double value;

    public DoubleValueCellVo() {
    }

    public DoubleValueCellVo(Double value, int rowNumber, int colNumber, int styleId) {
        super(rowNumber, colNumber, styleId);
        this.value = value;
    }
    
    @Override
    public Double getValue() {
        return value;
    }
    
    @Override
    public void setValue(Double value) {
        this.value = value;
    }
}
