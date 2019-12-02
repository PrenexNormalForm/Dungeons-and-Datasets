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
    /*These two are volatile so that the thread does not store a local
      version of them and instead checks the master version
    */
    static volatile boolean FINISHED = false;
    static volatile String NAME = "";
    //Port is set to 4567 because it is a UDP port
    static int PORT = 4567;
    //Group is set to 238.245.3.7 because this is a multicast IP address
    static String GROUP = "238.245.3.7";
    //This variable is used to indicate time to live for the listener
    static int DEFAULT_TIME = 5;
    static InetAddress ADDRESS;
    static MulticastSocket SOCKET;

    /**
     * This handles starting the chat server and setting
     * @param _username
     */
    public static void startServer (String _username) {
        try {
            GroupChat.NAME = _username;
            GroupChat.ADDRESS = InetAddress.getByName(GroupChat.GROUP);
            GroupChat.SOCKET = new MulticastSocket(GroupChat.PORT);
            GroupChat.SOCKET.setTimeToLive(GroupChat.DEFAULT_TIME);
            GroupChat.SOCKET.joinGroup(GroupChat.ADDRESS);
            Thread t = new Thread(new ReadThread(GroupChat.SOCKET, GroupChat.ADDRESS, GroupChat.PORT));
            t.start();
            joinMessage();
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
    /**
     * This handles the joining message sending
     * @throws IOException
     */
    private static void joinMessage() throws IOException{
        message(GroupChat.NAME + " has joined the chat!");
    }
    /**
     * This handles messages which should be prepended with the username
     * @param _message
     * @throws IOException
     */
    public static void playerMessage(String _message) throws IOException{
        message(GroupChat.NAME + ": " + _message);
    }
    /**
     * This handles the generic message sending process
     * @param _message
     * @throws IOException
     */
    private static void message(String _message) throws IOException{
        //pulls the message into a byte buffer
        byte[] buffer = _message.getBytes();
        //creates a datagram packet using the new byte buffer and the address and port
        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, GroupChat.ADDRESS, PORT);
        //sends the packet via the group chat socket
        GroupChat.SOCKET.send(datagram);
    }
    /**
     * This handles updating the username in chat
     * @param _new
     * @throws IOException
     */
    public static void updateName(String _new) throws IOException{
        message(GroupChat.NAME + " has changed their name to " + _new);
        GroupChat.NAME = _new;
    }
}
