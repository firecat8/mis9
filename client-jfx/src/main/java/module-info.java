module com.mis9.jfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mis9.jfx to javafx.fxml;
    exports com.mis9.jfx;
}