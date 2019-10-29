/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author gdimitrova
 */
public class LoginController implements Initializable {

    @FXML
    private TextField userNameInput, passwordInput;

    @FXML
    private Button loginBtn;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getTarget() == loginBtn) {
             App.setRoot("sales_system");
//            if (userNameInput.getText().equals("admin") && passwordInput.getText().equals("admin")) {
//                App.setRoot("sales_system");
//            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
