package vues;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Vue {
    private Stage stage;


    private Scene scene;


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void show(){
       stage.setScene(scene);
       stage.show();

    }
    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
