module com.mycompany.cajeroauto {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.cajeroauto to javafx.fxml;
    exports com.mycompany.cajeroauto;
}
