package view;
/*
Last updated 10/08/2019

This is the view controller for the disconnected view (disconnected.fxml) of the
ServerPane.

Contributors:
Eva Moniz
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * This is the view controller for the disconnected view (disconnected.fxml) of
 * the ServerPane.
 *
 * @author Eva Moniz
 */
public class ServerPaneDisconnectedController {

    @FXML
    private Button startServerButton;

    /**
     * Constructs the controller. This should not reference any components, as
     * they have not yet been loaded.
     */
    public ServerPaneDisconnectedController() {
    }

    /**
     * Registers an action to perform when the component receives input to start
     * the server.
     *
     * @param _handler the event handler to handle the user input
     */
    public void setStartServerAction(EventHandler<ActionEvent> _handler) {
        this.startServerButton.setOnAction(_handler);
    }

    /**
     * Initializes the controller for the Disconnected view of the ServerPane
     * component.
     */
    @FXML
    private void initialize() {

    }

}
