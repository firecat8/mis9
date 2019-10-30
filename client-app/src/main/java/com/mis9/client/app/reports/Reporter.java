/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reports;

import com.mis9.client.app.reports.config.TableDataVo;

/**
 *
 * @author gdimitrova
 */
public interface Reporter {

    public void generateReport(TableDataVo data);
}
