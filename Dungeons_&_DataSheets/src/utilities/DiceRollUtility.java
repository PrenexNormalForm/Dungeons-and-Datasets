package DandD.utilities;

import java.util.concurrent.ThreadLocalRandom;

public class DiceRollUtility {
    private static final int LOWER_BOUND_FOR_TLR = 1;
    private static final int DEFAULT_D4 = 5;
    private static final int DEFAULT_D6 = 6;
    private static final int DEFAULT_D8 = 8;
    private static final int DEFAULT_D10 = 10;
    private static final int DEFAULT_D12 = 12;
    private static final int DEFAULT_D20 = 20;
    
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
    
    private static int getRandomNumber(int _typeOfDie) {
        int random = ThreadLocalRandom.current().nextInt(LOWER_BOUND_FOR_TLR,_typeOfDie);
        return random;
    }
    
}
