package OSHandler;

import com.fazecast.jSerialComm.SerialPort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SerialComms {

    private SerialPort arduino = null;
    public static int PACKET_BYTE_LENGTH = 16;

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

        this.arduino.addDataListener(new SerialPortListener());
    }

    public InputStream getInputStream() {
        return this.arduino.getInputStream();
    }

    public static void main(String[] args) throws IOException {
        SerialComms comms = new SerialComms();
    }
}
