package viewer;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.Group;
import javafx.scene.shape.Sphere;

import static viewer.util.setColor;

/**
 * Representation of a finger.
 */
public class Finger {

    private final Group fingerGroup;

    /**
     * Finger Constructor method.
     * @param ratio Ratio of this finger to other fingers.
     * @param x horizontal position of root node
     * @param y vertical position of root node
     */
    public Finger (double ratio, int x, int y) {
        // instantiate joint positions
        Sphere knuckle = new Sphere(10);
        Sphere joint = new Sphere(10);
        Sphere tip = new Sphere(10);

        knuckle.setLayoutX(x);
        knuckle.setLayoutY(y);
        setColor(knuckle, 1.0, 0.0, 0.0);

        joint.setLayoutX(x);
        joint.setLayoutY((double) y - 50 * ratio);
        setColor(joint, 0.0, 1.0, 0.0);

        tip.setLayoutX(x);
        tip.setLayoutY((double) y - 80 * ratio);
        setColor(tip, 0.0, 0.0, 1.0);

        this.fingerGroup = new Group();

        this.fingerGroup.getChildren().add(knuckle);
        this.fingerGroup.getChildren().add(joint);
        this.fingerGroup.getChildren().add(tip);
    }

    public Group getFinger() {
        return this.fingerGroup;
    }
}
