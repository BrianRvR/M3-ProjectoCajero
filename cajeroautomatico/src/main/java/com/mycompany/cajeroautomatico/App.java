package com.mycompany.cajeroautomatico;

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
        scene = new Scene(loadFXML("primary"), 561, 386);
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
         launch(args);
        /*Cliente brian = new Cliente();
        brian.setUsuario("Brian");
        Cliente joaco = new Cliente("Joaco", "12345", false);
        
        System.out.println("Cliente brian: "+brian.getUsuario());
        System.out.println("Cliente joaco: "+joaco.getUsuario());
        launch();*/
    }

}