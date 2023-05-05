/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author brian
 */
public class PantallaTransferenciaController {

    @FXML
    private Label nombreUsuarioLabel;
    @FXML
    private Button transferirButton;
    @FXML
    private TextField montoField;
    @FXML
    private TextField cuentaDestinoField;
    @FXML
    public TextField cuentaOrigenField;

    private Banco miBanco;
    private Cliente cliente;
    private Cuenta cuentaOrigen;

    public void setBanco(Banco banco) {
        this.miBanco = banco;
    }

    public void setCliente(Cliente cliente) {
    if (cliente != null) {
        this.cliente = cliente;
        nombreUsuarioLabel.setText(cliente.getNombreUsuario());
    } else {
        // Manejar caso de cliente nulo
    }
}

    public void setCuenta(Cuenta cuenta) {
        cuentaOrigenField.setText(Integer.toString(cuenta.getIdCuenta()));
        cuentaOrigen = cuenta;
    }

    public void actualizarSaldo(Cuenta cuenta) {
        cuentaOrigenField.setText(Integer.toString(cuenta.getIdCuenta()));
    }

    @FXML
    private void handleTransferirButtonAction(ActionEvent event) {
        // Obtener el monto a transferir
        double monto = Double.parseDouble(montoField.getText());

        // Obtener el ID de la cuenta corriente de destino
        int idCuentaDestino = Integer.parseInt(cuentaDestinoField.getText());

        Cuenta cuentaDestino = miBanco.buscarCuentaPorIdTipo(idCuentaDestino, "corriente");
        Cliente clienteDestino = cuentaDestino != null ? miBanco.buscarClientePorCuenta(cuentaDestino) : null;

        if (cuentaOrigen != null && cuentaDestino != null && clienteDestino != null && !clienteDestino.equals(cliente)) {
            // Transferir el monto de la cuenta origen a la cuenta destino
            try {
                miBanco.transferirEntreCuentas(cuentaOrigen.getIdCuenta(), cuentaDestino.getIdCuenta(), monto);
            } catch (IOException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al realizar la transferencia.");
                alert.showAndWait();
                return;
            }

            // Mostrar mensaje de éxito
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Transferencia realizada");
            alert.setHeaderText(null);
            alert.setContentText("La transferencia se ha realizado correctamente.");
            alert.showAndWait();

            // Cerrar la ventana actual
            Stage stage = (Stage) transferirButton.getScene().getWindow();
            stage.close();
        } else {
            // Mostrar mensaje de error si la cuenta de destino no se encontró o pertenece al mismo cliente
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("La cuenta corriente de destino no se encontró o pertenece al mismo cliente.");
            alert.showAndWait();
            return;
        }
    }
    
    @FXML
    private void handleCancelarButtonAction(ActionEvent event) {
        // Cerrar la ventana actual
        Stage stage = (Stage) transferirButton.getScene().getWindow();
        stage.close();
    }
}