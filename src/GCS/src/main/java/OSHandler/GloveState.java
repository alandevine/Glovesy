package OSHandler;

import Database.StateHandler;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton for storing the state of Glovesy
 */
public class GloveState {
    private final ConcurrentHashMap<String, Double> state;
    private boolean capturingData;
    private StateHandler stateHandler;

    public GloveState() {

        this.stateHandler = new StateHandler("mongodb://127.0.0.1:27017", "glovesy");

        this.state = new ConcurrentHashMap<>();
        this.state.put("gyroX",         0.0);
        this.state.put("gyroY",         0.0);
        this.state.put("gyroZ",         0.0);
        this.state.put("velocityX",     0.0);
        this.state.put("velocityY",     0.0);
        this.state.put("velocityZ",     0.0);
        this.state.put("thumb",         0.0);
        this.state.put("indexKnuckle",  0.0);
        this.state.put("indexNeutro",   0.0);
        this.state.put("middleKnuckle", 0.0);
        this.state.put("middleNeutro",  0.0);
        this.state.put("ring",          0.0);
        this.state.put("pinky",         0.0);
        this.capturingData = false;
    }
    

    /**
     * Method for updating GloveState by unpacking an array of doubles.
     *
     * @param sensorData Array of doubles of length 11
     */
    public void updateState(String[] sensorData) {

        if (sensorData.length != 13)
            throw new IllegalArgumentException("Supplied sensor data must an array of length 14");

        this.state.put("thumb",         Double.parseDouble(sensorData[0]));
        this.state.put("indexKnuckle",  Double.parseDouble(sensorData[1]));
        this.state.put("indexNeutro",   Double.parseDouble(sensorData[2]));
        this.state.put("middleKnuckle", Double.parseDouble(sensorData[3]));
        this.state.put("middleNeutro",  Double.parseDouble(sensorData[4]));
        this.state.put("ring",          Double.parseDouble(sensorData[5]));
        this.state.put("pinky",         Double.parseDouble(sensorData[6]));
        this.state.put("velocityX",     Double.parseDouble(sensorData[7]));
        this.state.put("velocityY",     Double.parseDouble(sensorData[8]));
        this.state.put("velocityZ",     Double.parseDouble(sensorData[9]));
        this.state.put("gyroX",         Double.parseDouble(sensorData[10]));
        this.state.put("gyroY",         Double.parseDouble(sensorData[11]));
        this.state.put("gyroZ",         Double.parseDouble(sensorData[12]));

        if (capturingData)
            this.stateHandler.addState(this.state);

    }

    public ConcurrentHashMap<String, Double> getState() {
        return this.state;
    }

    public void startCapture() {
        this.capturingData = true;
    }

    public void stopCapture() {
        this.capturingData = false;
    }

    public boolean isCapturing() {
        return this.capturingData;
    }

    public double[] getMovementVector() {
        double delta_x = this.state.get("velocityX") / SerialComms.getPollingRate();
        double delta_y = this.state.get("velocityY") / SerialComms.getPollingRate();
        double delta_z = this.state.get("velocityZ") / SerialComms.getPollingRate();

        return new double[] {delta_x, delta_y, delta_z};
    }

    public double[] getRotationVector() {
        double delta_x = this.state.get("gyroX") / SerialComms.getPollingRate();
        double delta_y = this.state.get("gyroY") / SerialComms.getPollingRate();
        double delta_z = this.state.get("gyroZ") / SerialComms.getPollingRate();

        return new double[] {delta_x, delta_y, delta_z};
    }
}