package dnds.model.characters;
/*
Last updated Oct 10, 2019

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
    
    // =================== GETTERS ===============================//

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
    
    // =================== SETTERS ===============================//

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
