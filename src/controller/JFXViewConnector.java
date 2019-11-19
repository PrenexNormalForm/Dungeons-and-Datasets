package controller;
/*
Last updated November 7, 2019

This view connector connects the controller and the JavaFX view. It facilitates all communication

Contributors:
Eva Moniz
 */

import model.characters.CharacterProperty;
import java.util.UUID;
import javafx.application.Platform;
import model.characters.CharacterData;
import view.MainViewController;

/**
 * This view connector connects the controller and the JavaFX view.
 *
 * @author Eva Moniz
 */
public class JFXViewConnector extends ViewConnector {

    /**
     * The view controller associated with the primary window of the program.
     */
    private MainViewController fxController;

    /**
     * Construct the view connector and pass the main window controller.
     *
     * @param _fxController The JFX controller for the main window
     */
    public JFXViewConnector(MainViewController _fxController) {
        this.fxController = _fxController;
    }

    /**
     * Send the given data to the main window controller
     *
     * @param _data The character data to send
     */
    @Override
    public void sendCharacterData(CharacterData _data) {
        Platform.runLater(() -> this.fxController.receiveCharacterData(_data));
    }

    /**
     * Notify the controller that the user has selected to create a new
     * character.
     */
    @Override
    public void inputCreateCharacter() {
        Controller.createNewCharacter();
    }

    /**
     * Notify the controller that the user has selected to update the given
     * property of the character with the given UUID with the given new value.
     *
     * @param _uuid The character UUID
     * @param _property The property to update
     * @param _value The user-entered value of the property
     */
    @Override
    public void inputCharacterProperty(UUID _uuid, CharacterProperty _property, Object _value) {
        Controller.updateCharacterProperty(_uuid, _property, _value);
    }

    /**
     * Notify the controller that the user has requested a dice roll.
     *
     * @param _repetitions The number of times to roll
     * @param _die The die to roll
     */
    @Override
    public void rollDie(int _repetitions, int _die) {
        Controller.rollDie(_repetitions, _die);
    }

    /**
     * Send a message to the view to display in chat.
     *
     * @param _message The message to display
     */
    @Override
    public void displayMessage(String _message) {
        Platform.runLater(() -> this.fxController.displayMessage(_message));
    }

}
