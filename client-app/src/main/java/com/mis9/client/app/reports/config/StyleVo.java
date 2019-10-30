/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports.config;

/**
 *
 * @author gdimitrova
 */
public class StyleVo {

    private int fontId;
    private int bgColorId;
    private String align;
    private String verticalAlign;
    private String numberFormat;
    private Boolean withBlackBorders;

    public StyleVo() {
    }

    public StyleVo(int fontId, int bgColorId, String align, String verticalAlign, String numberFormat, Boolean withBlackBorders) {
        this.fontId = fontId;
        this.bgColorId = bgColorId;
        this.align = align;
        this.verticalAlign = verticalAlign;
        this.numberFormat = numberFormat;
        this.withBlackBorders = withBlackBorders;
    }

    public int getFontId() {
        return fontId;
    }

    public int getBgColorId() {
        return bgColorId;
    }

    public String getAlign() {
        return align;
    }

    public String getVerticalAlign() {
        return verticalAlign;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public Boolean isWithBlackBorders() {
        return withBlackBorders;
    }

}
