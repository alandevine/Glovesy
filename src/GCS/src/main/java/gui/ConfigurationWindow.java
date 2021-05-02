package gui;

import Database.GloveConfigurationHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;

public class ConfigurationWindow implements Initializable {

    @FXML public VBox elements;
    @FXML public Label instruction;
    @FXML public Label timeRemaining;
    @FXML public Button ok;

    private Timeline timeline;
    private int durationOfCountdown = 5;

    Queue<String> instructions = new PriorityQueue<>();

    private final GloveConfigurationHandler gloveConfigurationHandler = new GloveConfigurationHandler( "mongodb://127.0.0.1:27017", "glovesy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.instructions.add("Lay your hand flat on a table.");
        this.instructions.add("Make a fist with your hand.");

        this.instruction.setText(instructions.poll());
        this.timeRemaining.setText(String.valueOf(durationOfCountdown));
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::advance));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    private void advance(ActionEvent actionEvent) {
        this.timeRemaining.setText(String.valueOf(this.durationOfCountdown));
        this.durationOfCountdown--;

        if (durationOfCountdown >= 0)
            return;

        if (instructions.isEmpty()) {
            this.ok.setOnAction(this::exitMenu);
            this.timeline.stop();
            return;
        }

        this.durationOfCountdown = 5;
        this.instruction.setText(instructions.poll());
    }

    private void exitMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) this.ok.getScene().getWindow();
        stage.close();
    }
}