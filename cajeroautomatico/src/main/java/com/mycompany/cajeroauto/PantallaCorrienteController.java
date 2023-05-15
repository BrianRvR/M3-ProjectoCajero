package com.mycompany.cajeroauto;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class PantallaCorrienteController {
    
    //private Label info;
    private FXMLLoader loader;
    private RetiroController controller;
    
    private IngresoController ingresoController;
    private PantallaTransferenciaController PantallaTransferenciaController;
    private MovimientosCuentaController MovimientosCuentaController;
    private DonacionController DonacionController;
    private SegurosController SegurosController;
    private PrestamoController PrestamoController;

    private Banco miBanco;

    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        someMethod();
    }

    public void setBanco(Banco banco) {
        this.miBanco = banco;
    }

    public void someMethod() {
        setBanco(App.getBanco());
    }

    @FXML
    private void DetallesBoton() {
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
    private void RetiroBoton() {
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
    private void IngresoBoton() {
        // Obtener cuenta corriente
        Cuenta cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
        if (cuentaCorriente != null) {
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

            // Actualizar el saldo de la cuenta corriente en el controlador de la ventana de Ingreso
            ingresoController.actualizarSaldo(cuentaCorriente);

            // Crear una nueva ventana y mostrarla
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void BotonTransferencia() {
        // Obtener cuenta corriente
        Cuenta cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
        if (cuentaCorriente != null) {
            // Inicializar el objeto FXMLLoader
            loader = new FXMLLoader(getClass().getResource("PantallaTransferencia.fxml"));

            // Cargar el archivo FXML de Deposito
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Obtener el controlador de la ventana de Deposito
            PantallaTransferenciaController controller = loader.getController();

            // Pasar la instancia de miBanco al controlador de la ventana de Deposito
            controller.setBanco(miBanco);
            controller.setCliente(cliente);

            // Actualizar el saldo de la cuenta corriente en el controlador de la ventana de Deposito
            controller.actualizarSaldo(cuentaCorriente);

            // Establecer la cuenta en el controlador de Deposito
            controller.setCuenta(cuentaCorriente);

            // Crear una nueva ventana y mostrarla
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    @FXML
    private void MovimientosBoton() {
        // Obtener la cuenta seleccionada
        Cuenta cuentaSeleccionada = cliente.buscarCuentaPorTipo("corriente");

        if (cuentaSeleccionada != null) {
            // Obtener los movimientos de la cuenta seleccionada
            List<Movimiento> movimientos = cuentaSeleccionada.obtenerMovimientosCuenta();

            // Crear la ventana de MovimientosCuenta y mostrarla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovimientosCuenta.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (root != null) {
                // Obtener el controlador de la ventana de MovimientosCuenta
                MovimientosCuentaController controller = loader.getController();

                // Pasar la instancia de miBanco al controlador de la ventana de MovimientosCuenta
                controller.setBanco(miBanco);

                // Pasar los movimientos a la ventana de MovimientosCuenta
                controller.setMovimientos(movimientos);

                // Crear una nueva ventana y mostrarla
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            // Mostrar un mensaje de error si no se ha seleccionado una cuenta
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar una cuenta primero.");
            alert.showAndWait();
        }
        
        
    }
    
    
    @FXML
    private void DonacionBoton() {
        // Obtener cuenta corriente
        Cuenta cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
        if (cuentaCorriente != null) {
            // Inicializar el objeto FXMLLoader
            loader = new FXMLLoader(getClass().getResource("Donacion.fxml"));

            // Cargar el archivo FXML de Donacion
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Obtener el controlador de la ventana de Donacion
            DonacionController controller = loader.getController();

            // Pasar la instancia de miBanco al controlador de la ventana de Donacion
            controller.setBanco(miBanco);
            controller.setCliente(cliente);

            // Actualizar el saldo de la cuenta corriente en el controlador de la ventana de Donacion
            controller.actualizarSaldo(cuentaCorriente);

            // Establecer la cuenta en el controlador de Donacion
            controller.setCuenta(cuentaCorriente);

            // Crear una nueva ventana y mostrarla
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    @FXML
    private void SegurosBoton() {
        // Obtener cuenta corriente
        Cuenta cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
        if (cuentaCorriente != null) {
            // Inicializar el objeto FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Seguros.fxml"));

            // Cargar el archivo FXML de Seguros
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Obtener el controlador de la ventana de Seguros
            SegurosController controller = loader.getController();

            // Pasar la instancia de miBanco y cliente al controlador de la ventana de Seguros
            controller.setBanco(miBanco);
            controller.setCliente(cliente);

            // Establecer la cuenta en el controlador de Seguros
            controller.setCuenta(cuentaCorriente);

            // Crear una nueva ventana y mostrarla
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    @FXML
    private void abrirPrestamoController() {
        Cuenta cuentaCorriente = cliente.buscarCuentaPorTipo("corriente");
        if (cuentaCorriente != null) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Prestamo.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrestamoController prestamoController = loader.getController();
        prestamoController.setCuentaCorriente(cuentaCorriente);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
       }
        
    }
    
     @FXML
    private void Volveralmenu(ActionEvent event) {
        // Cerrar la ventana actual
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        // Abrir la ventana anterior (en este caso, SecondaryController)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Stage secondaryStage = new Stage();
        try {
            Parent root = loader.load();
            SecondaryController controller = loader.getController();
            controller.setCliente(cliente);
            controller.setBanco(miBanco);
            secondaryStage.setScene(new Scene(root));
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 
    

