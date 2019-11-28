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
    private static final String DEFAULT_RACE = "human";
    private static final String DEFAULT_ALIGN = "neutral";

    private UUID uuid;
    private CharacterClass characterClass;
    private int level;
    private String name;
    private String race;
    private String align;
    private Stats stats;
    private String inventory;
    private String backstory;

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
    public Characters(CharacterClass _class, String _name, String _race, String _align, String _inventory, String _backstory, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma) {
        this();
        this.characterClass = _class;
        this.name = _name;
        this.race = _race;
        this.align = _align;
        this.stats = new Stats(_strength, _dex, _constitution, _intelligence, _wisdom, _charisma);
        this.inventory = _inventory;
        this.backstory = _backstory;
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
        this.race = DEFAULT_RACE;
        this.align = DEFAULT_ALIGN;
        this.stats = new Stats();
        this.inventory = "";
        this.backstory = "";
    }

    /**
     * Method to print all fields of the Characters class.
     *
     * @return The character details.
     */
    @Override
    public String toString() {
        return "\nName:" + this.getName() + "\nClass:" + this.getCharacterClass() + "Alignment:\n" + this.align + this.stats.toString() + "Inventory:\n" + this.inventory + "Backstory:\n" + this.backstory;
    }

    // =================== GETTERS ===============================//
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

    public UUID getUUID() {
        return this.uuid;
    }

    public String getRace(){
        return this.race;
    }

    public String getAlign(){
        return this.align;
    }

    public String getBackstory(){
        return this.backstory;
    }

    public String getInventory(){
        return this.inventory;
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

    public void setRace(String _race){
        this.race = _race;
    }

    public void setAlign(String _align){
        this.align = _align;
    }

    public void setBackstory(String _backstory){
        this.backstory = _backstory;
    }

    public void setInventory(String _inventory){
        this.inventory = _inventory;
    }
}
