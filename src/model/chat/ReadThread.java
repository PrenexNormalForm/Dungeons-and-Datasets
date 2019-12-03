package model.chat;

import java.io.*;
import java.net.*;
import javafx.application.Platform;

class ReadThread implements Runnable {

    private final MulticastSocket socket;
    private volatile InetAddress group;
    private volatile String room;
    private final int port;
    private static final int MAX_LEN = 1024;
    private volatile boolean exit;

    ReadThread(MulticastSocket _socket, InetAddress _group, int _port, String _room) {
        this.socket = _socket;
        this.group = _group;
        this.port = _port;
        this.room = _room;
        this.exit = false;
    }

    public void run() {
        while (!this.exit) {
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
    /**
     * This handles stopping the read thread
     */
    public void stop(){
        this.exit = false;
    }

    /**
     * This handles the changing of rooms
     */
    public void updateRoom(String _room){
        this.room = _room;
    }
}
