module wang.blair.Personbook {
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;

    opens wang.blair.Personbook to javafx.fxml;
    exports wang.blair.Personbook;
}