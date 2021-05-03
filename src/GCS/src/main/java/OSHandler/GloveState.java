package OSHandler;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton for storing the state of Glovesy
 */
public class GloveState {
    private final ConcurrentHashMap<String, Double> state;

    public GloveState() {
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
    }

    /**
     * Method for updating GloveState by unpacking an array of doubles.
     *
     * @param sensorData Array of doubles of length 11
     */
    public void updateState(double[] sensorData) {

        if (sensorData.length != 13)
            throw new IllegalArgumentException("Supplied sensor data must an array of length 14");

        this.state.put("gyroX",         sensorData[0]);
        this.state.put("gyroY",         sensorData[1]);
        this.state.put("gyroZ",         sensorData[2]);
        this.state.put("velocityX",     sensorData[3]);
        this.state.put("velocityY",     sensorData[4]);
        this.state.put("velocityZ",     sensorData[5]);
        this.state.put("thumb",         sensorData[6]);
        this.state.put("indexKnuckle",  sensorData[7]);
        this.state.put("indexNeutro",   sensorData[8]);
        this.state.put("middleKnuckle", sensorData[9]);
        this.state.put("middleNeutro",  sensorData[10]);
        this.state.put("ring",          sensorData[11]);
        this.state.put("pinky",         sensorData[12]);
    }

    public ConcurrentHashMap<String, Double> getState() {
        return this.state;
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