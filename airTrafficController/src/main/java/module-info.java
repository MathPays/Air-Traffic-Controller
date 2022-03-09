module air.airtrafficcontroller {
    requires javafx.controls;
    requires javafx.fxml;


    opens air.airtrafficcontroller to javafx.fxml;
    exports air.airtrafficcontroller;
}