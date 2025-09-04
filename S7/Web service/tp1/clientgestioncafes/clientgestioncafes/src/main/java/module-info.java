module fr.info.orleans.ws {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires java.net.http;

    opens fr.info.orleans.ws.vues to javafx.fxml;
    exports fr.info.orleans.ws;
    exports fr.info.orleans.ws.modele;
}