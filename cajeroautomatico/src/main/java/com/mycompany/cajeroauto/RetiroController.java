/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author brian
 */
public class RetiroController implements Initializable {

    @FXML
    private Label saldoActualLabel;

    @FXML
    private TextField montoTextField;
    
    @FXML
    private Button retiro20Button;
    
    @FXML
    private Button retiro50Button;
    
    @FXML
    private Button retiro100Button;

    private Banco miBanco;
    private Cliente cliente;
    private Cuenta cuenta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setBanco(Banco banco) {
        miBanco = banco;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void actualizarSaldo(Cuenta cuenta) {
        saldoActualLabel.setText("$ " + cuenta.getSaldoActual());
    }

    @FXML
    private void handleRetirarButtonAction(ActionEvent event) {
        
                if (miBanco == null) {
            System.err.println("El objeto miBanco es nulo.");
            return;
        }
        double monto = Double.parseDouble(montoTextField.getText());

        if (monto % 10 != 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("El monto a retirar debe ser múltiplo de 10");
            alert.showAndWait();
            return;
        }

        if (monto <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("El monto a retirar debe ser mayor a cero");
            alert.showAndWait();
            return;
        }

        if (!cuenta.verificarSaldoSuficiente(monto)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Saldo insuficiente");
            alert.showAndWait();
            return;
        }

        cuenta.retirar(monto);

        // Actualizar el saldo de la cuenta en el archivo CSV
        try {
            miBanco.actualizarInfoClienteCSV(miBanco.getClientes(), "src/main/java/com/mycompany/csv/clientes_cuentas.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Desglosar el monto en billetes de $100, $50 y $20
        int cantidadBilletes100 = (int) (monto / 100);
        int cantidadBilletes50 = (int) ((monto % 100) / 50);
        int cantidadBilletes20 = (int) (((monto % 100) % 50) / 20);

        // Mostrar el mensaje de retiro exitoso
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Retiro exitoso");
        alert.setHeaderText("Retiro realizado con éxito");
        alert.setContentText("Billetes de $100: " + cantidadBilletes100 + "\nBilletes de $50: " + cantidadBilletes50
                + "\nBilletes de $20: " + cantidadBilletes20);
        alert.showAndWait();

        // Cerrar la ventana de retiro
        Stage stage = (Stage) saldoActualLabel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void handleRetiro20ButtonAction(ActionEvent event) {
        montoTextField.setText("20");
        handleRetirarButtonAction(event);
    }

    @FXML
    private void handleRetiro50ButtonAction(ActionEvent event) {
        montoTextField.setText("50");
         handleRetirarButtonAction(event);
    }

    @FXML
    private void handleRetiro100ButtonAction(ActionEvent event) {
        montoTextField.setText("100");
         handleRetirarButtonAction(event);
    }
}