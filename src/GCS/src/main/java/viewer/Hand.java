package viewer;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;

/**
 * @TODO: 12/01/2021: Refactor Constructor method
 */
public class Hand {

    private final Group hand;

    public Hand(int width, int height) {
        this.hand = new Group();

        Finger thumb = new Finger(0.6, width - 60, height + 40);
        Finger index = new Finger(0.8, width - 30, height + 10);

        Line thumbToIndex = util.addEdge(thumb.getKnuckle(), index.getKnuckle());

        Finger middle = new Finger(1.0, width, height);
        Line indexToMiddle = util.addEdge(index.getKnuckle(), middle.getKnuckle());

        Finger ring = new Finger(0.8, width + 30, height + 10);
        Line middleToRing = util.addEdge(middle.getKnuckle(), ring.getKnuckle());

        Finger pinky = new Finger(0.6, width + 60, height + 20);
        Line ringToPinky = util.addEdge(ring.getKnuckle(), pinky.getKnuckle());

        Sphere leftHandBase = new Sphere(10);
        leftHandBase.setLayoutX(width - 30);
        leftHandBase.setLayoutY(height + 100);
        util.setColor(leftHandBase, 1.0, 1.0, 0.0);

        Sphere rightHandBase = new Sphere(10);
        rightHandBase.setLayoutX(width + 30);
        rightHandBase.setLayoutY(height + 100);
        util.setColor(rightHandBase, 1.0, 1.0, 0.0);

        Line edge = util.addEdge(leftHandBase, rightHandBase);
        Line thumbToLBase = util.addEdge(thumb.getKnuckle(), leftHandBase);
        Line pinkyToRBase = util.addEdge(pinky.getKnuckle(), rightHandBase);

        this.hand.getChildren().add(thumb.getFinger());
        this.hand.getChildren().add(index.getFinger());
        this.hand.getChildren().add(middle.getFinger());
        this.hand.getChildren().add(ring.getFinger());
        this.hand.getChildren().add(pinky.getFinger());
        this.hand.getChildren().add(leftHandBase);
        this.hand.getChildren().add(rightHandBase);

        this.hand.getChildren().add(edge);
        this.hand.getChildren().add(thumbToIndex);
        this.hand.getChildren().add(indexToMiddle);
        this.hand.getChildren().add(middleToRing);
        this.hand.getChildren().add(ringToPinky);
        this.hand.getChildren().add(thumbToLBase);
        this.hand.getChildren().add(pinkyToRBase);

    }

    public Group getHand() {
        return this.hand;
    }
}
