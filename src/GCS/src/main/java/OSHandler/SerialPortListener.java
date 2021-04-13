package OSHandler;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

import java.nio.charset.StandardCharsets;

public class SerialPortListener implements SerialPortPacketListener {

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public int getPacketSize() {
        return SerialComms.PACKET_BYTE_LENGTH;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] newData = event.getReceivedData();
        String str = new String(newData).split("\n", 2)[0].replaceAll("\\s+", "");
        int byteSize = 0;
        byteSize = str.getBytes(StandardCharsets.UTF_8).length;
        if (byteSize == this.getPacketSize()) {
            //System.out.println("(Received data of size: " + byteSize + ")" + str);
            System.out.println("Received data: " + str);
        }
    }
}
