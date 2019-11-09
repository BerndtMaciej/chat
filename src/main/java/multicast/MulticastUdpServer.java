package multicast;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
@Slf4j
public class MulticastUdpServer {
    final static String INET_ADDR = "224.0.0.5";
    final static int PORT = 5858;

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(INET_ADDR);

        byte[] buf = new byte[256];

        try (MulticastSocket clientSocket = new MulticastSocket(PORT)) {
            clientSocket.joinGroup(address);

            while (true) {
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);

                String receivedMessage = new String(buf, 0, msgPacket.getLength());
                if (!receivedMessage.contains(":")) {
                    System.out.println(receivedMessage + " dołączył do czata");
                    log.info(receivedMessage + " dołączył do czata");
                } else {
                    System.out.println(receivedMessage);
                    log.info(receivedMessage);
                }

                if (receivedMessage.equals("end")) {
                    System.out.print("Server closing...");
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
