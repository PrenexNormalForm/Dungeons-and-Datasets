package model.characters;
/*
Last Updated: November 5, 2019

The Character class that will be used for Character Creation and updating
character stats and information throughout a campaign.

Contributors:
Brandon Pozil
Jonathan Bacon
Eva Moniz
 */

import java.util.HashMap;
import java.util.UUID;
import model.items.Item;

/**
 * The Characters class (God forbid you actually call it Character) that will a
 * player will start and update throughout a campaign. Includes all stats needed
 * for basic character creation baring some more complex Strings to be
 * integrated at a later point in time. Declared class constants are used for
 * default character creation (Discussed below).
 */
public class Characters {

    private static final int MAX_LEVEL = 20;
    private static final int DEFAULT_LEVEL = 7;
    private static final String DEFAULT_NAME = "default name";

    private UUID uuid;
    private CharacterClass characterClass;
    private int level;
    private String name;
    private Stats stats;
    private Inventory inventory;

    /**
     * The overridden constructor for creating a basic character (Will update
     * with more complex fields such as background, etc. at a later date.)
     *
     * @param _class - indicates the class chosen
     * @param _name - indicates the name chosen
     * @param _strength - indicates the strength of the character
     * @param _dex - indicates the dexterity of the character
     * @param _constitution - indicates the constitution of the character
     * @param _intelligence - indicates the intelligence of the character
     * @param _wisdom - indicates the wisdom of the character
     * @param _charisma - indicates the charisma of the character
     */
    public Characters(CharacterClass _class, String _name, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma) {
        this();
        this.characterClass = _class;
        this.name = _name;
        this.stats = new Stats(_strength, _dex, _constitution, _intelligence, _wisdom, _charisma);
        this.inventory = new Inventory(_strength);
    }

    /**
     * The default character creation for someone just starting and just wants
     * to get started in a campaign. This method creates a default Barbarian
     * named "YEEEEEHAWWWWWW" (This will be updated with a random name generator
     * at a later date) with standard stat rolls.
     */
    public Characters() {
        this.uuid = UUID.randomUUID();
        this.setLevel(Characters.DEFAULT_LEVEL);
        this.characterClass = CharacterClass.BARBARIAN;
        this.name = DEFAULT_NAME;
        this.stats = new Stats();
        this.inventory = new Inventory(getStrength());
    }

    /**
     * Method to print all fields of the Characters class.
     *
     * @return The character details.
     */
    @Override
    public String toString() {
        return "\nName:" + this.getName() + "\nClass:" + this.getCharacterClass() + this.stats.toString() + this.inventory.toString();
    }

    // =================== Inventory Managers ===============================//
    public void addItem(Item _item) {
        this.inventory.addItem(_item);
    }

    public void removeItem(String _item) {
        this.inventory.removeItem(_item);
    }

    // =================== GETTERS ===============================//
    public UUID getID(){
        return this.ID;
    }

    public CharacterClass getCharacterClass() {
        return this.characterClass;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public int getStrength() {
        return this.stats.getStrength();
    }

    public int getDex() {
        return this.stats.getDex();
    }

    public int getConstitution() {
        return this.stats.getConstitution();
    }

    public int getIntelligence() {
        return this.stats.getIntelligence();
    }

    public int getWisdom() {
        return this.stats.getWisdom();
    }

    public int getCharisma() {
        return this.stats.getCharisma();
    }

    public HashMap getBag() {
        return this.inventory.getBag();
    }

    public UUID getUUID() {
        return this.uuid;
    }

    // =================== SETTERS ===============================//
    public void setCharacterClass(CharacterClass _class) {
        this.characterClass = _class;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public void setLevel(int _level) {
        if (_level < 1 || _level > Characters.MAX_LEVEL) {
            throw new IllegalArgumentException("Invalid level");
        }
        this.level = _level;
    }

    public void setStrength(int _strength) {
        this.stats.setStrength(_strength);
    }

    public void setDex(int _dex) {
        this.stats.setDex(_dex);
    }

    public void setConstitution(int _constitution) {
        this.stats.setConstitution(_constitution);
    }

    public void setIntelligence(int _intelligence) {
        this.stats.setIntelligence(_intelligence);
    }

    public void setWisdom(int _wisdom) {
        this.stats.setWisdom(_wisdom);
    }

    public void setCharisma(int _charisma) {
        this.stats.setCharisma(_charisma);
    }
}
