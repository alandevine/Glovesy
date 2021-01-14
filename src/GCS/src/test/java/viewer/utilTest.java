package viewer;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class utilTest {

    private Sphere a;
    private Sphere b;

    // setColor Tests
    @Test
    void testColorAssignment() {
        a = new Sphere();
        util.setColor(a, 1.0, 1.0, 1.0);

        PhongMaterial mat = (PhongMaterial) a.getMaterial();
        Color color = mat.getDiffuseColor();
        Assertions.assertEquals(color.getRed(), 1.0);
        Assertions.assertEquals(color.getGreen(), 1.0);
        Assertions.assertEquals(color.getBlue(), 1.0);
    }

    // addEdge Tests
    @Test
    void testStartEndPositions() {
        a = new Sphere();
        a.setLayoutX(0.0);
        a.setLayoutY(0.0);

        b = new Sphere();
        b.setLayoutX(20.0);
        b.setLayoutY(20.0);

        Line edge = util.addEdge(a, b);
        Assertions.assertEquals(edge.getStartX(), a.getLayoutX());
        Assertions.assertEquals(edge.getStartY(), a.getLayoutY());
        Assertions.assertEquals(edge.getEndX(), b.getLayoutX());
        Assertions.assertEquals(edge.getEndY(), b.getLayoutY());
    }
}