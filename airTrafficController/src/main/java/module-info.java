module air.airtrafficcontroller {
    requires javafx.controls;
    requires javafx.fxml;


    opens air.airtrafficcontroller to javafx.fxml;
    exports air.airtrafficcontroller;
    exports air.airtrafficcontroller.options;
    opens air.airtrafficcontroller.options to javafx.fxml;
}