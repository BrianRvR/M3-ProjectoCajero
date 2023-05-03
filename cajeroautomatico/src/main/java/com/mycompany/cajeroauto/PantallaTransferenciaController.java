/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author brian
 */
public class PantallaTransferenciaController {
    
    @FXML
    private Label nombreUsuarioLabel;
    
    @FXML
    private Button btnTransferir;
    
    @FXML
    private Button cerrarsesion;
    
    @FXML
    private TextField txtCuentaOrigen;
    
    @FXML
    private TextField txtCuentaDestino;
    
    @FXML
    private TextField txtMonto;
    
    private Cliente cliente;
    
    private Banco miBanco = App.getBanco();
    
    public void setCuentaOrigen(int numeroCuenta) {
        txtCuentaOrigen.setText(Integer.toString(numeroCuenta));
    }

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
    private void transferir(ActionEvent event) throws IOException {
        // Obtener los valores de los campos de texto
        int idCuentaOrigen = Integer.parseInt(txtCuentaOrigen.getText());
        int idCuentaDestino = Integer.parseInt(txtCuentaDestino.getText());
        double monto = Double.parseDouble(txtMonto.getText());
        
        // Buscar las cuentas de origen y destino
        Cuenta cuentaOrigen = null;
        Cuenta cuentaDestino = null;
        for (Cliente cliente : miBanco.getClientes()) {
            cuentaOrigen = cliente.buscarCuentaPorId(idCuentaOrigen);
            cuentaDestino = cliente.buscarCuentaPorId(idCuentaDestino);
            if (cuentaOrigen != null && cuentaDestino != null) {
                break;
            }
        }
        
        // Verificar que las cuentas existan
        if (cuentaOrigen == null || cuentaDestino == null) {
            throw new IllegalArgumentException("Cuenta origen o destino no encontrada");
        }
        
        // Realizar la transferencia entre las cuentas
        miBanco.transferirEntreCuentas(cuentaOrigen, cuentaDestino, monto);
        
        // Mostrar mensaje de éxito en la transferencia
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Transferencia realizada");
        alert.setHeaderText(null);
        alert.setContentText("La transferencia se realizó correctamente.");
        alert.showAndWait();
        
        // Limpiar los campos de texto
        txtCuentaOrigen.setText("");
        txtCuentaDestino.setText("");
        txtMonto.setText("");
    }
    
}