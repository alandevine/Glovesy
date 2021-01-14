package viewer;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;

public class Hand extends Group {

    public Hand(int width, int height) {

        Sphere leftHandBase = new Sphere(10);
        leftHandBase.setLayoutX(width - 30);
        leftHandBase.setLayoutY(height + 100);
        util.setColor(leftHandBase, 1.0, 1.0, 0.0);

        this.getChildren().add(leftHandBase);
        this.getChildren().add(new Finger(0.6, width - 60, height + 40));
        this.getChildren().add(new Finger(0.8, width - 30, height + 10));
        this.getChildren().add(new Finger(1.0, width, height));
        this.getChildren().add(new Finger(0.8, width + 30, height + 10));
        this.getChildren().add(new Finger(0.6, width + 60, height + 20));

        Sphere rightHandBase = new Sphere(10);
        rightHandBase.setLayoutX(width + 30);
        rightHandBase.setLayoutY(height + 100);
        util.setColor(rightHandBase, 1.0, 1.0, 0.0);

        this.getChildren().add(rightHandBase);

        int size = this.getChildren().size();
        Line edge;
        edge = util.addEdge(this.getChildren().get(0), this.getChildren().get(size - 1));
        this.getChildren().add(edge);

        Node curr;
        Node last;
        // loop through
        for (int i = 1; i < size; i++) {
            curr = this.getChildren().get(i) instanceof Finger ? ((Finger) this.getChildren().get(i)).getKnuckle() : this.getChildren().get(i);
            last = this.getChildren().get(i - 1) instanceof Finger ? ((Finger) this.getChildren().get(i - 1)).getKnuckle() : this.getChildren().get(i - 1);

            edge = util.addEdge(curr, last);
            this.getChildren().add(edge);
        }
    }

    public Group getHand() {
        return this;
    }
}
