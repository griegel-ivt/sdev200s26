module com.sdev200 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.sdev200 to javafx.fxml;
    exports com.sdev200;
}
