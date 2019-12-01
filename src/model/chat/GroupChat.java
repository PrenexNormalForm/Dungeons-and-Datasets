package model.chat;

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
    static volatile boolean FINISHED = false;
    static int PORT = 4567;
    static String GROUP = "238.245.3.7";
    static int DEFAULT_TIME = 5;
    static InetAddress ADDRESS;
    static MulticastSocket SOCKET;

    public static void startServer () {
        try {
            GroupChat.ADDRESS = InetAddress.getByName(GroupChat.GROUP);
            GroupChat.SOCKET = new MulticastSocket(GroupChat.PORT);
            GroupChat.SOCKET.setTimeToLive(GroupChat.DEFAULT_TIME);
            GroupChat.SOCKET.joinGroup(GroupChat.ADDRESS);
            Thread t = new Thread(new ReadThread(GroupChat.SOCKET, GroupChat.ADDRESS, GroupChat.PORT));
            t.start();
        } catch (SocketException se) {
            System.out.println("Error creating socket");
            se.printStackTrace();
        } catch (IOException ie) {
            System.out.println("Error reading/writing from/to socket");
            ie.printStackTrace();
        }
    }
    //method used to read in input from the read thread
    public static void readInput(String _message){
        //adds the comment into the chatlog
        view.ChatLog.addComment(_message);
    }
    //method used to send messages with the users name attached
    public static void sendMessage (String _userInput) throws IOException {
            message(_userInput);
        }
    //generic messaging method
    private static void message(String _message) throws IOException{
        //pulls the message into a byte buffer
        byte[] buffer = _message.getBytes();
        //creates a datagram packet using the new byte buffer and the address and port
        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, GroupChat.ADDRESS, PORT);
        //sends the packet via the group chat socket
        GroupChat.SOCKET.send(datagram);
    }
}
