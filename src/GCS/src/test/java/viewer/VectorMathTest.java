package viewer;

import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static viewer.VectorMath.rotateAboutPoint;

class VectorMathTest {

    @Test
    void getAngleYZNormal() {
        Point3D a = new Point3D(0, 0, 0);
        Point3D b = new Point3D(0, 0, 1);
        double angle = VectorMath.getAngleYZ(a, b);
        assertEquals(angle, 90d);
    }

    @Test
    void getAngleYZ45() {
        Point3D a = new Point3D(0, 0, 0);
        Point3D b = new Point3D(0, 1, 1);
        double angle = VectorMath.getAngleYZ(a, b);
        assertEquals(angle, 45d);
    }

    @Test
    void getAngleXY() {
    }

    /**
     * Test the distance from the subject to the after rotation.
     */
    @Test
    void rotateTestLength() {
        double angle = 90;
        Point3D pivot   = new Point3D(0, 0, 0);
        Point3D subject = new Point3D(1, 1, 1);

        double lengthPreTranslate = pivot.distance(subject);
        Point3D newPoint = rotateAboutPoint(angle, pivot, subject);
        assertEquals(lengthPreTranslate, pivot.distance(newPoint));
    }
}