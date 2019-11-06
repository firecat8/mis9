/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author gdimitrova
 */
public class PriceTextFieldListener implements ChangeListener<String> {

    private TextField priceTxt;

    public PriceTextFieldListener(TextField priceTxt) {
        this.priceTxt = priceTxt;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("\\d{0,7}([\\.]\\d{1,2})?")) {
            priceTxt.setText(oldValue);
        }
    }

}
