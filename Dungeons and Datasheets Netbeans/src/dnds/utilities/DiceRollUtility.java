package dnds.utilities;
/*
Last updated: October 1, 2019

The Dice Roll Utility for rolling die in case a player or Dungeon Monster
has forgotten their die or if they like this one better. I like this one
better.

Contributors:
Brandon Pozil
*/

/**
 * The Utility itself uses ThreadLocal.Random to ensure random rolls. Note all
 * default roll constants are + 1 from their names. ThreadLocal.Random needs
 * have a +1 to it's upper bound as it is an exclusive bound.
 */

import java.util.concurrent.ThreadLocalRandom;

public class DiceRollUtility {
    private static final int LOWER_BOUND_FOR_TLR = 1;
    private static final int DEFAULT_D4 = 5;
    private static final int DEFAULT_D6 = 7;
    private static final int DEFAULT_D8 = 9;
    private static final int DEFAULT_D10 = 11;
    private static final int DEFAULT_D12 = 13;
    private static final int DEFAULT_D20 = 21;

    public static int rollD4() {
        return DiceRollUtility.getRandomNumber(DiceRollUtility.DEFAULT_D4);
    }

    public static int rollD6() {
        return DiceRollUtility.getRandomNumber(DiceRollUtility.DEFAULT_D6);
    }

    public static int rollD8() {
        return DiceRollUtility.getRandomNumber(DiceRollUtility.DEFAULT_D8);
    }

    public static int rollD10() {
        return DiceRollUtility.getRandomNumber(DiceRollUtility.DEFAULT_D10);
    }

    public static int rollD12() {
        return DiceRollUtility.getRandomNumber(DiceRollUtility.DEFAULT_D12);
    }

    public static int rollD20() {
        return DiceRollUtility.getRandomNumber(DiceRollUtility.DEFAULT_D20);
    }

    /**
     * Method for returning a random number between 1 and the type of die
     * selected for the roll.
     * @param _typeofDie Selection of the die that the user needs.
     * @return the random number between 1 and the type of die rolled.
     */

    private static int getRandomNumber(int _typeOfDie) {
        int random = ThreadLocalRandom.current().nextInt(LOWER_BOUND_FOR_TLR,_typeOfDie);
        return random;
    }

}
