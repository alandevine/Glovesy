package viewer;

import javafx.scene.Group;
import javafx.scene.shape.Sphere;

import static viewer.util.setColor;

public class Hand {

    private final Group hand;

    public Hand(int width, int height) {
        this.hand = new Group();

        Finger thumb = new Finger(0.6, width - 60, height + 40);
        Finger index = new Finger(0.8, width - 30, height + 10);
        Finger middle = new Finger(1.0, width, height);
        Finger ring = new Finger(0.8, width + 30, height + 10);
        Finger pinky = new Finger(0.6, width + 60, height + 20);

        Sphere leftHandBase = new Sphere(10);
        leftHandBase.setLayoutX(width - 30);
        leftHandBase.setLayoutY(height + 100);
        setColor(leftHandBase, 1.0, 1.0, 0.0);


        Sphere rightHandBase = new Sphere(10);
        rightHandBase.setLayoutX(width + 30);
        rightHandBase.setLayoutY(height + 100);
        setColor(rightHandBase, 1.0, 1.0, 0.0);

        this.hand.getChildren().add(thumb.getFinger());
        this.hand.getChildren().add(index.getFinger());
        this.hand.getChildren().add(middle.getFinger());
        this.hand.getChildren().add(ring.getFinger());
        this.hand.getChildren().add(pinky.getFinger());
        this.hand.getChildren().add(leftHandBase);
        this.hand.getChildren().add(rightHandBase);
    }

    public Group getHand() {
        return this.hand;
    }
}
