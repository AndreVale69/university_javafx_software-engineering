module com.univr.anagrafica {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.univr.anagrafica to javafx.fxml;
    exports com.univr.anagrafica;
    exports com.univr.graphics;
    opens com.univr.graphics.components.custom to javafx.base;
}