/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author brian
 */
public class PrestamoController implements Initializable {

    @FXML
    private TextField cantidadTextField;

    @FXML
    private Button botonCancelar;

    @FXML
    private ChoiceBox<Integer> plazoChoiceBox;

    private Cliente cliente;
    private Cuenta cuentaCorriente;

   public void initialize(URL url, ResourceBundle rb) {
    // Inicialización del controlador
    // Puedes realizar acciones adicionales al iniciar la ventana

    // Configurar los plazos disponibles en el ChoiceBox según la cantidad de préstamo
    cantidadTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.isEmpty()) {
            plazoChoiceBox.setItems(FXCollections.emptyObservableList());
            return;
        }

        double cantidadPrestamo = Double.parseDouble(newValue);

        if (cantidadPrestamo >= 1000 && cantidadPrestamo <= 5000) {
            plazoChoiceBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        } else if (cantidadPrestamo > 5000 && cantidadPrestamo <= 50000) {
            plazoChoiceBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        } else {
            plazoChoiceBox.setItems(FXCollections.emptyObservableList());
        }
    });
}
    

    public void setCuentaCorriente(Cuenta cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        // Obtener la cuenta corriente del cliente
        this.cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
    }

    private double calcularIngresos(List<Movimiento> movimientos) {
        // Lógica para calcular los ingresos basados en los movimientos de la cuenta
        // Puedes sumar los movimientos de tipo "ingreso" o aplicar tu lógica específica
        double totalIngresos = 0;
        for (Movimiento movimiento : movimientos) {
            if (movimiento.getTipoMovimiento().equals("ingreso")) {
                totalIngresos += movimiento.getMonto();
            }
        }

        return totalIngresos;
    }

    @FXML
private void solicitarPrestamo() {
    // Obtener la cantidad ingresada en el TextField
    double cantidadPrestamo = Double.parseDouble(cantidadTextField.getText());

    // Obtener el plazo seleccionado por el usuario
    int plazoSeleccionado = plazoChoiceBox.getValue();

    // Verificar si la cantidad solicitada cumple con el requisito mínimo
    if (cantidadPrestamo >= 1000) {
        // Verificar si la cuenta corriente existe
        if (cuentaCorriente != null) {
            // Lógica para evaluar los ingresos basados en los movimientos de la cuenta
            List<Movimiento> movimientos = cuentaCorriente.obtenerMovimientosCuenta();
            double ingresos = calcularIngresos(movimientos);

            // Verificar si los ingresos son suficientes para el préstamo solicitado
            if (ingresos >= cantidadPrestamo * 2) {
                System.out.println("¡Felicitaciones! Tu préstamo ha sido aprobado.");

                // Actualizar el saldo de la cuenta corriente después del préstamo
                cuentaCorriente.actualizarSaldoDespuesPrestamo(cantidadPrestamo);

                // Cerrar la ventana actual de Prestamo.fxml
                Stage stage = (Stage) cantidadTextField.getScene().getWindow();
                stage.close();
            } else {
                System.out.println("Lo siento, no cumples con los requisitos para el préstamo solicitado.");
            }
        } else {
            System.out.println("No se encontró la cuenta corriente.");
        }
    } else {
        System.out.println("La cantidad mínima para el préstamo es de 1000.");
    }
}
                 
                    
    @FXML
    private void cancelar() {
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
    }
}