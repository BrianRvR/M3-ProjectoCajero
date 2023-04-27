package com.mycompany.cajeroauto;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SecondaryController {
    
    @FXML
    private Label nombreUsuarioLabel;
    
    @FXML
    private Button btncorriente;
    
    @FXML
    private Button btnahorro;
    
    
    private Cliente cliente;
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        nombreUsuarioLabel.setText(cliente.getNombreUsuario());
    }
    
    @FXML
    private void cargarPantallaCorriente() throws IOException {
        // Cargar la pantalla de la cuenta corriente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaCoriente.fxml"));
        Parent root = loader.load();
        PantallaCorrienteController controller = loader.getController();
        controller.setCliente(cliente);
        Scene scene = new Scene(root);
        Stage stage = (Stage) nombreUsuarioLabel.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void cargarPantallaAhorro() throws IOException {
        // Cargar la pantalla de la cuenta de ahorro
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaAhorro.fxml"));
        Parent root = loader.load();
        PantallaAhorroController controller = loader.getController();
        controller.setCliente(cliente);
        Scene scene = new Scene(root);
        Stage stage = (Stage) nombreUsuarioLabel.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}

    
