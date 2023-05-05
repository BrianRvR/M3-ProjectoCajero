package com.mycompany.cajeroauto;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Banco miBanco;

    @Override
    public void start(Stage stage) throws IOException, FileNotFoundException, ParseException {
        // Cargar clientes desde el archivo CSV"src/main/java/com/mycompany/csv/clientes_cuentas.csv"
        miBanco = new Banco("Mi Banco", "src/main/java/com/mycompany/csv/clientes_cuentas.csv"); // Crear instancia de Banco con clientes y cuentas del archivo CSV
        scene = new Scene(loadFXML("primary"), 773.0, 614.0);  
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

    public static Banco getBanco() {
        return miBanco;
    }

    public static void main(String[] args) {
        launch();        
    }

}
