package dnds.model.utilities;

/* Last updated: October 1, 2019

A class entirely devoted to calculating a character's profiency bonus at
any given time.

Contributors:
Brandon Pozil

*/

import dnds.model.characters.Characters;

/**
 * A proficiency modifier can be calculated by taking the character's
 * stat subtracting it by 10 and dividing the final number by 2 and rounding
 * down. These methods account for any modifier needed in a campaign.
 * @author Brandon Pozil
 */
public class ProficiencyBonus {

    private static final int MODIFIER = 10;
    private static final int DIVISOR = 2;

    public int getStrengthModifier(Characters _character) {
        return (int) Math.floor((_character.getStrength()-ProficiencyBonus.MODIFIER)/ProficiencyBonus.DIVISOR);
    }

    public int getDexModifier(Characters _character) {
        return (int) Math.floor((_character.getDex()-ProficiencyBonus.MODIFIER)/ProficiencyBonus.DIVISOR);
    }

    public int getConstitutionModifier(Characters _character) {
        return (int) Math.floor((_character.getConstitution()-ProficiencyBonus.MODIFIER)/ProficiencyBonus.DIVISOR);
    }

    public int getIntelligenceModifier(Characters _character) {
        return (int) Math.floor((_character.getIntelligence()-ProficiencyBonus.MODIFIER)/ProficiencyBonus.DIVISOR);
    }

    public int getWisdomModifier(Characters _character) {
        return (int) Math.floor((_character.getWisdom()-ProficiencyBonus.MODIFIER)/ProficiencyBonus.DIVISOR);
    }

    public int getCharismaModifier(Characters _character) {
        return (int) Math.floor((_character.getCharisma()-ProficiencyBonus.MODIFIER)/ProficiencyBonus.DIVISOR);
    }

}
