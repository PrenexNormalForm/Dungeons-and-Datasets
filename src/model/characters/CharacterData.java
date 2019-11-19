package model.characters;
/*
Last updated November 6, 2019

Represents a snapshot of the collection of property information necessary to
display a DND Character. This class serves as an intermediary data type between
the model and view.

Contributors:
Eva Moniz
 */

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This immutable data type represents a snapshot of the collection of
 * properties necessary to display a DND Character.
 *
 * @author Eva Moniz
 */
public class CharacterData {

    /**
     * The UUID of the Character
     */
    private final UUID uuid;
    /**
     * The viewable data representing the character
     */
    private final Map<CharacterProperty, Object> data;

    /**
     * Creates a character data object using the current state of the given
     * {@code Characters}
     *
     * @param _character The character to extract data from
     */
    public CharacterData(Characters _character) {
        this.uuid = _character.getUUID();
        this.data = new HashMap<>();

        //Read the public properties of the character as defined in
        //CharacterProperty and record them in the data map.
        for (CharacterProperty property : CharacterProperty.values()) {
            String getterName = property.getGetterName();
            Class type = property.getType();
            try {
                Method getter = Characters.class.getMethod(getterName);
                Object propertyValue = getter.invoke(_character);
                this.data.put(property, propertyValue);
            } catch (ReflectiveOperationException | SecurityException ex) {
                Logger.getLogger(CharacterData.class.getName()).log(Level.SEVERE, "Failed to find method " + getterName, ex);
            }
        }
    }

    @Override
    public String toString() {
        String s = "CharacterData{UUID=" + this.getUuid();
        s += ", data=" + this.data;
        s += "}";
        return s;
    }

    //=========GETTERS==========================================================
    public UUID getUuid() {
        return this.uuid;
    }

    public <T> T getProperty(CharacterProperty _property) {
        if (!this.data.containsKey(_property)) {
            throw new IllegalArgumentException("Invalid property!");
        }
        return (T) this.data.get(_property);
    }

}
