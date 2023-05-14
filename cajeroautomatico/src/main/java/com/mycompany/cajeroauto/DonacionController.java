/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author brian
 */
public class DonacionController {
    private Banco miBanco;
    private Cliente cliente;
    private Cuenta cuenta;

    @FXML
    private Button botonCancelar;
    
    @FXML
    private Button botonDonar;
    @FXML
    private TextField montoDonacion; // Suponiendo que tienes un TextField para el monto a donar

    @FXML
    private ChoiceBox<String> seleccionAsociacion; // Suponiendo que tienes un ChoiceBox para seleccionar la asociación
    
    @FXML
    private Map<String, String> informacionAsociaciones;
    
    @FXML
    private Label infoAsociacion ;

    public void initialize() {
        // Supón que tienes una lista de asociaciones
        List<String> asociaciones = Arrays.asList("Cruz Roja", "Médicos Sin Fronteras", "Unicef", "Amnistía Internacional", "World Wildlife Fund (WWF)", "Save the Children");

        // Define la información de cada asociación
        informacionAsociaciones = new HashMap<>();
        informacionAsociaciones.put("Cruz Roja", "La Cruz Roja proporciona asistencia humanitaria en casos de emergencia.");
        informacionAsociaciones.put("Médicos Sin Fronteras", "Médicos Sin Fronteras ofrece atención médica en áreas de conflicto.");
        informacionAsociaciones.put("Unicef", "Unicef trabaja para mejorar las vidas de los niños y sus familias.");
        informacionAsociaciones.put("Amnistía Internacional", "Amnistía Internacional lucha por los derechos humanos en todo el mundo.");
        informacionAsociaciones.put("World Wildlife Fund (WWF)", "WWF trabaja para conservar la naturaleza y reducir las amenazas \n más apremiantes para la diversidad de la vida en la Tierra.");
        informacionAsociaciones.put("Save the Children", "Save the Children trabaja para mejorar la vida de los niños\n a través de mejores programas de educación, salud y seguridad económica.");

        // Luego, puedes llenar tu ChoiceBox con estas asociaciones
        seleccionAsociacion.getItems().add("Seleccionar");
        seleccionAsociacion.getItems().addAll(asociaciones);

        // Establece "Seleccionar" como valor por defecto
        seleccionAsociacion.setValue("Seleccionar");

        // Agrega un ChangeListener a la ChoiceBox
        seleccionAsociacion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Este código se ejecutará cada vez que el valor seleccionado en la ChoiceBox cambie

            // Aquí, puedes actualizar el texto de tu Label basado en la nueva asociación seleccionada
            if(!newValue.equals("Seleccionar")) {
                String info = obtenerInfoAsociacion(newValue);
                infoAsociacion.setText(info);
            } else {
                infoAsociacion.setText(""); // si la opción "Seleccionar" está seleccionada, limpia el texto del label
            }
        });
    }

    private String obtenerInfoAsociacion(String asociacion) {
        return informacionAsociaciones.get(asociacion);
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

    // Método para actualizar el saldo en la interfaz
    public void actualizarSaldo(Cuenta cuenta) {
        // Aquí actualizas el saldo en la interfaz, por ejemplo podrías tener un Label que muestre el saldo actual
    }

    @FXML
    private void donar() {
        String asociacion = seleccionAsociacion.getValue();

        // Verifica que una asociación válida ha sido seleccionada
        if (asociacion.equals("Seleccionar")) {
            // Muestra un mensaje de error si no se ha seleccionado una asociación
            Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor selecciona una asociación.");
            alert.showAndWait();
        } else {
            double monto = Double.parseDouble(montoDonacion.getText());
            cuenta.donar(monto, asociacion);
            actualizarSaldo(cuenta);

            // Muestra un mensaje de agradecimiento después de hacer una donación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Donación realizada");
            String content = "Gracias por tu donación.\nAyudarás bastante a la asociación " + asociacion + ".";
            Label label = new Label(content);
            label.setWrapText(true);
            alert.getDialogPane().setContent(label);

            // Ajusta el contenido al tamaño de la ventana
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            alert.showAndWait();
        }
         // cerrar la ventana actual
        Stage stage = (Stage) botonDonar.getScene().getWindow();
        stage.close();

       
    }
    @FXML
    private void cancelar() {
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
    }

}






