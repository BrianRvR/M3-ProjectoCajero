package com.mycompany.cajeroautomatico;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SecondaryController {

    @FXML private Label userLabel;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        
    }
    public void setUserName(String username) {
        userLabel.setText(username);
    }
}