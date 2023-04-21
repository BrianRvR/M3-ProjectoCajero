package com.mycompany.cajeroautomatico;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class SecondaryController {
    private Cuenta cuenta;

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    // Métodos para los botones que abren los demás controladores
    @FXML
    private void irAMovimientos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("movimientos.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        MovimientosController controller = loader.getController();
        controller.setCuenta(cuenta);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        SecondaryController secController = loader.getController();
        secController.setCuenta(cuenta);
        stage.show();
    }

    @FXML
    private void irASaldo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("saldo.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        SaldoController controller = loader.getController();
        controller.setCuenta(cuenta);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        SecondaryController secController = loader.getController();
        secController.setCuenta(cuenta);
        stage.show();
    }

    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        PrimaryController controller = loader.getController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
