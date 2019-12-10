package view;
/*
Last Updated: December 3, 2019

Contributors:
Jonathan Bacon

A chat log object that will store data for the GroupChat object
 */

import java.util.ArrayList;
import javafx.application.Platform;

/**
 * Class used for handling the chat log
 */
public class ChatLog {
    static ArrayList<String> log = new ArrayList<>();

    //method used for adding a commend to the log and then telling the view to update
    public static void addComment(String _comment){
        log.add(_comment);
        //this line tells the thread of the UI to run the update chat method when it can
        Platform.runLater( () -> MainViewController.updateChat());
    }

    // =================== GETTERS ===============================//
    public static ArrayList<String> getLog(){
        return log;
    }

    // =================== SETTERS ===============================//
    public static void setLog(ArrayList<String> _log){
        log = _log;
    }
}
