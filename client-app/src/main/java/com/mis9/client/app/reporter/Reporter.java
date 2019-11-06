/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.client.app.reporter;

import java.io.FileOutputStream;
import javafx.scene.control.TableView;

/**
 *
 * @author gdimitrova
 */
public interface Reporter<S,R> {

    public void generateReport(FileOutputStream outputStream, TableView<S> table);

    public String getFileExtention();

    public String getReportType();
}
