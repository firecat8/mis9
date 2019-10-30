/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports.config;

/**
 *
 * @author gdimitrova
 */
public class FontVo {

    private int colorId;
    private int size;

    public FontVo() {
    }

    public FontVo(int colorId, int size) {
        this.colorId = colorId;
        this.size = size;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
