package OSHandler;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

public class InputManager {
    Robot robot;
    PointerInfo mouseInfo;

    public InputManager() throws AWTException {
        robot = new Robot();
        mouseInfo = MouseInfo.getPointerInfo();
    }

    /**
     * Method for executing sequences of key presses
     * @param keySequence Array of strings which follow the format $ACTION,$KEYCODE, where action could be press, or release
     */
    public void executeKeySequence(String[] keySequence) {
        String key;
        String action;

        if (!parseKeySequence(keySequence)) {
            System.out.println("bad ks m8");
            return;
        }

        for (String line : keySequence) {
            action = line.split(",")[0];
            key = line.split(",")[1];

            if (action.equals("press"))
                robot.keyPress(Integer.parseInt(key));
            else
                robot.keyRelease(Integer.parseInt(key));
        }
    }

    /**
     * Simple syntax checker for key sequences
     * @param keySequence Array of strings which follow the format $ACTION,$KEYCODE, where action could be press, or release.
     * @return Boolean denoting success or failure.
     */
    private boolean parseKeySequence(String[] keySequence) {
        ArrayList<String> keys = new ArrayList<>();
        String key;
        String action;

        for (String line : keySequence) {
            System.out.println(line);
            action = line.split(",")[0];
            key = line.split(",")[1];

            if (!action.equals("press") && !action.equals("release"))
                return false;

            if (action == "press") {
                keys.add(key);
                continue;
            }

            if (keys.contains(key)) {
                keys.remove(key);
                continue;
            }
        }
        
        return keys.size() == 0;
    }

    public void moveMouse(int delta_x, int delta_y) {
        int x = mouseInfo.getLocation().x + delta_x;
        int y = mouseInfo.getLocation().y + delta_y;

        robot.mouseMove(x, y);
    }

    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(200);
    }
}