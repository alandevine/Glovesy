package viewer;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class View {

    private final Hand hand;
    private final Scene scene;

    public View (int width, int height) {
        hand = new Hand(width / 2, height / 2);
        scene = new Scene(hand, width, height);

        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);
        initKeyboardHandler();
    }

    public Scene getScene() {
        return scene;
    }

    /**
     * Method for adding keyboard events to apply rotation on the overall scene.
     * The cases below are to be replaced with data received from the glove
     */
    private void initKeyboardHandler() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP:
                    hand.rotateOnXAxis(+10);
                    break;
                case DOWN:
                    hand.rotateOnXAxis(-10);
                    break;
                case LEFT:
                    hand.rotateOnYAxis(-10);
                    break;
                case RIGHT:
                    hand.rotateOnYAxis(10);
                    break;
            }
        });
    }
}