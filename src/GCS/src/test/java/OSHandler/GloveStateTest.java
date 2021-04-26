package OSHandler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GloveStateTest {

    @Test
    void updateStateArrayTooSmall() {
        GloveState gloveState = new GloveState();
        double[] testData = new double[5];
        assertThrows(IllegalArgumentException.class, () -> gloveState.updateState(testData));
    }
    @Test
    void updateStateArrayTooLarge() {
        GloveState gloveState = new GloveState();
        double[] testData = new double[20];
        assertThrows(IllegalArgumentException.class, () -> gloveState.updateState(testData));
    }
}