package viewer;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;

/**
 * Representation of a finger.
 */
public class Finger extends Group {

    private final Sphere knuckle;
    private final Sphere joint;
    private final Sphere tip;

    /**
     * Finger Constructor method.
     * @param ratio Ratio of this finger to other fingers.
     * @param x horizontal position of root node
     * @param y vertical position of root node
     */
    public Finger (double ratio, int x, int y) {
        // instantiate joint positions
        knuckle = new Sphere(10);
        joint = new Sphere(10);
        tip = new Sphere(10);


        this.getChildren().add(knuckle);
        this.getChildren().add(joint);
        this.getChildren().add(tip);


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

        this.getChildren().add(edge1);
        this.getChildren().add(edge2);
    }

    public Sphere getKnuckle() {
        return knuckle;
    }

    public void contractJoint() {}

    public void contractTip() {}


}
