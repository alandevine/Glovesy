package OSHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.AWTException;

class InputManagerTest {
    @Test
    void parseKeySequence() throws AWTException {
        InputManager inputManager = new InputManager();

        String[] simplePassingKeySequence = new String[] {
                "press,14",
                "release,14"
        };

        String[] simpleFailingKeySequence = new String[] {
                "mash,14"
        };

        String[] nonMatchingKeySequence = new String[] {
                "press,14",
                "press,15",
                "release,15"
        };

        Assertions.assertTrue(inputManager.parseKeySequence(simplePassingKeySequence));
        Assertions.assertFalse(inputManager.parseKeySequence(simpleFailingKeySequence));
        Assertions.assertFalse(inputManager.parseKeySequence(nonMatchingKeySequence));
    }
}