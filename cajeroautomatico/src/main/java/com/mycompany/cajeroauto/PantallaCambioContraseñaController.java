package com.mycompany.cajeroauto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class PantallaCambioContraseñaController {
    
    @FXML
    private PasswordField contraseñaActualField;
    
    @FXML
    private PasswordField contraseñaNuevaField;
    
    @FXML
    private Button cambiarContraseñaButton;
    
    @FXML
    private Button CancelarCambioButton;
    
    private Cliente cliente;
    
    private Banco miBanco = App.getBanco();
    
    public void setCliente(Cliente cliente) {
        if (cliente != null) {
            this.cliente = cliente;
        } else {
            // Manejar caso de cliente nulo
        }
    }
    
    public void setBanco(Banco banco) {
        this.miBanco = banco;
    }
    
    @FXML
    private void cambiarContraseña(ActionEvent event) throws IOException {
        String contraseñaActual = contraseñaActualField.getText();
        String contraseñaNueva = contraseñaNuevaField.getText();

        // Verificar que la contraseña actual sea correcta
        if (!cliente.getPassword().equals(contraseñaActual)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("La contraseña actual no es correcta.");
            alert.showAndWait();
            return;
        }

        // Definir las variables para cambiar la contraseña del cliente en la clase Banco
        String contraseñaAntigua = contraseñaActual;
        String archivoClientes = "src/main/java/com/mycompany/csv/clientes_cuentas.csv";

        // Cambiar la contraseña del cliente en la clase Banco
        miBanco.cambiarContraseñaCliente(cliente, contraseñaAntigua, contraseñaNueva, archivoClientes);

        // Cerrar la ventana de cambio de contraseña
        Stage stage = (Stage) cambiarContraseñaButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void cancelarCambioContraeña(ActionEvent event){
        Stage stage=(Stage) CancelarCambioButton.getScene().getWindow();
        stage.close();
    }
    
}

    
