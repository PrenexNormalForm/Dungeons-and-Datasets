package controller;
/*
Last updated November 14, 2019

This abstract class defines the interface for the connecter between the
controller and the view.

Contributors:
Eva Moniz
 */

import java.io.File;
import java.util.UUID;
import model.characters.CharacterData;
import model.characters.CharacterProperty;

/**
 * This abstract class defines the interface for the connecter between the
 * controller and the view.
 *
 * @author Eva Moniz
 */
public abstract class ViewConnector {

    /**
     * Sends character data to the view.
     *
     * @param _data The character data to send.
     */
    public abstract void sendCharacterData(CharacterData _data);

    /**
     * Notifies the controller that the user has requested to create a new
     * character.
     */
    public abstract void inputCreateCharacter();

    /**
     * Notifies the controller that the user has requested a character property
     * be updated with the given value.
     *
     * @param _uuid The UUID of the character
     * @param _property The character property to update
     * @param _value The new value of the property
     */
    public abstract void inputCharacterProperty(UUID _uuid, CharacterProperty _property, Object _value);

    /**
     * Notifies the controller that the user has requested a die to be rolled a
     * given number of times.
     *
     * @param _repetitions The repetitions requested by the user
     * @param _die The die to roll requested by the user
     */
    public abstract String inputRollDye(int _repetitions, int _die);

    /**
     * Notifies the controller that the user has requested to save a character.
     *
     * @param _characterUUID The character to save
     * @param _filePath The file path to save the character to
     */
    public abstract void inputSaveAs(UUID _characterUUID, File _file);

    /**
     * Notifies the controller that the user has requested to load a character
     * file.
     *
     * @param _filePath The file path of the saved character
     */
    public abstract void inputLoadFile(File _file);

    /**
     * Sends a message for the view to display in the chat.
     *
     * @param _message The message to display.
     */
    public abstract void displayMessage(String _message);

}
