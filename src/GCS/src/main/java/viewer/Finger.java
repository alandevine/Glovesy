package viewer;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;

/**
 * Representation of a finger.
 */
public class Finger {

    private final Group fingerGroup;
    private final Sphere knuckle;

    /**
     * Finger Constructor method.
     * @param ratio Ratio of this finger to other fingers.
     * @param x horizontal position of root node
     * @param y vertical position of root node
     */
    public Finger (double ratio, int x, int y) {
        // instantiate joint positions
        knuckle = new Sphere(10);
        Sphere joint = new Sphere(10);
        Sphere tip = new Sphere(10);

        knuckle.setLayoutX(x);
        knuckle.setLayoutY(y);
        util.setColor(knuckle, 1.0, 0.0, 0.0);

        joint.setLayoutX(x);
        joint.setLayoutY((double) y - 50 * ratio);
        util.setColor(joint, 0.0, 1.0, 0.0);

        Line edge1 = util.addEdge(knuckle, joint);

        tip.setLayoutX(x);
        tip.setLayoutY((double) y - 80 * ratio);
        util.setColor(tip, 0.0, 0.0, 1.0);

        Line edge2 = util.addEdge(joint, tip);

        this.fingerGroup = new Group();

        this.fingerGroup.getChildren().add(knuckle);
        this.fingerGroup.getChildren().add(joint);
        this.fingerGroup.getChildren().add(tip);
        this.fingerGroup.getChildren().add(edge1);
        this.fingerGroup.getChildren().add(edge2);
    }

    public Group getFinger() {
        return this.fingerGroup;
    }

    public Sphere getKnuckle() {
        return knuckle;
    }
}
