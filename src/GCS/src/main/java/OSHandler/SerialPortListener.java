package OSHandler;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public final class SerialPortListener implements SerialPortMessageListener {

    private final GloveState gloveState;
    private boolean captureEvents = false;

    public SerialPortListener(GloveState gloveState) {
        this.gloveState = gloveState;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public byte[] getMessageDelimiter() {
        return new byte[] { (byte) 0x0A };
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return true;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] receivedData = event.getReceivedData();
        String input = new String(receivedData).replace("\n", "");
        this.gloveState.updateState(input.split(","));
    }
}
