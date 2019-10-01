package characters;

public class CharacterInfo {
    private final String[] CLASSES = {"Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rouge", "Sorcerer", "Warlock", "Wizard"};
    private String NAME;
    private String CHARACTERSCLASS;
    private int STRENGTH;
    private int DEXTERITY;
    private int CONSTITUTION;
    private int INTELLIGENCE;
    private int WISDOM;
    private int CHARISMA;
    private static final int STANDARD_STRENGTH = 15;
    private static final int STANDARD_DEXTERITY = 14;
    private static final int STANDARD_CONSTITUTION = 13;
    private static final int STANDARD_INTELLIGENCE = 12;
    private static final int STANDARD_WISDOM = 10;
    private static final int STANDARD_CHARISMA = 8;

    public CharacterInfo(String _name, String _class, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma) {
        this.NAME = _class;
        this.CHARACTERSCLASS = _class;
        this.STRENGTH = _strength;
        this.DEXTERITY = _dex;
        this.CONSTITUTION = _constitution;
        this.INTELLIGENCE = _intelligence;
        this.WISDOM = _wisdom;
        this.CHARISMA = _charisma;

    }
    
    public CharacterInfo createDefaultCharacter(String _name, String _class, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma) {
        CharacterInfo newDefaultCharacter = new CharacterInfo("New Character","Barbarian",CharacterInfo.STANDARD_STRENGTH, CharacterInfo.STANDARD_DEXTERITY, CharacterInfo.STANDARD_CONSTITUTION, CharacterInfo.STANDARD_WISDOM, CharacterInfo.STANDARD_WISDOM, CharacterInfo.STANDARD_CHARISMA);
        return newDefaultCharacter;
        
    }
    
    //============ GETTERS & SETTERS =============//
    
    public String getName() {
        return this.NAME;
        
    }
    
    public String getCharacterClass() {
        return this.CHARACTERSCLASS;
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
    
    public void setName(String _name) {
        this.NAME = _name;
    }
    
    public void setCharacterClass(String _characterClass) {
        this.CHARACTERSCLASS = _characterClass;
    }
    
    public void setStrength(int _strength) {
        this.STRENGTH = _strength;
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
