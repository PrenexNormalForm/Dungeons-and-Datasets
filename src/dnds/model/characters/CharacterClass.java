package dnds.model.characters;
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

    /**
     * The printable name of the Character Class
     */
    private final String name;

    /**
     * Returns a string representation of the character class.
     *
     * @return The character class represented as a string.
     */
    @Override
    public String toString() {
        return name;
    }

    private CharacterClass(String _name) {
        this.name = _name;
    }
}
