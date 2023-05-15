/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author brian
 */
public class SegurosController implements Initializable {

    @FXML
    private Label saldoActualLabel;

    private Banco miBanco;
    private Cliente cliente;
    private Cuenta cuenta;

    @FXML
    private TextArea textoSeguro1;
    
    @FXML
    private TextArea textoSeguro2;
    
    @FXML
    private Button botonCancelar;
    
    @FXML
    private Button botonseguro1;
    
    @FXML
    private Button botonseguro2;

    private double precioSeguro1 = 10000;
    private double precioSeguro2 = 5000;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar los textos con la información de los seguros
         textoSeguro1.setText("Seguro para personas mayores de 50 años durante 3 años. Este seguro está\n" +
        "diseñado especialmente para brindar protección a personas de 50 años o más.\n" +
        "Ofrece cobertura completa en caso de fallecimiento, así como beneficios\n" +
        "adicionales como indemnización por enfermedades críticas. \n" +
        "\n" +
        "Precio: $" + precioSeguro1);

         textoSeguro2.setText("Seguro para personas de 20 a 49 años durante 3 años. Este seguro está dirigido\n" +
        "a personas jóvenes y de mediana edad brindando una amplia cobertura en caso\n" +
        "de fallecimiento y una protección financiera sólida para tus seres queridos. \n" +
        "Además, ofrece beneficios adicionales como asistencia médica\n" +
        "en caso de accidentes. \n" +
        "\n" +
        "Precio: $" + precioSeguro2);
         
}

    public void setBanco(Banco banco) {
        this.miBanco = banco;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    
     @FXML
    private void comprarSeguro1() {
        try {
            cuenta.comprarSeguro(precioSeguro1, "Seguro para personas mayores de 50 años durante 3 años");

            // Mostrar mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Seguro contratado");
            alert.setHeaderText(null);
            alert.setContentText("Has contratado el seguro para personas mayores de 50 años durante 3 años.\nSe ha realizado el pago de $" + precioSeguro1);
            alert.showAndWait();

            // Desactivar el botón después de la compra exitosa
            botonseguro1.setDisable(true);
        } catch (IllegalArgumentException e) {
            // Mostrar mensaje de error si el saldo de la cuenta no es suficiente
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saldo insuficiente");
            alert.setHeaderText(null);
            alert.setContentText("No tienes suficiente saldo para contratar este seguro.");
            alert.showAndWait();
        }
    }

    @FXML
    private void comprarSeguro2() {
        try {
            cuenta.comprarSeguro(precioSeguro2, "Seguro para personas de 20 a 49 años durante 3 años");

            // Mostrar mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Seguro contratado");
            alert.setHeaderText(null);
            alert.setContentText("Has contratado el seguro para personas mayores de 20 a 49 años durante 3 años.\nSe ha realizado el pago de $" + precioSeguro2);
            alert.showAndWait();

            // Desactivar el botón después de la compra exitosa
            botonseguro2.setDisable(true);
        } catch (IllegalArgumentException e) {
            // Mostrar mensaje de error si el saldo de la cuenta no es suficiente
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saldo insuficiente");
            alert.setHeaderText(null);
            alert.setContentText("No tienes suficiente saldo para contratar este seguro.");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
    }
}