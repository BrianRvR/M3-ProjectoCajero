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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SecondaryController {
    
    @FXML
    private Label nombreUsuarioLabel;
    
    @FXML
    private Button btncorriente;
    
    @FXML
    private Button btnahorro;
    
    @FXML
    private Button cerrarsesion;
    
    private Cliente cliente;
    
    private Banco miBanco = App.getBanco();
    
    public void setCliente(Cliente cliente) {
    if (cliente != null) {
        this.cliente = cliente;
        nombreUsuarioLabel.setText(cliente.getNombreUsuario());
    } else {
        // Manejar caso de cliente nulo
    }
}
    public void setBanco(Banco banco) {
        this.miBanco = banco;
    }
    
    @FXML
    private void cargarPantallaCorriente() throws IOException {
        // Cargar la pantalla de la cuenta corriente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaCorriente.fxml"));
        Parent root = loader.load();
        PantallaCorrienteController controller = loader.getController();
        controller.setCliente(cliente);
        controller.setBanco(miBanco); // Pasar la instancia del objeto Banco al controlador de la pantalla de la cuenta corriente
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
        controller.setBanco(miBanco); // Pasar la instancia del objeto Banco al controlador de la pantalla de la cuenta de ahorro
        Scene scene = new Scene(root);
        Stage stage = (Stage) nombreUsuarioLabel.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void cambiarContraseña(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CambiarContrasena.fxml"));
        Parent root = loader.load();
        PantallaCambioContraseñaController controller = loader.getController();
        controller.setCliente(cliente);
        controller.setBanco(miBanco);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Actualizar el nombre de usuario en la pantalla después de cambiar la contraseña
        nombreUsuarioLabel.setText(cliente.getNombreUsuario());
    }
    
    @FXML
    private void transferir(ActionEvent event) throws IOException {
        // Cargar la pantalla de transferencia
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaTransferencia.fxml"));
        Parent root = loader.load();
        PantallaTransferenciaController controller = loader.getController();
        controller.setCliente(cliente);
        controller.setBanco(miBanco); // Pasar la instancia del objeto Banco al controlador de la pantalla de transferencia
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        // Cargar la pantalla de login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
    
