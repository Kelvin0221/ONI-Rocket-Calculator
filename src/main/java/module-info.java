module com.rocktcalculator.onirocketcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.desktop;

    opens com.rocktcalculator.onirocketcalculator to javafx.fxml;
    exports com.rocktcalculator.onirocketcalculator;
}