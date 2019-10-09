package dnds.model.characters;
/*
Last Updated: October 1, 2019
=======
package dnds.model.characters;
>>>>>>> 7c3c39c524ffb500631d1448bd470a12f4b25b02:Dungeons and Datasheets Netbeans/src/dnds/model/characters/Characters.java

The Character class that will be used for Character Creation and updating
character stats and information throughout a campaign.

Contributors:
Brandon Pozil
*/

/**
 * The Characters class (God forbid you actually call it Character) that will a
 * player will start and update throughout a campaign.
 * Includes all stats needed for
 * basic character creation baring some more complex Strings to be
 * integrated at a later point in time.
 * Declared class constants are used
 * for default character creation (Discussed below).
 */

public class Characters {
    private String CLASS;
    private String NAME;
    private int STRENGTH;
    private int DEXTERITY;
    private int CONSTITUTION;
    private int INTELLIGENCE;
    private int WISDOM;
    private int CHARISMA;
    private static final String DEFAULT_NAME = "YEEEEEHAWWWWWW";
    private static final int STANDARD_STRENGTH = 15;
    private static final int STANDARD_DEXTERITY = 14;
    private static final int STANDARD_CONSTITUTION = 13;
    private static final int STANDARD_INTELLIGENCE = 12;
    private static final int STANDARD_WISDOM = 10;
    private static final int STANDARD_CHARISMA = 8;

    /**
     * The overridden constructor for creating a basic character (Will update
     * with more complex fields such as background, etc. at a later date.)
     */
    public Characters(String _class, String _name, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma) {
        this.CLASS = _class;
        this.NAME = _name;
        this.STRENGTH = _strength;
        this.DEXTERITY = _dex;
        this.CONSTITUTION = _constitution;
        this.INTELLIGENCE = _intelligence;
        this.WISDOM = _wisdom;
        this.CHARISMA = _charisma;
    }
    /**
     * The default character creation for someone just starting and just
     * wants to get started in a campaign. This method creates a default
     * Barbarian named "YEEEEEHAWWWWWW" (This will be updated with a random
     * name generator at a later date) with standard stat rolls.
     * @return The default Character information found in the class
     * constants listed above.
     */
    public Characters createDefaultCharacter(String _class,String _name, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma) {
        Characters defaultCharacter = new Characters(CharacterClass.BARBARIAN.name(),Characters.DEFAULT_NAME, Characters.STANDARD_STRENGTH, Characters.STANDARD_DEXTERITY,Characters.STANDARD_CONSTITUTION,Characters.STANDARD_INTELLIGENCE,Characters.STANDARD_WISDOM,Characters.STANDARD_CHARISMA);
        return defaultCharacter;
    }
    /**
     * Method to print all fields of the Characters class.
     * @param _character
     * @return The character details.
     */
    public String toString(Characters _character) {
        return "Class:" + _character.getCharacterClass() + "Name:" + _character.getName() + "Strength:" + _character.getStrength() + "Dex:" + _character.getDex() + "Constitution:" + _character.getConstitution() + "Intelligence:" + _character.getIntelligence() + "Wisdom:" + _character.getWisdom() + "Charisma:" + _character.getCharisma();
    }

    // =================== GETTERS & SETTERS ===============================//

    public String getCharacterClass() {
        return this.CLASS;
    }

    public String getName() {
        return this.NAME;
    }

    public int getStrength() {
        return this.STRENGTH;
    }

    public int getDex() {
        return this.DEXTERITY;
    }
    public int getConstitution() {
        return this.CONSTITUTION;
    }

    public int getIntelligence() {
        return this.INTELLIGENCE;
    }

    public int getWisdom() {
        return this.WISDOM;
    }

    public int getCharisma() {
        return this.CHARISMA;
    }

    public void setCharacterClass(String _class) {
        this.CLASS = _class;
    }

    public void setName(String _name) {
        this.NAME = _name;
    }

    public void setStrength(int _strength) {
        this.STRENGTH = _strength;
    }

    public void setDex(int _dex) {
        this.DEXTERITY = _dex;
    }

    public void setConstitution(int _constitution) {
        this.CONSTITUTION = _constitution;
    }

    public void setIntelligence(int _intelligence) {
        this.INTELLIGENCE = _intelligence;
    }

    public void setWisdom(int _wisdom) {
        this.WISDOM = _wisdom;
    }

    public void setCharisma(int _charisma) {
        this.CHARISMA = _charisma;
    }
}
