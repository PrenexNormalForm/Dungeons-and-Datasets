package model.characters;
/*
Last updated November 6, 2019

Represents the different character classes of a DnD character.

Contributors:
Eva Moniz
Brandon Pozil
Jonathan Bacon
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
    ROGUE("Rogue"),
    SORCERER("Sorcerer"),
    WARLOCK("Warlock"),
    WIZARD("Wizard");

    private final String string;

    private CharacterClass(String _string) {
        this.string = _string;
    }

    @Override
    public String toString() {
        return this.string;
    }
}
