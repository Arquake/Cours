package vues.abstractVues;

import javafx.scene.Scene;
import vues.Vue;

public abstract class AbstractVue implements Vue {

    private Scene scene;

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void initialisation() {
        this.scene = new Scene(this.getTopParent());
    }
}
