module permissions {
    requires javafx.controls;
    requires javafx.fxml;

    opens vues to javafx.fxml;
    exports pnt;
    opens vues.abstractvue to javafx.fxml;
}