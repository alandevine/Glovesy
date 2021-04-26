package OSHandler;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

import java.nio.charset.StandardCharsets;

public class SerialPortListener implements SerialPortPacketListener {

    private final GloveState gs;

    public SerialPortListener(GloveState gs) {
        this.gs = gs;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public int getPacketSize() {
        return SerialComms.getPacketByteLength();
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] newData = event.getReceivedData();
        String str = new String(newData).split("\n", 2)[0].replaceAll("\\s+", "");
        int byteSize = str.getBytes(StandardCharsets.UTF_8).length;

        if (byteSize != this.getPacketSize()) {
            return;
        }

        System.out.println(str);

        String[] entry = str.split(",");
        double[] state = new double[entry.length];

        for (int i = 0; i < entry.length; i++)
            state[i] = Double.parseDouble(entry[i]);

        gs.updateState(state);
    }
}
