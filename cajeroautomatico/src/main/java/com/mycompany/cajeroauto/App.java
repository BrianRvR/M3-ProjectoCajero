package com.mycompany.cajeroauto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 564.0, 386.0);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        Banco miBanco = new Banco("Mi Banco");
        Cliente nuevoCliente = new Cliente("usuario123", "password");

        Cuenta corriente = new Cuenta("001", "corriente", 1000);
        Cuenta ahorro = new Cuenta("002", "ahorro", 5000);

        nuevoCliente.agregarCuenta(corriente);
        nuevoCliente.agregarCuenta(ahorro);

        miBanco.agregarCliente(nuevoCliente);
    }

}