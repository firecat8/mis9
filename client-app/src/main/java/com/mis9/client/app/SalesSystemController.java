/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app;

import com.mis9.bl.ServiceProvider;
import com.mis9.domain.Item;
import com.mis9.domain.ItemCategory;
import com.mis9.domain.Size;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gdimitrova
 */
public class SalesSystemController implements Initializable {

    @FXML
    private AnchorPane productsPane;

    @FXML
    private Button products, reports, addCategoryBtn, addProductBtn;

    @FXML
    private ChoiceBox categoryChoiceBox, productChoiceBox, sizeChoiceBox;

    @FXML
    private TextField addCategoryTxt, productNameTxt, descriptionTxt, priceTxt, amountTxt;

    @FXML
    private TableColumn name, description, category, size, price, amount;

    @FXML
    private AnchorPane reportsPane;

    @FXML
    private ChoiceBox reportsChoice, formats;

    @FXML
    private DatePicker datePicker;

    private ServiceProvider svcProvider = ServiceProvider.INSTANCE;

    private Thread loadInfo;

    private int productsData = 0, reportsData = 0;

    private static final String SALE_BEFORE_DATE = "1. Sales before chosen date.";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setViewVisibility(true);
        reportsChoice.setItems(FXCollections.observableArrayList(
                SALE_BEFORE_DATE
        ));
        reportsChoice.setValue(SALE_BEFORE_DATE);
        datePicker.setValue(LocalDate.now());
        formats.setItems(FXCollections.observableArrayList(
                "xls", "xlsx", "csv"
        ));
        formats.setValue("xls");
        sizeChoiceBox.setItems(FXCollections.observableArrayList(
                Size.values()
        ));
        sizeChoiceBox.setValue(Size.NONE);
        loadInfo = new Thread() {
            @Override
            public void run() {
                try {
                    svcProvider.beginTransaction();
                    List<ItemCategory> categories = svcProvider.getItemCategoryDao().loadAll();
                    List<Item> items = svcProvider.getItemDao().loadByCategory(categories.get(0));
                    svcProvider.commit();
                } catch (Exception ex) {
                    Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
                    if (svcProvider.getTransaction() != null) {
                        svcProvider.rollback();
                    }
                }
            }
        };

    }

    @FXML
    private void onProductsButtonClicked(Event event) {
        System.out.println(event.getEventType().getName());
        setViewVisibility(true);
    }

    @FXML
    private void onReportsButtonClicked(Event event) {
        System.out.println(event.getEventType().getName());
        setViewVisibility(false);
    }

    private void setViewVisibility(boolean productsPaneVisible) {
        products.setStyle(productsPaneVisible ? "-fx-text-fill: red bold" : "-fx-text-fill: black");
        productsPane.setVisible(productsPaneVisible);
        productsPane.setManaged(productsPaneVisible);
        reports.setStyle(!productsPaneVisible ? "-fx-text-fill: red bold" : "-fx-text-fill: black");
        reportsPane.setVisible(!productsPaneVisible);
        reportsPane.setManaged(!productsPaneVisible);

    }

}
