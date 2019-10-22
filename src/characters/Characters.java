package characters;
/*
Last Updated: October 22, 2019

The Character class that will be used for Character Creation and updating
character stats and information throughout a campaign.

Contributors:
Brandon Pozil
Jonathan Bacon
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
    private Inventory INVENTORY;
    private static final String DEFAULT_NAME = "YEEEEEHAWWWWWW";

    /**
     * The overridden constructor for creating a basic character (Will update
     * with more complex fields such as background, etc. at a later date.)
     * @param _class - indicates the class chosen
     * @param _name - indicates the name chosen
     * @param _strength - indicates the strength of the character
     * @param _dex - indicates the dexterity of the character
     * @param _constitution - indicates the constitution of the character
     * @param _intelligence - indicates the intelligence of the character
     * @param _wisdom - indicates the wisdom of the character
     * @param _charisma - indicates the charisma of the character
     * @param _bagSize - indicates the size of bag desired
     */
    public Characters(String _class, String _name, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma, int _bagSize) {
        this.CLASS = _class;
        this.NAME = _name;
        this.STATS = new Stats(_strength, _dex, _constitution, _intelligence, _wisdom, _charisma);
        this.INVENTORY = new Inventory(_bagSize, _strength);
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
        this.INVENTORY = new Inventory(getStrength());
    }
    /**
     * Method to print all fields of the Characters class.
     * @return The character details.
     */
    @Override
    public String toString() {
        return "Class:" + this.getCharacterClass() + "\nName:" + this.getName() + this.STATS.toString();
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
    
    public Object[][] getBag() {
        return this.INVENTORY.getBag();
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
    
    public void setBag(int _bagSize){
        this.INVENTORY.setBag(_bagSize);
    }
}
