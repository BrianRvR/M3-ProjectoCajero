package com.mycompany.cajeroautomatico;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {

    private Cliente cliente;
    private int intentos=0;
    
    
    @FXML
    private TextField usuariop;
    
    @FXML
    private PasswordField contraseñap;
    
    @FXML
    private Label mensaje;
    
    public void initialize() {
        cliente = new Cliente("usuario", "password");
    }
    
    @FXML
     void iniciarSesion(ActionEvent event) throws IOException {
        String usuario = usuariop.getText();
        String password = contraseñap.getText();

        if (usuario.equals(cliente.getNombreUsuario()) && password.equals(cliente.getContrasena())) {
            mensaje.setText("Sesión iniciada correctamente");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Cerrar la ventana de inicio de sesión
            Stage loginStage = (Stage) usuariop.getScene().getWindow();
            loginStage.close();
        } else {
            intentos++;
            mensaje.setText("Usuario o contraseña incorrectos");

            if (intentos == 3) {
                mensaje.setText("Ha superado el número máximo de intentos.");
                usuariop.setDisable(true);
                contraseñap.setDisable(true);
            }
        }
    }
    /*private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        
       
        
    }*/
}
