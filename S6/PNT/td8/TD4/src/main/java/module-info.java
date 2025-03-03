module permissions {
    requires javafx.controls;
    requires javafx.fxml;

    opens vues to javafx.fxml;
    exports pnt;
}