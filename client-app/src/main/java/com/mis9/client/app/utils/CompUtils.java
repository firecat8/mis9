/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.utils;

import java.util.function.UnaryOperator;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author gdimitrova
 */
public class CompUtils {
    
    public static void setVisible(boolean visible, Button... buttons) {
        for (Button button : buttons) {
            button.setVisible(visible);
        }
    }
    
    public static void setVisible(boolean visible, TextField... fields) {
        for (TextField field : fields) {
            field.setVisible(visible);
        }
    }
    
    public static void setVisible(boolean visible, Label... labels) {
        for (Label l : labels) {
            l.setVisible(visible);
        }
    }
    
    public static void setVisible(boolean visible, ChoiceBox... choiceBoxes) {
        for (ChoiceBox c : choiceBoxes) {
            c.setVisible(visible);
        }
    }
    
    public static void setVisible(boolean visible, CheckBox... checkBoxes) {
        for (CheckBox c : checkBoxes) {
            c.setVisible(visible);
        }
    }

    public static void setVisible(boolean visible, AnchorPane... panes) {
        for (AnchorPane p : panes) {
            p.setVisible(visible);
        }
    }

    public static void setVisible(boolean visible, Group... groups) {
        for (Group g : groups) {
            g.setVisible(visible);
            g.setManaged(visible);
        }
    }
    
    public static void clear(ChoiceBox... choiceBoxes) {
        for (ChoiceBox c : choiceBoxes) {
            c.getItems().clear();
        }
    }
    
    public static TextFormatter<String> createTextFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            return text.matches("[0-9]*") ? change : null;
        };
        return new TextFormatter<>(filter);
    }
}
