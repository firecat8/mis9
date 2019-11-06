/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reporter.config;

import com.mis9.client.app.reports.config.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public class DefaultExcelConfig {

    public static DefaultExcelConfig instance = new DefaultExcelConfig();

    public final static int HEADER_ALIGN_CENTER = 0;

    public final static int TEXT_COLUMN = 1;

    public final static int NUMBER_COLUMN = 2;

    public final static int TEXT_COLUMN_ODD = 3;

    public final static int NUMBER_COLUMN_ODD = 4;

    public final static int BLACK_COLOR = 0;

    public final static int GREY_BORDER_COLOR = 6;

    private final String DEFAULT_NUMBER_FORMAT = "#,##0.0000";

    private final ExcelReportConfigFactory factory = ExcelReportConfigFactory.instance;

    private List<TripletVo> colors = new ArrayList<>();

    private List<FontVo> fonts = new ArrayList<>();

    private List<StyleVo> styles = new ArrayList<>();

    private DefaultExcelConfig() {
        addColors();
        addFonts();
        addStyles();
    }

    private void addColors() {
        // POI don't convert rgb(0,0,0) like black color for .xlsx files,
        // so it sets the closest acceptable rgb color for both Excel types
        // 0 = BLACK 
        colors.add(factory.makeTripletVo(2, 2, 2));
        // POI don't convert rgb(255,255,255) like white color for .xlsx files
        // so it sets the closest acceptable rgb color for both Excel types
        // 1 = WHITE / EVEN 
        colors.add(factory.makeTripletVo(254, 254, 254));
        // 2 = GREY LIGHT
        colors.add(factory.makeTripletVo(225, 225, 225));
        // 3 = GREY LIGHTER
        colors.add(factory.makeTripletVo(200, 200, 200));
    }

    private void addFonts() {
        // 0 = BLACK font
        fonts.add(factory.makeFontVo(0, 11));
    }

    private void addStyles() {
        // 0 = BLACK font with SIZE 11 and GREY LIGHT  background color
        styles.add(factory.makeStyleVo(0, 2, "ALIGN_CENTER", "VERTICAL_CENTER", null, true));
        // 1 = BLACK font with SIZE 11 and GREY LIGHT  background color
        styles.add(factory.makeStyleVo(0, 2, "ALIGN_CENTER", "VERTICAL_CENTER", null, true));
        // 2 = BLACK font with SIZE 10 and WHITE background color
        styles.add(factory.makeStyleVo(1, 1, "ALIGN_RIGHT", "VERTICAL_CENTER", DEFAULT_NUMBER_FORMAT, true));
        // 3 = BLACK font with SIZE 10 and ODD background color
        styles.add(factory.makeStyleVo(1, 2, "ALIGN_RIGHT", "VERTICAL_CENTER", DEFAULT_NUMBER_FORMAT, true));
        // 4 = BLACK font with SIZE 10 and WHITE background color
        styles.add(factory.makeStyleVo(1, 1, "ALIGN_LEFT", "VERTICAL_CENTER", null, true));
        // 5 = BLACK font with SIZE 10 and ODD background color
        styles.add(factory.makeStyleVo(1, 2, "ALIGN_LEFT", "VERTICAL_CENTER", null, true));
        // 6 = BLACK font with SIZE 10 and GREY LIGHT background color
        styles.add(factory.makeStyleVo(1, 4, "ALIGN_RIGHT", "VERTICAL_CENTER", DEFAULT_NUMBER_FORMAT, true));
        // 7 = BLACK font with SIZE 10 and GREY LIGHT background color
        styles.add(factory.makeStyleVo(1, 4, "ALIGN_LEFT", "VERTICAL_CENTER", null, true));
        // 8 = BLACK font with SIZE 10 and WHITE background color
        styles.add(factory.makeStyleVo(1, 1, "ALIGN_RIGHT", "VERTICAL_CENTER", null, false));
        // 9 = BLACK font with SIZE 10 and WHITE background color
        styles.add(factory.makeStyleVo(1, 1, "ALIGN_LEFT", "VERTICAL_CENTER", null, false));
    }

    public List<TripletVo> getColors() {
        return colors;
    }

    public List<FontVo> getFonts() {
        return fonts;
    }

    public List<StyleVo> getStyles() {
        return styles;
    }

}
