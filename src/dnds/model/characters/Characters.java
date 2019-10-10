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
    private Stats STATS;
    private static final String DEFAULT_NAME = "YEEEEEHAWWWWWW";

    /**
     * The overridden constructor for creating a basic character (Will update
     * with more complex fields such as background, etc. at a later date.)
     */
    public Characters(String _class, String _name, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma) {
        this.CLASS = _class;
        this.NAME = _name;
        this.STATS = new Stats(_strength, _dex, _constitution, _intelligence, _wisdom, _charisma);
    }
    /**
     * The default character creation for someone just starting and just
     * wants to get started in a campaign. This method creates a default
     * Barbarian named "YEEEEEHAWWWWWW" (This will be updated with a random
     * name generator at a later date) with standard stat rolls.
     */
    public Characters(){
        this.CLASS = CharacterClass.BARBARIAN.name();
        this.NAME = DEFAULT_NAME;
        this.STATS = new Stats();
    }
    /**
     * Method to print all fields of the Characters class.
     * @return The character details.
     */
    @Override
    public String toString() {
        return "Class:" + this.getCharacterClass() + "\nName:" + this.getName() + "\nStrength:" + this.getStrength() + "\nDex:" + this.getDex() + "\nConstitution:" + this.getConstitution() + "\nIntelligence:" + this.getIntelligence() + "\nWisdom:" + this.getWisdom() + "\nCharisma:" + this.getCharisma();
    }

    // =================== GETTERS ===============================//

    public String getCharacterClass() {
        return this.CLASS;
    }

    public String getName() {
        return this.NAME;
    }

    public int getStrength() {
        return this.STATS.getStrength();
    }

    public int getDex() {
        return this.STATS.getDex();
    }
    public int getConstitution() {
        return this.STATS.getConstitution();
    }

    public int getIntelligence() {
        return this.STATS.getIntelligence();
    }

    public int getWisdom() {
        return this.STATS.getWisdom();
    }

    public int getCharisma() {
        return this.STATS.getCharisma();
    }
    
    // =================== SETTERS ===============================//

    public void setCharacterClass(String _class) {
        this.CLASS = _class;
    }

    public void setName(String _name) {
        this.NAME = _name;
    }

    public void setStrength(int _strength) {
        this.STATS.setStrength(_strength);
    }

    public void setDex(int _dex) {
        this.STATS.setDex(_dex);
    }

    public void setConstitution(int _constitution) {
        this.STATS.setConstitution(_constitution);
    }

    public void setIntelligence(int _intelligence) {
        this.STATS.setIntelligence(_intelligence);
    }

    public void setWisdom(int _wisdom) {
        this.STATS.setWisdom(_wisdom);
    }

    public void setCharisma(int _charisma) {
        this.STATS.setCharisma(_charisma);
    }
}
