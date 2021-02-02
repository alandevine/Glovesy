package viewer;

import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void rotateAboutPoint() {
    }
}