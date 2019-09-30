package dnds.characters;
/*
Last updated Sept 30, 2019

Represents the different character classes of a DnD character.

Contributors:
Eva Moniz
*/

/**
 * Represents the different character classes of a DnD character.
 */
public enum CharacterClass {
    BARBARIAN("Barbarian"), 
    BARD("Bard"), 
    CLERIC("Cleric"), 
    DRUID("Druid"), 
    FIGHTER("Fighter"), 
    MONK("Monk"), 
    PALADIN("Paladin"), 
    RANGER("Ranger"), 
    ROUGE("Rouge"), 
    SORCERER("Sorcerer"), 
    WARLOCK("Warlock"), 
    WIZARD("Wizard");
    
    private String name;
    
    @Override
    public String toString() {
        return name;
    }
    
    private CharacterClass(String _name) {
        this.name = _name;
    }
}
