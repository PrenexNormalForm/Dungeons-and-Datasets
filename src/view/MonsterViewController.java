package view;
/*
Last updated December 10, 2019

This is the view controller for the monster window.

Contributors:
Jonathan Bacon
 */
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
/**
 * This is the view controller for the monster window.
 *
 */
public class MonsterViewController {

    @FXML
    private TextArea MonsterInfoDisplay;

    @FXML
    private void initialize(){
        //this will set the info display area to a value that is in constants
        MonsterInfoDisplay.setText(Constants.MONSTER);
    }


}
