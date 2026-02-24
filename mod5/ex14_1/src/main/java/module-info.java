module com.sdev200 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sdev200 to javafx.fxml;
    exports com.sdev200;
}
