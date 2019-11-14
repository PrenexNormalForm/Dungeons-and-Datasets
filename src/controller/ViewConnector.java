package controller;
/*
Last updated November 14, 2019

This abstract class defines the interface for the connecter between the
controller and the view.

Contributors:
Eva Moniz
 */

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
    public abstract void rollDie(int _repetitions, int _die);

    /**
     * Sends a message for the view to display in the chat.
     *
     * @param _message The message to display.
     */
    public abstract void displayMessage(String _message);

}
