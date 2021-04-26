package OSHandler;

import com.fazecast.jSerialComm.SerialPort;

import java.io.FileNotFoundException;

public class SerialComms {

    private SerialPort arduino = null;
    private static int PACKET_BYTE_LENGTH = 64;
    private GloveState gloveState = new GloveState();

    public GloveState getGloveState() {
        return this.gloveState;
    }

    public static int getPacketByteLength() {
        return PACKET_BYTE_LENGTH;
    }

    public SerialComms() throws FileNotFoundException {
        SerialPort[] ports = SerialPort.getCommPorts();
        String deviceName = "Feather M0";

        int devIdx = -1;

        for (int i = 0; i < ports.length; i++) {
            if (ports[i].getDescriptivePortName().contains(deviceName)) {
                devIdx = i;
                break;
            }
        }

        for (int i = 0; i < ports.length; i++) {

            String portName = ports[i].getDescriptivePortName();
            System.out.println(ports[i].getSystemPortName() + ": " + portName + ": " + i);

            if (portName.contains(deviceName)) {
                arduino = ports[i];
                break;
            }
        }

        if (devIdx == -1)
            throw new FileNotFoundException("Serial Port not found");

        this.arduino.setComPortParameters(
                115200,    // Baud Rate
                16,         // Data Bits
                1,          // Stop Bits
                0             // Parity Bit
        );

        this.arduino.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        this.arduino.openPort();

        this.arduino.addDataListener(new SerialPortListener(gloveState));
    }
}
