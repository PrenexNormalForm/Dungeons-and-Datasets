package DandD;

import DandD.utilities.DiceRollUtility;

public class Dungeons__DataSheets {

    public static void main(String[] args) {
        System.out.print("D4:" + DiceRollUtility.rollD4() + ",");
        System.out.println(DiceRollUtility.rollD4());
        System.out.print("D6:" + DiceRollUtility.rollD6() + ",");
        System.out.println(DiceRollUtility.rollD6());
        System.out.print("D8:" + DiceRollUtility.rollD8() + ",");
        System.out.println(DiceRollUtility.rollD8());
        System.out.print("D10:" + DiceRollUtility.rollD10() + ",");
        System.out.println(DiceRollUtility.rollD10());
        System.out.print("D20:" + DiceRollUtility.rollD20() + ",");
        System.out.println(DiceRollUtility.rollD20());

    }
    
}
