package com.mycompany.cajeroauto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class DetallesCuenta implements Initializable {
    
    
    @FXML
    private Label idCuentaLabel;
    
    @FXML
    private Label saldoLabel;
    
    private Cuenta cuenta;
    
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    public void actualizarLabels() {
        if (cuenta != null) {
            idCuentaLabel.setText("ID de Cuenta: " + cuenta.getIdCuenta());
            saldoLabel.setText("Saldo: " + cuenta.getSaldoActual());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarLabels();
    }
}



    

