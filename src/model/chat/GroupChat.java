package model.chat;
/*
Last Updated: December 3, 2019

A group chat object for use with people connected to a UDP Multicast port and
communicate over it.

Contributors:
Jonathan Bacon
Brandon Pozil

 */

import java.net.*;
import java.io.*;

/**
 * This is the GroupChat object class which acts as a sender and listener for messages
 *
 */
public class GroupChat {
    //The name is volatile so that the object threads does not store a local version
    private volatile String NAME = "";
    //Port is set to because it is a UDP port
    private static final int DEFAULT_PORT = 18;
    private int PORT;
    //Group is set to 238.245.3.7 because this is a multicast IP address
    private static final String DEFAULT_GROUP = "238.245.3.0";
    private static final String GROUP_TAG = "238.245.3.";
    private static final int GROUP_MIN = 0;
    private static final int GROUP_MAX = 255;
    private String GROUP;
    //This variable is used to indicate time to live for the listener
    static int DEFAULT_TIME = 5;
    private int TIME;
    private InetAddress ADDRESS;
    private MulticastSocket SOCKET;
    private static final String ROOM_TAG = " ROOM:";
    private String ROOM;
    private Thread READ_THREAD;

    /**
     * This handles creating a GroupChat object
     * @param _username
     * @param _group
     * @param _room
     */
    public GroupChat(String _username, int _group, String _room){
        this.NAME = _username;
        this.PORT = GroupChat.DEFAULT_PORT;
        this.GROUP = groupCheck(_group);
        this.TIME = GroupChat.DEFAULT_TIME;
        this.ROOM = GroupChat.ROOM_TAG + _room;
        createThread();

    }

    /**
     * This handles creation of read Thread
     */
    private void createThread(){
        try{
            this.ADDRESS = InetAddress.getByName(this.GROUP);
            this.SOCKET = new MulticastSocket(this.PORT);
            this.SOCKET.setTimeToLive(this.TIME);
            this.SOCKET.joinGroup(this.ADDRESS);
            this.READ_THREAD = new Thread(new ReadThread(this.SOCKET, this.ADDRESS, this.PORT, this.ROOM));
        } catch (SocketException se) {
            System.out.println("Error creating socket");
            se.printStackTrace();
        } catch (IOException ie) {
            System.out.println("Error reading/writing from/to socket");
            ie.printStackTrace();
        }
    }

    /**
     * This handles starting the chat thread
     * @throws IOExcepton
     */
    public void start() throws IOException{
        this.READ_THREAD.start();
        joinMessage();
    }

    /**
     * This handles stopping the chat thread
     * @throws IOException
     */
    public void stop() throws IOException{
        this.READ_THREAD.stop();
    }


    /**
     * This verifies that the given integer is a valid port
     * @param _group
     */
    private String groupCheck(int _group){
        if(_group < GroupChat.GROUP_MIN || _group > GroupChat.GROUP_MAX){
            return GroupChat.DEFAULT_GROUP;
        } else{
            return GroupChat.GROUP_TAG + _group;
        }
    }

    /**
     * Handles reading input
     * @param _message
     * @param _room
     */
    public static void readInput(String _message, String _room){
        _message = _message.replaceAll(_room, "");
        //adds the comment into the chatlog
        view.ChatLog.addComment(_message);
    }

    /**
     * Handles sending messages
     * @param _userInput
     * @throws IOException
     */
    public void sendMessage (String _userInput) throws IOException {
            message(_userInput);
        }

    /**
     * This handles the name change message
     * @param _old
     * @throws IOException
     */
    public void nameChangedMessage(String _old) throws IOException{
        message(_old + " has changed their name to " + this.NAME);
    }

    /**
     * This handles the joining message sending
     * @throws IOException
     */
    private void joinMessage() throws IOException{
        message(this.NAME + " has joined the chat!");
    }

    /**
     * This handles the leaving message sending
     * @param _name
     * @throws IOException
     */
    private void leaveMessage(String _name) throws IOException{
        message(_name + " has left the chat!");
    }

    /**
     * This handles messages which should be prepended with the username
     * @param _message
     * @throws IOException
     */
    public void playerMessage(String _message) throws IOException{
        message(this.NAME + ": " + _message);
    }

    /**
     * This handles the generic message sending process
     * @param _message
     * @throws IOException
     */
    private void message(String _message) throws IOException{
        _message += this.ROOM;
        //pulls the message into a byte buffer
        byte[] buffer = _message.getBytes();
        //creates a datagram packet using the new byte buffer and the address and port
        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, this.ADDRESS, this.PORT);
        //sends the packet via the group chat socket
        this.SOCKET.send(datagram);
    }

    /**
     * This handles updating the username in chat
     * @param _new
     * @throws IOException
     */
    public void updateName(String _new) throws IOException{
        //updates the username with the new one
        this.NAME = _new;
    }

    /**
     * This handles the updating the chat room
     * @param _room
     * @throws IOException
     */
    private void updateRoom(String _room) throws IOException{
        leaveMessage(this.NAME);
        //updates the room with the provided room data
        this.ROOM = GroupChat.ROOM_TAG + _room;
    }

    /**
     * This handles the updating groups
     * @param _group
     */
    private void updateGroup(int _group){
        //updates the group variable with the given group if its in the correct range
        this.GROUP = groupCheck(_group);
    }

    /**
     * This handles updating the thread
     * @param _group
     * @param _room
     * @param _name
     * @throws IOException
     * @throws InterruptedException
     */
    public void update(int _group, String _room, String _name) throws IOException, InterruptedException{
        updateRoom(_room);
        updateGroup(_group);
        //sends a leaving message with the provided name
        leaveMessage(_name);
        //calls the method to stop the read thread
        stop();
        //gives time for the thread to fully stop
        Thread.sleep(1000);
        //creates a new read thread
        createThread();
        //starts the new read thread
        this.READ_THREAD.start();
        //sends a joining message to the new chat
        joinMessage();
    }

}
