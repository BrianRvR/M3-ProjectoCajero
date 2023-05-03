package com.mycompany.cajeroauto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PantallaCorrienteController {
    
    
    //private Label info;
    private FXMLLoader loader;
    private RetiroController controller;
    
    private IngresoController ingresoController;
    private PantallaTransferenciaController PantallaTransferenciaController;


    private Banco miBanco;

    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setBanco(Banco banco) {
        this.miBanco = banco;
    }
    
    public void someMethod() {
        setBanco(App.getBanco());
    }
    
    @FXML
    private void handleDetallesButtonAction() {
        // Obtener cuenta corriente
        Cuenta cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
        if (cuentaCorriente != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cuentadetalles.fxml"));
            Stage stage = new Stage();
            try {
                Parent root = loader.load();
                DetallesCuenta controller = loader.getController();
                // Obtener cuenta seleccionada
                Cuenta cuentaSeleccionada = cliente.buscarCuentaPorId(cuentaCorriente.getIdCuenta());
                controller.setCuenta(cuentaSeleccionada);
                // Actualizar saldo y saldo total
                controller.actualizarLabels();
                controller.actualizarSaldoTotal(cliente.obtenerSaldoTotalCuentas());
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } /*else {
            info.setText("No se encontró la cuenta corriente.");
        }*/
    }
    
    @FXML
    private void handleRetiroButtonAction() {
        // Obtener cuenta corriente
        Cuenta cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
        if (cuentaCorriente != null) {
            // Inicializar el objeto FXMLLoader
            loader = new FXMLLoader(getClass().getResource("Retiro.fxml"));

            // Cargar el archivo FXML de Retiro
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Obtener el controlador de la ventana de Retiro
            RetiroController controller = loader.getController();

            // Pasar la instancia de miBanco al controlador de la ventana de Retiro
            controller.setBanco(miBanco);
            controller.setCliente(cliente);

            // Actualizar el saldo de la cuenta corriente en el controlador de la ventana de Retiro
            controller.actualizarSaldo(cuentaCorriente);

            // Establecer la cuenta en el controlador de Retiro
            controller.setCuenta(cuentaCorriente);

            // Crear una nueva ventana y mostrarla
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } 
    }
    
    @FXML
    private void handleIngresoButtonAction() {
        // Obtener cuenta corriente
        Cuenta cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
        if (cuentaCorriente != null) {
            // Inicializar el objeto FXMLLoader
            // Inicializar el objeto FXMLLoader
            loader = new FXMLLoader(getClass().getResource("Ingreso.fxml"));

            // Cargar el archivo FXML de Ingreso
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Obtener el controlador de la ventana de Ingreso
            ingresoController = loader.getController();

            // Pasar la instancia de miBanco al controlador de la ventana de Ingreso
            ingresoController.setBanco(miBanco);

            // Establecer la cuenta en el controlador de Ingreso
            ingresoController.setCuenta(cuentaCorriente);

            // Crear una nueva ventana y mostrarla
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    @FXML
    private void BotonTransferencia(ActionEvent event) throws IOException {
        List<CuentaCorriente> cuentasCorrientes = cliente.getCuentasCorrientes();
        if (cuentasCorrientes.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No tienes cuentas corrientes.");
            alert.showAndWait();
        } else {
            // Cargar la pantalla de transferencia
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaTransferencia.fxml"));
            Parent root = loader.load();
            PantallaTransferenciaController controller = loader.getController();

            // Obtener la cuenta corriente seleccionada (si solo tiene una, se selecciona automáticamente)
            CuentaCorriente cuentaOrigen;
            if (cuentasCorrientes.size() == 1) {
                cuentaOrigen = cuentasCorrientes.get(0);
            } else {
                ChoiceDialog<CuentaCorriente> dialog = new ChoiceDialog<>(cuentasCorrientes.get(0), cuentasCorrientes);
                dialog.setTitle("Seleccionar cuenta corriente");
                dialog.setHeaderText(null);
                dialog.setContentText("Seleccione la cuenta corriente a utilizar:");

                Optional<CuentaCorriente> result = dialog.showAndWait();
                if (result.isPresent()) {
                    cuentaOrigen = result.get();
                } else {
                    return;
                }
            }

            controller.setCliente(cliente);
            controller.setCuentaOrigen(cuentaOrigen.getIdCuenta());
            controller.setBanco(miBanco); // Pasar la instancia del objeto Banco al controlador de la pantalla de transferencia
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
    }






    
    @FXML
    private void handleCerrarSesionButtonAction() throws IOException {
        App.setRoot("primary");
    }

}

    

