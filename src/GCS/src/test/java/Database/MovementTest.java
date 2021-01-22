package Database;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    @Test
    void testIllegalXVector() {
        double[] x = new double[] {1d, 0d};
        double[] y = new double[] {0d, 1d, 1d};
        double[] v = new double[] {0d, 0d, 0d};

        assertThrows(IllegalArgumentException.class, () ->
                 new Movement(x, y, v)
        );
    }

    @Test
    void testIllegalYVector() {
        double[] x = new double[] {1d, 0d, 0d};
        double[] y = new double[] {0d, 1d};
        double[] v = new double[] {0d, 0d, 0d};

        assertThrows(IllegalArgumentException.class, () ->
                new Movement(x, y, v)
        );
    }

    @Test
    void testIdenticalXYVectors() {
        double[] x = new double[] {1d, 0d, 0d};
        double[] y = new double[] {1d, 0d, 0d};
        double[] v = new double[] {0d, 0d, 0d};

        assertThrows(IllegalArgumentException.class, () ->
                new Movement(x, y, v)
        );
    }

    @Test
    void testNonOrthogonalVectors() {
        double[] x = new double[] {1d, 0d, 0d};
        double[] y = new double[] {1d, 1d, 0d};
        double[] v = new double[] {0d, 0d, 0d};

        assertThrows(IllegalArgumentException.class, () ->
                new Movement(x, y, v)
        );
    }

    @Test
    void getzVectorSimpleCase() {
        double[] x = new double[] {1d, 0d, 0d};
        double[] y = new double[] {0d, 1d, 0d};
        double[] v = new double[] {0d, 0d, 0d};

        Movement m = new Movement(x, y, v);

        double[] z = new double[] {0d, 0d, 1d};
        assertTrue(Arrays.equals(m.getzVector(), z));
    }

    @Test
    void testZVectorAfterSetX() {
        double[] x = new double[] {1d, 0d, 0d};
        double[] y = new double[] {0d, 1d, 0d};
        double[] v = new double[] {0d, 0d, 0d};

        Movement m = new Movement(x, y, v);

        m.setxVector(new double[] {0d, 0d, 1d});

        assertTrue(Arrays.equals(m.getzVector(), new double[] {-1d, 0d, 0d}));
    }

    @Test
    void testZVectorAfterSetY() {
        double[] x = new double[] {1d, 0d, 0d};
        double[] y = new double[] {0d, 1d, 0d};
        double[] v = new double[] {0d, 0d, 0d};

        Movement m = new Movement(x, y, v);

        m.setyVector(new double[] {0d, 0d, 1d});

        assertTrue(Arrays.equals(m.getzVector(), new double[] {0d, -1d, 0d}));
    }
}