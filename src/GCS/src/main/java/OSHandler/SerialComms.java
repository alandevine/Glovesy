package OSHandler;

import com.fazecast.jSerialComm.SerialPort;

import java.io.FileNotFoundException;

public class SerialComms implements Runnable {

    private static final int POLLING_RATE = 10;  // ms
    private final GloveState gloveState;

    public SerialComms() throws FileNotFoundException {

        this.gloveState = new GloveState();
        SerialPortListener serialPortListener = new SerialPortListener(this.gloveState);

        String deviceName = "Feather M0";

        SerialPort arduinoPort = null;
        for (SerialPort commPort : SerialPort.getCommPorts()) {
            if (commPort.getDescriptivePortName().contains(deviceName)) {
                arduinoPort = commPort;
                commPort.openPort();
                break;
            }
        }

        if (arduinoPort == null)
            throw new FileNotFoundException(String.format("Could not find device \"%s\".", deviceName));

        arduinoPort.addDataListener(serialPortListener);
    }

    public GloveState getGloveState() {
        return this.gloveState;
    }

    public void startCapture() {
        this.gloveState.startCapture();
    }

    public void stopCapture() {
        this.gloveState.stopCapture();
    }

    public boolean isCapturing() {
        return this.gloveState.isCapturing();
    }

    public static int getPollingRate() {
        return POLLING_RATE;
    }

    @Override
    public void run() {
        while (true) {

        }
    }
}