/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports.config;

/**
 *
 * @author gdimitrova
 */
public class ExcelReportConfigFactory {

    public static ExcelReportConfigFactory instance = new ExcelReportConfigFactory();

    private ExcelReportConfigFactory() {
    }

    public TripletVo makeTripletVo(int r, int g, int b) {
        return new TripletVo(r, g, b);
    }

    public FontVo makeFontVo(int colorId, int size) {
        return new FontVo(colorId, size);
    }

    public StyleVo makeStyleVo(int fontId, int bgColorId, String align, String verticalAlign, String numberFormat, Boolean withBlackBorders) {
        return new StyleVo(fontId, bgColorId, align, verticalAlign, numberFormat, withBlackBorders);
    }

}
