module com.mycompany.cajeroautomatico {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.cajeroautomatico to javafx.fxml;
    exports com.mycompany.cajeroautomatico;
}
