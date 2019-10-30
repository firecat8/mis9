/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports.config;


/**
 *
 * @author gdimitrova
 */
public class ReportCfgVo {

    private String file;
    private DelimiterVo delimiter;

    public ReportCfgVo() {
    }

    public ReportCfgVo(String file, DelimiterVo delimiter) {
        this.file = file;
        this.delimiter = delimiter;
    }

    public DelimiterVo getDelimiter() {
        return delimiter;
    }

    public String getFile() {
        return file;
    }
}
