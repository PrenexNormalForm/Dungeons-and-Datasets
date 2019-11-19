package ChatPrototype;

import java.net.*;
import java.io.*;
import java.util.*;

/*
Last Updated: November 19, 2019

Contributors: Brandon Pozil

A protoype chat application for use with people connected to a single WiFi
network. This will take an array of bytes and pass it to a datagram that will
then be multicasted to everyone who is "logged in" at the static IP address.
 */
public class GroupChat {

    private static final String TERMINATE = "Bye";
    static String NAME;
    static volatile boolean FINISHED = false;
    static int PORT = 4567;
    static String GROUP = "238.245.3.7";
    static int DEFAULT_TIME = 5;

    public static void startServer (String _dClassIp, int _port) {
        try {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Enter your name: ");
            NAME = userInput.nextLine();
            InetAddress _group = InetAddress.getByName(_dClassIp);
            MulticastSocket socket = new MulticastSocket(PORT);
            socket.setTimeToLive(GroupChat.DEFAULT_TIME);
            socket.joinGroup(_group);
            Thread t = new Thread(new ReadThread(socket, _group, PORT));
            t.start();
            System.out.println("Start typing messages...\n");
            readInput(GroupChat.FINISHED, socket, _group, userInput);
        } catch (SocketException se) {
            System.out.println("Error creating socket");
            se.printStackTrace();
        } catch (IOException ie) {
            System.out.println("Error reading/writing from/to socket");
            ie.printStackTrace();
        }
    }

    public static void readInput (boolean _run, MulticastSocket _socket, InetAddress _default, Scanner _userInput) throws IOException {
        while (_run = true) {
            String message = _userInput.nextLine();
            if (message.equalsIgnoreCase(GroupChat.TERMINATE)) {
                FINISHED = true;
                _socket.leaveGroup(_default);
                _socket.close();
                break;
            }
            message = NAME + ": " + message;
            byte[] buffer = message.getBytes();
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, _default, PORT);
            _socket.send(datagram);
        }

    }

    public static void main(String[] args) {
        startServer(GroupChat.GROUP, GroupChat.PORT);
    }
}
