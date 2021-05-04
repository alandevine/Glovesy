package OSHandler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GloveStateTest {

    @Test
    void updateStateArrayTooSmall() {
        GloveState gloveState = new GloveState();
        String[] testData = new String[5];
        assertThrows(IllegalArgumentException.class, () -> gloveState.updateState(testData));
    }
    @Test
    void updateStateArrayTooLarge() {
        GloveState gloveState = new GloveState();
        String[] testData = new String[20];
        assertThrows(IllegalArgumentException.class, () -> gloveState.updateState(testData));
    }
}