package com.mycompany.cajeroautomatico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
         
         /*Cuenta cuenta = new Cuenta("Joaco", 1200.00, null);
         Cuenta cuenta2 = new Cuenta("Joaco", 2000.00, null);
         
         List<Cuenta> cuentas = new ArrayList();
         cuentas.add(cuenta);
         cuentas.add(cuenta2);
         
         Double cantidadTotalSaldo = 0.00;
         
         for (Cuenta cuenta_x : cuentas) {
             cantidadTotalSaldo = cantidadTotalSaldo + cuenta_x.getSaldo();
         }
         
         System.out.println("Saldo total cuentas = " + cantidadTotalSaldo);
         
         cantidadTotalSaldo = 0.00;
         
         for (int i = 0; i < cuentas.size(); i++) {
             cantidadTotalSaldo = cantidadTotalSaldo + cuentas.get(i).getSaldo();
         }
         
         System.out.println("Con indice, Saldo total cuentas = " + cantidadTotalSaldo);

         //cuenta.hacerMovimiento(new Movimiento("Ingreso", new Date(), 1100.00));
        Cliente brian = new Cliente();
        brian.setUsuario("Brian");
        Cliente joaco = new Cliente("Joaco", "12345", false);
        
        System.out.println("Cliente brian: "+brian.getUsuario());
        System.out.println("Cliente joaco: "+joaco.getUsuario());
        launch();*/
    }

}