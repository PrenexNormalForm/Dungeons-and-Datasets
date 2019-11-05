package model.characters;
/*
Last updated November 5, 2019

Represents a snapshot of the collection of primitive information necessary to
display a DND Character. This class serves as an intermediary data type between
the model and view.

Contributors:
Eva Moniz
 */

import java.util.HashMap;
import java.util.Map;
import model.characters.Characters;

/**
 * This immutable data type represents a snapshot of the collection of strings
 * necessary to display a DND Character.
 *
 * @author Eva Moniz
 */
public class CharacterData {

    /**
     * The UUID of the Character
     */
    private final String uuid;
    /**
     * The string data representing the character
     */
    private final String name, characterClass, strength, dexterity,
            constitution, intelligence, wisdom, charisma;

    /**
     * Creates a character data object using the current state of the given
     * {@code Characters}
     *
     * @param _character The character to extract data from
     */
    public CharacterData(Characters _character) {
        //this.uuid = _character.getUuid();
        this.uuid = "uuid";
        this.name = _character.getName();
        this.characterClass = _character.getCharacterClass().toString();
        this.strength = Integer.toString(_character.getStrength());
        this.dexterity = Integer.toString(_character.getDex());
        this.constitution = Integer.toString(_character.getConstitution());
        this.intelligence = Integer.toString(_character.getIntelligence());
        this.wisdom = Integer.toString(_character.getWisdom());
        this.charisma = Integer.toString(_character.getCharisma());
    }

    //=========GETTERS==========================================================
    public String getUuid() {
        return this.uuid;
    }

    public String getName() {
        return name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public String getStrength() {
        return strength;
    }

    public String getDexterity() {
        return dexterity;
    }

    public String getConstitution() {
        return constitution;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public String getWisdom() {
        return wisdom;
    }

    public String getCharisma() {
        return charisma;
    }

}
