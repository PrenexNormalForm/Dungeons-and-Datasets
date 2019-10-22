package characters;
/*
Last updated Oct 22, 2019

Represents the different character stats of a DnD character.

Contributors:
Brandon Pozil
Jonathan Bacon
 */

/**
 * The Stats class is used to store and handle the different stats of a character for the character class
 *
 */
public class Stats {
    private int STRENGTH;
    private int DEXTERITY;
    private int CONSTITUTION;
    private int INTELLIGENCE;
    private int WISDOM;
    private int CHARISMA;
    //Default values for stats.
    private static final int STANDARD_STRENGTH = 15;
    private static final int STANDARD_DEXTERITY = 14;
    private static final int STANDARD_CONSTITUTION = 13;
    private static final int STANDARD_INTELLIGENCE = 12;
    private static final int STANDARD_WISDOM = 10;
    private static final int STANDARD_CHARISMA = 8;

    //method for creating stats object with given values
    public Stats(int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma){
        this.STRENGTH = _strength;
        this.DEXTERITY = _dex;
        this.CONSTITUTION = _constitution;
        this.INTELLIGENCE = _intelligence;
        this.WISDOM = _wisdom;
        this.CHARISMA = _charisma;
    }
    //Method for creating stats object with default values
    public Stats(){
        this.STRENGTH = STANDARD_STRENGTH;
        this.DEXTERITY = STANDARD_DEXTERITY;
        this.CONSTITUTION = STANDARD_CONSTITUTION;
        this.INTELLIGENCE = STANDARD_INTELLIGENCE;
        this.WISDOM = STANDARD_WISDOM;
        this.CHARISMA = STANDARD_CHARISMA;
    }
    
    @Override
    public String toString(){
        return "\nStats:" + "\nStrength:" + this.STRENGTH + "\nDex:" + this.DEXTERITY + "\nConstitution:" + this.CONSTITUTION + "\nIntelligence:" + this.INTELLIGENCE + "\nWisdom:" + this.WISDOM + "\nCharisma:" + this.CHARISMA;
    }

    // =================== GETTERS ===============================//

    protected int getStrength() {
        return this.STRENGTH;
    }

    protected int getDex() {
        return this.DEXTERITY;
    }
    protected int getConstitution() {
        return this.CONSTITUTION;
    }

    protected int getIntelligence() {
        return this.INTELLIGENCE;
    }

    protected int getWisdom() {
        return this.WISDOM;
    }

    protected int getCharisma() {
        return this.CHARISMA;
    }

    // =================== SETTERS ===============================//

    protected void setStrength(int _strength) {
        this.STRENGTH = _strength;
    }

    protected void setDex(int _dex) {
        this.DEXTERITY = _dex;
    }

    protected void setConstitution(int _constitution) {
        this.CONSTITUTION = _constitution;
    }

    protected void setIntelligence(int _intelligence) {
        this.INTELLIGENCE = _intelligence;
    }

    protected void setWisdom(int _wisdom) {
        this.WISDOM = _wisdom;
    }

    protected void setCharisma(int _charisma) {
        this.CHARISMA = _charisma;
    }

}
