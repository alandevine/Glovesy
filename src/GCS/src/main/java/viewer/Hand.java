package viewer;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class Hand extends Group {
    Rotate r;
    Transform t = new Rotate();

    public Hand(int startX, int startY) {

        // Create all nodes in this instance of the hand object at the top left of the screen.

        Sphere leftHandBase = new Sphere(10);
        leftHandBase.setLayoutX(-30);
        leftHandBase.setLayoutY(100);
        util.setColor(leftHandBase, 1.0, 1.0, 0.0);

        this.getChildren().add(leftHandBase);
        this.getChildren().add(new Finger(0.6, - 60, 40));
        this.getChildren().add(new Finger(0.8, - 30, 10));
        this.getChildren().add(new Finger(1.0, 0, 0));
        this.getChildren().add(new Finger(0.8, 30, 10));
        this.getChildren().add(new Finger(0.6, 60, 20));

        Sphere rightHandBase = new Sphere(10);
        rightHandBase.setLayoutX(30);
        rightHandBase.setLayoutY(100);
        util.setColor(rightHandBase, 1.0, 1.0, 0.0);

        this.getChildren().add(rightHandBase);

        int size = this.getChildren().size();
        Line edge;
        edge = util.addEdge(this.getChildren().get(0), this.getChildren().get(size - 1));
        this.getChildren().add(edge);

        Node curr;
        Node last;
        // loop through nodes and create edges
        for (int i = 1; i < size; i++) {
            curr = this.getChildren().get(i) instanceof Finger ? ((Finger) this.getChildren().get(i)).getKnuckle() : this.getChildren().get(i);
            last = this.getChildren().get(i - 1) instanceof Finger ? ((Finger) this.getChildren().get(i - 1)).getKnuckle() : this.getChildren().get(i - 1);

            edge = util.addEdge(curr, last);
            this.getChildren().add(edge);
        }

        // Move this instance of the hand to the provided xy coordinates
        this.translateXProperty().set(startX);
        this.translateYProperty().set(startY);
    }

    public void rotateOnXAxis(int ang) {
        r = new Rotate(ang, Rotate.X_AXIS);
        t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().addAll(t);
    }

    public void rotateOnYAxis(int ang) {
        r = new Rotate(ang, Rotate.Y_AXIS);
        t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().addAll(t);
    }
}
