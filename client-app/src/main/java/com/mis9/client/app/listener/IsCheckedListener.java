/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.listener;

import java.util.function.BiConsumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

/**
 *
 * @author gdimitrova
 */
public class IsCheckedListener implements ChangeListener<Boolean> {

    private CheckBox checkBox;

    private BiConsumer<Boolean, Boolean> func;

    public IsCheckedListener(CheckBox checkBox, BiConsumer<Boolean, Boolean> func) {
        this.checkBox = checkBox;
        this.func = func;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> ov, Boolean oldhecked, Boolean isChecked) {
        func.accept(checkBox.isVisible(), isChecked);
    }
  
}
