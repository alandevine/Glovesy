package viewer;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Scene;

public class View {
    private final int width;
    private final int height;

    public View (int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene getScene() {
        Hand hand = new Hand(width / 2, height / 2);
        Group handGroup = hand.getHand();

        Scene scene = new Scene(handGroup, width, height);

        return scene;
    }
}