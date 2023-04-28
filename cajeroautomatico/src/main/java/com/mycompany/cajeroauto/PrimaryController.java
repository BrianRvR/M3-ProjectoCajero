package com.mycompany.cajeroauto;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PrimaryController {

    private Banco miBanco = new Banco("Mi Banco");
    private int intentos = 0;

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField contrasenaField;

    @FXML 
    private Label mensaje;
    
    @FXML
    private Button btnlogin;

   public void iniciarSesion(ActionEvent event) throws IOException {
    String nombreUsuario = usuarioField.getText();
    String password = contrasenaField.getText();
    Cliente cliente = miBanco.buscarClientePorCredenciales(nombreUsuario, password);

    if (cliente != null && cliente.getCuentas().size() > 0) {
        // Si el cliente existe, las credenciales son correctas y tiene al menos una cuenta, cargar la pantalla de selección de cuenta
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();
        SecondaryController controller = loader.getController();
        controller.setCliente(cliente);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } else {      
            // Si las credenciales son incorrectas o el cliente no tiene cuentas, mostrar mensaje de error y aumentar contador de intentos
        intentos++;
        if (intentos == 3) {
            mensaje.setText("Ha excedido el número máximo de intentos.");
            mensaje.setTextFill(Color.RED);
            usuarioField.setDisable(true);
            contrasenaField.setDisable(true);
            btnlogin.setDisable(true);

        } else {
            mensaje.setText("Por favor, verifique su nombre de usuario y contraseña.");
            mensaje.setTextFill(Color.RED);
        }
    }
   }
}

