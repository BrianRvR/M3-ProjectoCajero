/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author brian
 */
public class MovimientosCuentaController extends AnchorPane {
    
    @FXML
    private AnchorPane rootAnchorPane;
    
    @FXML
    private TableView<Movimiento> movimientosTable;
    
    @FXML
    private TableColumn<Movimiento, String> tipoColumn;
    
    @FXML
    private TableColumn<Movimiento, String> fechaColumn;
    
    @FXML
    private TableColumn<Movimiento, Double> montoColumn;
    
    @FXML
    private Button volverButton;
    
    private Cuenta cuentaSeleccionada;
    
    private Banco miBanco;
    
    public void setBanco(Banco banco) {
        this.miBanco = banco;
    }
    
    public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
        ObservableList<Movimiento> movimientos = FXCollections.observableArrayList(cuentaSeleccionada.obtenerMovimientosCuenta());
        movimientosTable.setItems(movimientos);
    }
    
    public void setMovimientos(List<Movimiento> movimientos) {
        if (movimientosTable != null) {
            ObservableList<Movimiento> listaMovimientos = FXCollections.observableArrayList(movimientos);
            movimientosTable.setItems(listaMovimientos);
        }
    }
    
    @FXML
    private void initialize() {
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoMovimiento"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        montoColumn.setCellValueFactory(new PropertyValueFactory<>("monto"));


        if (cuentaSeleccionada != null) {
            ObservableList<Movimiento> movimientos = FXCollections.observableArrayList();
            movimientos.addAll(cuentaSeleccionada.obtenerMovimientosCuenta());
            movimientosTable.setItems(movimientos);
        }
    }
    
    @FXML
    private void handleVolverButtonAction(ActionEvent event) {
        // Cerrar la ventana actual
        Stage stage = (Stage) volverButton.getScene().getWindow();
        stage.close();
    }
}