package viewer;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class utilTest {

    // setColor Tests
    @Test
    void testColorAssignment() {
        Sphere a = new Sphere();
        util.setColor(a, 1.0, 1.0, 1.0);

        PhongMaterial mat = (PhongMaterial) a.getMaterial();
        Color color = mat.getDiffuseColor();
        Assertions.assertEquals(color.getRed(), 1.0);
        Assertions.assertEquals(color.getGreen(), 1.0);
        Assertions.assertEquals(color.getBlue(), 1.0);
    }
}