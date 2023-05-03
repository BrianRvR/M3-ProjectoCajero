package com.mycompany.cajeroauto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class DetallesCuenta implements Initializable {
    
    @FXML
    private Label idCuentaLabel;
    
    @FXML
    private Label saldoLabel;
    
    @FXML
    private Label saldoTotalLabel;
    
    private Cuenta cuenta;
    
    private Cliente cliente;
    
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void actualizarLabels() {
        if (cuenta != null) {
            idCuentaLabel.setText("ID de Cuenta: " + cuenta.getIdCuenta());
            saldoLabel.setText("Saldo: " + cuenta.getSaldoActual());
        }
    }
    
    public void actualizarSaldoTotal(double saldoTotal) {
        saldoTotalLabel.setText("Saldo total: " + saldoTotal);
    }
    
    @FXML
    private void handleCloseButtonAction() {
        // Obtenemos la ventana actual y la cerramos
        Stage stage = (Stage) saldoLabel.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarLabels();
    }
}



    

