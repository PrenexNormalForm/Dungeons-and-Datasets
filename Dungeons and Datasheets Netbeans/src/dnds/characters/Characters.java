package dnds.characters;

public class Characters {
    
    private int[] STATS;
    private int STRENGTH = 0;
    private int DEXTERITY = 1;
    private int CONSTITUTION = 2;
    private int INTELLIGENCE = 3;
    private int WISDOM = 4;
    private int CHARISMA = 5;
    private int STANDARD_STRENGTH = 15;
    private int STANDARD_DEXTERITY = 14;
    private int STANDARD_CONSTITUTION = 13;
    private int STANDARD_INTELLIGENCE = 12;
    private int STANDARD_WISDOM = 10;
    private int STANDARD_CHARISMA = 8;

    public Characters(String[] _class, int[] stats, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma) {
        //this.CLASSES = _class;
        this.STATS[STRENGTH] = _strength;
        this.STATS[DEXTERITY] = _dex;
        this.STATS[CONSTITUTION] = _constitution;
        this.STATS[INTELLIGENCE] = _intelligence;
        this.STATS[WISDOM] = _wisdom;
        this.STATS[CHARISMA] = _charisma;

    }
    /*
    public Characters createDefaultCharacter(String[] _class, int _strength, int _dex, int _constitution, int _intelligence, int _wisdom, int _charisma) {
        
       //To do: Implement proper default Character with use of Arrays and predefined enumerated constants at the top.
        
    }
    
*/
}
