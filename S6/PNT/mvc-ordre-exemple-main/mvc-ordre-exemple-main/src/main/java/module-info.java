module fr.univ.orleans.pnt {
    requires javafx.controls;
    requires javafx.fxml;

    opens vues to javafx.fxml;
    opens modele to javafx.base;
    exports pnt;
}