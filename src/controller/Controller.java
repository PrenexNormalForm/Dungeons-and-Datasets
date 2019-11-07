package controller;
/*
Last updated November 7, 2019

The primary controller class for the program.

Contributors:
Eva Moniz
 */

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.characters.CharacterData;
import model.characters.CharacterProperty;
import model.characters.Characters;

/**
 * The primary controller class for the program. This class should not be
 * instantiated.
 *
 * @author Eva Moniz
 */
public class Controller {

    /**
     * The map of all open characters in the program, by UUID.
     */
    private static Map<UUID, Characters> openCharacters = new HashMap<>();
    /**
     * The connector to the view.
     */
    private static ViewConnector viewConnector;

    /**
     * Create a new character and add it to the open characters map.
     */
    public static void createNewCharacter() {
        Characters character = new Characters();
        Controller.openCharacters.put(character.getUUID(), character);
        Controller.updateCharacterView(character);
    }

    /**
     * Update the given property of the character with the given UUID with the
     * given value.
     *
     * @param _uuid The UUID of the character
     * @param _property The property to update
     * @param _value The new value of the property
     */
    public static void updateCharacterProperty(UUID _uuid, CharacterProperty _property, Object _value) {
        Characters character = Controller.openCharacters.get(_uuid);
        try {
            //Obtain the setter method for the property and invoke it with the value
            Method setter = Characters.class.getMethod(_property.getSetterName(), _property.getType());
            setter.invoke(character, _value);

            //Notify the view of the change
            Controller.updateCharacterView(character);
        } catch (ReflectiveOperationException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, _property.toString(), ex);
        }
    }

    /**
     * Send a snapshot of character data to the view.
     *
     * @param _character The character to update in the view
     */
    private static void updateCharacterView(Characters _character) {
        CharacterData data = new CharacterData(_character);
        Controller.viewConnector.sendCharacterData(data);
    }

    //===============SETTERS====================================================
    public static void setViewConnector(ViewConnector _viewConnector) {
        Controller.viewConnector = _viewConnector;
    }
}
