package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import viewer.View;

public class Main extends Application {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GCS");

        button = new Button();
        button.setText("Button");

        Scene scene = new View(WIDTH, HEIGHT).getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
