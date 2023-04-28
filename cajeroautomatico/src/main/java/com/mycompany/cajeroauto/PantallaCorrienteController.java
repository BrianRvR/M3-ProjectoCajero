package com.mycompany.cajeroauto;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class PantallaCorrienteController {
    @FXML
    private Label saldoLabel;
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private void handleDetallesButtonAction() {
        Cuenta cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
        if (cuentaCorriente != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cuentadetalles.fxml"));
            Stage stage = new Stage();
            try {
                Parent root = loader.load();
                DetallesCuenta controller = loader.getController();
                Cuenta cuentaSeleccionada = cliente.buscarCuentaPorId(cuentaCorriente.getIdCuenta());
                controller.setCuenta(cuentaSeleccionada);
                controller.actualizarLabels(); // Agrega esta línea para actualizar los labels
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saldoLabel.setText("No se encontró la cuenta corriente.");
        }
    }
}

    

