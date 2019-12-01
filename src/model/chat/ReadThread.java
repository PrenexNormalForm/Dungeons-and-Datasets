package model.chat;

import java.io.*;
import java.net.*;

class ReadThread implements Runnable {

    private final MulticastSocket socket;
    private final InetAddress group;
    private final int port;
    private static final int MAX_LEN = 1024;

    ReadThread(MulticastSocket _socket, InetAddress _group, int _port) {
        this.socket = _socket;
        this.group = _group;
        this.port = _port;
    }

    public void run() {
        while (!GroupChat.FINISHED && true) {
            byte[] buffer = new byte[ReadThread.MAX_LEN];
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
            String message;
            try {
                socket.receive(datagram);
                message = new String(datagram.getData(), 0, datagram.getLength(), "UTF-8");
                GroupChat.readInput(message);
            } catch (IOException e) {
                System.out.println("Socket closed!");
            }
        }
    }
}
