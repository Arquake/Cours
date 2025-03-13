package vues;

import javafx.scene.Parent;
import javafx.scene.Scene;

public interface Vue {
    Scene getScene();

    void setScene(Scene scene);

    Parent getTopParent();

    void initialisation();
}
