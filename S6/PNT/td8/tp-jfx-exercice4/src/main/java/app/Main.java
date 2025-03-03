package app;

import controller.Controller;
import facadeLudotheque.FacadeLudothequeImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Controller controller = new Controller(new FacadeLudothequeImpl(), stage);
        controller.run();
    }

    public static void main(String[] args) { launch(); }
}
