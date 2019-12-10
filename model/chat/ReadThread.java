package model.chat;
/*
Last Updated: December 3, 2019

The ReadThread is a threadable class which handles incoming UDP messages and reports them back

Contributors:
Brandon Pozil
Jonathan Bacon
 */
import java.io.*;
import java.net.*;

class ReadThread implements Runnable {

    private final MulticastSocket socket;
    private volatile InetAddress group;
    private volatile String room;
    private final int port;
    private static final int MAX_LEN = 1024;

    /**
     * This creates a read thread
     * @param _socket
     * @param _group
     * @param _port
     * @param _room
     */
    ReadThread(MulticastSocket _socket, InetAddress _group, int _port, String _room) {
        this.socket = _socket;
        this.group = _group;
        this.port = _port;
        this.room = _room;
    }

    /**
     * This handles launching the read thread.
     */
    public void run() {
        while (true) {
            byte[] buffer = new byte[ReadThread.MAX_LEN];
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
            String message;
            try {
                socket.receive(datagram);
                message = new String(datagram.getData(), 0, datagram.getLength(), "UTF-8");
                if(message.endsWith(room)){
                GroupChat.readInput(message, this.room);
                } else {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Socket closed!");
            }
        }
    }
}
