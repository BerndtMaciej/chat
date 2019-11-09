package multicast;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

@Slf4j
public class MulticastUdpClient {
    final static String INET_ADDR = "224.0.0.5";
    final static int PORT = 5858;

    public static void main(String[] args) throws IOException {
        String login = "";
        if (args.length != 0) {
            login = args[0];
            System.out.println("Zalogowano: " + sendMessage(login));

        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Program umożliwia wysyłanie wiadomości typu multicast na " + INET_ADDR + ":" + PORT);

            while (true) {
                System.out.print("Wprowadź swój nick: ");
                login = scanner.nextLine();

               if (login.length() != 0) {
                   System.out.println(sendMessage(login));
                    break;
                }

            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Możesz teraz wysyłać wiadomości na chacie");
        String message2 = "";
        while (!message2.equals("exit")) {
            String nick2 = login + ":";
            System.out.print(nick2);
            message2 = nick2 + ' ' + scanner.nextLine();
            System.out.println("Send: " + sendMessage2(message2));
        }
    }

    static boolean sendMessage(String message) throws UnknownHostException {
        InetAddress addr = InetAddress.getByName(INET_ADDR);
        try (DatagramSocket serverSocket = new DatagramSocket()) {
            DatagramPacket msgPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, PORT);
            serverSocket.send(msgPacket);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    static boolean sendMessage2(String message2) throws UnknownHostException {
        InetAddress addr = InetAddress.getByName(INET_ADDR);
        try (DatagramSocket serverSocket = new DatagramSocket()) {
            DatagramPacket msgPacket = new DatagramPacket(message2.getBytes(), message2.getBytes().length, addr, PORT);
            serverSocket.send(msgPacket);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
