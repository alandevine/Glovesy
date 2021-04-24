package OSHandler;

import java.util.concurrent.ConcurrentHashMap;

public class GloveState {
    private final ConcurrentHashMap<String, Double> state;

    public GloveState() {
        this.state = new ConcurrentHashMap<>();
        this.state.put("gyroX",   0.0);
        this.state.put("gyroY",   0.0);
        this.state.put("gyroZ",   0.0);
        this.state.put("thumb",   0.0);
        this.state.put("index1",  0.0);
        this.state.put("index2",  0.0);
        this.state.put("middle1", 0.0);
        this.state.put("middle2", 0.0);
        this.state.put("ring1",   0.0);
        this.state.put("ring2",   0.0);
        this.state.put("pinky",   0.0);
    }

    public void updateState(double[] sensorData) {
        this.state.put("gyroX",   sensorData[0]);
        this.state.put("gyroY",   sensorData[1]);
        this.state.put("gyroZ",   sensorData[2]);
        this.state.put("thumb",   sensorData[3]);
        this.state.put("index1",  sensorData[4]);
        this.state.put("index2",  sensorData[5]);
        this.state.put("middle1", sensorData[6]);
        this.state.put("middle2", sensorData[7]);
        this.state.put("ring1",   sensorData[8]);
        this.state.put("ring2",   sensorData[9]);
        this.state.put("pinky",   sensorData[10]);
    }

    public ConcurrentHashMap<String, Double> getState() {
        return this.state;
    }
}