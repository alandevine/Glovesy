package viewer;

import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class utilTest {

    @Test
    void setColor() {
    }

    // addEdge Tests
    @Test
    void testStartEndPositions() {
        Sphere a = new Sphere();
        a.setLayoutX(0.0);
        a.setLayoutY(0.0);

        Sphere b = new Sphere();
        b.setLayoutX(20.0);
        b.setLayoutY(20.0);

        Line edge = util.addEdge(a, b);
        Assertions.assertEquals(edge.getStartX(), a.getLayoutX());
        Assertions.assertEquals(edge.getStartY(), a.getLayoutY());
        Assertions.assertEquals(edge.getEndX(), b.getLayoutX());
        Assertions.assertEquals(edge.getEndY(), b.getLayoutY());
    }
}