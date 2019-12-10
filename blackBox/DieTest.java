package blackBox;

import java.text.DecimalFormat;
import model.utilities.DiceRollUtility;

/**
 * Last Updated: December 10, 2019
 *
 * Contributors: Brandon Pozil
 *
 * This is the test class that see if the die do truly roll at about the
 * average. The average of each die should be roughly half the die + 0.5 (due to
 * starting at 1 and not 0). Notice that the value is actually average + 0.6.
 * While testing, some of the averages rose above 2.5 or 3.5 etc, and ran better
 * than 100% which shouldn't be possible. Switch cases are used to test each
 * individual die and can be altered to test tolerance values and higher or
 * lower amount of tests. This could be done with reflections but for the sake
 * of time and my own sanity, switch cases were used for this simple
 * implementation.
 */
public class DieTest {

    /*
    The enumerated values of expected amounts per die.
    The amount of runs.
    Type of die to roll.
    The format of the decimal for easy reading.
    Respectively.
     */
    private static final double TOLERANCE[] = {2.6, 3.6, 4.6, 5.6, 6.6, 10.6};
    private static final int RUNS = 200000;
    private static final int[] ACCEPTABLE_DIE = {4, 6, 8, 10, 12, 20};
    private static DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * This method tests every die and a tolerance level for each die. If the
     * tolerance level is above 1, then there is a logic error in the program.
     *
     * @param _dieToBeTested
     */
    public static void runTest(int _dieToBeTested) {
        int total = 0;
        double average = 0.0;
        boolean passed;
        if (isAcceptable(_dieToBeTested) == false) {
            System.out.println("Enter the roll of the die you want tested!");
            System.exit(1);
        }
        switch (_dieToBeTested) {
            case 4:
                System.out.println("Running D4 Test!");
                average = runTestD4();
                System.out.println(doesD4Pass(average, _dieToBeTested));
                break;
            case 6:
                System.out.println("Running D6 Test!");
                average = runTestD6();
                System.out.println(doesD6Pass(average, _dieToBeTested));
                break;
            case 8:
                System.out.println("Running D8 Test!");
                average = runTestD8();
                System.out.println(doesD8Pass(average, _dieToBeTested));
                break;
            case 10:
                System.out.println("Running D10 Test!");
                average = runTestD10();
                System.out.println(doesD10Pass(average, _dieToBeTested));
                break;
            case 12:
                System.out.println("Running D12 Test!");
                average = runTestD12();
                System.out.println(doesD12Pass(average, _dieToBeTested));
                break;
            case 20:
                System.out.println("Running D20 Test!");
                average = runTestD20();
                System.out.println(doesD20Pass(average, _dieToBeTested));
                break;
        }
    }

    /**
     * This method tests if a value called in the main is an acceptable type of
     * die. I.E, 4,6,8,10,12 or 20.
     *
     * @param _toBeTested
     * @return
     */
    public static boolean isAcceptable(int _toBeTested) {
        boolean acceptedValue = false;
        for (int testDie : DieTest.ACCEPTABLE_DIE) {
            if (_toBeTested == testDie) {
                acceptedValue = true;
                return acceptedValue;
            } else {
                acceptedValue = false;
            }
        }
        return acceptedValue;
    }

    /**
     * These methods below rolls the die and gets an average based on the
     * average of all the rolls.
     *
     * @return The tolerance level.
     */
    public static double runTestD4() {
        int total = 0;
        for (int i = 0; i < DieTest.RUNS; i++) {
            total += DiceRollUtility.rollD4();
        }
        double average = (double) total / DieTest.RUNS;
        System.out.println("Number of Rolls: " + DieTest.RUNS + "\n" + "Total roll of die: " + total + "\n" + "Tolerance Level:" + "\n" + average);
        return average;
    }

    public static double runTestD6() {
        int total = 0;
        for (int i = 0; i < DieTest.RUNS; i++) {
            total += DiceRollUtility.rollD6();
        }
        double average = (double) total / DieTest.RUNS;
        System.out.println("Number of Rolls: " + DieTest.RUNS + "\n" + "Total roll of die: " + total + "\n" + "Tolerance Level:" + "\n" + average);
        return average;
    }

    public static double runTestD8() {
        int total = 0;
        for (int i = 0; i < DieTest.RUNS; i++) {
            total += DiceRollUtility.rollD8();
        }
        double average = (double) total / DieTest.RUNS;
        System.out.println("Number of Rolls: " + DieTest.RUNS + "\n" + "Total roll of die: " + total + "\n" + "Tolerance Level:" + "\n" + average);
        return average;
    }

    public static double runTestD10() {
        int total = 0;
        for (int i = 0; i < DieTest.RUNS; i++) {
            total += DiceRollUtility.rollD10();
        }
        double average = (double) total / DieTest.RUNS;
        System.out.println("Number of Rolls: " + DieTest.RUNS + "\n" + "Total roll of die: " + total + "\n" + "Tolerance Level:" + "\n" + average);
        return average;
    }

    public static double runTestD12() {
        int total = 0;
        for (int i = 0; i < DieTest.RUNS; i++) {
            total += DiceRollUtility.rollD12();
        }
        double average = (double) total / DieTest.RUNS;
        System.out.println("Number of Rolls: " + DieTest.RUNS + "\n" + "Total roll of die: " + total + "\n" + "Tolerance Level:" + "\n" + average);
        return average;
    }

    public static double runTestD20() {
        int total = 0;
        for (int i = 0; i < DieTest.RUNS; i++) {
            total += DiceRollUtility.rollD20();
        }
        double average = (double) total / DieTest.RUNS;
        System.out.println("Number of Rolls: " + DieTest.RUNS + "\n" + "Total roll of die: " + total + "\n" + "Tolerance Level:" + "\n" + average);
        return average;
    }

    /**
     * These method check to see if the tolerance levels are indeed in the range
     * of tolerance established in the array entitled TOLERANCE. Originally,
     * these values were at average + 0.5 but since they can roll above that and
     * have their own average of say 2.51 they could throw off the percentage.
     *
     *
     * @param _average
     * @param _dieToBeRolled
     * @return
     */
    public static boolean doesD4Pass(double _average, int _dieToBeRolled) {
        boolean passed = false;
        double value = (_average / DieTest.TOLERANCE[0] * 100);
        if (_average + 1 <= _dieToBeRolled) {
            passed = true;
            System.out.println("Test within tolerance! Successful!");
            System.out.println("Closeness to actual tolerance level: " + DF.format(value) + "%");
        } else {
            passed = false;
            System.out.println("Test out of tolerance level!");
        }
        return passed;
    }

    public static boolean doesD6Pass(double _average, int _dieToBeRolled) {
        boolean passed = false;
        double value = (_average / DieTest.TOLERANCE[1] * 100);
        if (_average + 1 <= _dieToBeRolled) {
            passed = true;
            System.out.println("Test within tolerance! Successful!");
            System.out.println("Closeness to actual tolerance level: " + DF.format(value) + "%");
        } else {
            passed = false;
            System.out.println("Test out of tolerance level!");
        }
        return passed;
    }

    public static boolean doesD8Pass(double _average, int _dieToBeRolled) {
        boolean passed = false;
        double value = (_average / DieTest.TOLERANCE[2] * 100);
        if (_average + 1 <= _dieToBeRolled) {
            passed = true;
            System.out.println("Test within tolerance! Successful!");
            System.out.println("Closeness to actual tolerance level: " + DF.format(value) + "%");
        } else {
            passed = false;
            System.out.println("Test out of tolerance level!");
        }
        return passed;
    }

    public static boolean doesD10Pass(double _average, int _dieToBeRolled) {
        boolean passed = false;
        double value = (_average / DieTest.TOLERANCE[3] * 100);
        if (_average + 1 <= _dieToBeRolled) {
            passed = true;
            System.out.println("Test within tolerance! Successful!");
            System.out.println("Closeness to actual tolerance level: " + DF.format(value) + "%");
        } else {
            passed = false;
            System.out.println("Test out of tolerance level!");
        }
        return passed;
    }

    public static boolean doesD12Pass(double _average, int _dieToBeRolled) {
        boolean passed = false;
        double value = (_average / DieTest.TOLERANCE[4] * 100);
        if (_average + 1 <= _dieToBeRolled) {
            passed = true;
            System.out.println("Test within tolerance! Successful!");
            System.out.println("Closeness to actual tolerance level: " + DF.format(value) + "%");
        } else {
            passed = false;
            System.out.println("Test out of tolerance level!");
        }
        return passed;
    }

    public static boolean doesD20Pass(double _average, int _dieToBeRolled) {
        boolean passed = false;
        double value = (_average / DieTest.TOLERANCE[5] * 100);
        if (_average + 1 <= _dieToBeRolled) {
            passed = true;
            System.out.println("Test within tolerance! Successful!");
            System.out.println("Closeness to actual tolerance level: " + DF.format(value) + "%");
        } else {
            passed = false;
            System.out.println("Test out of tolerance level!");
        }
        return passed;
    }

    /**
     * The main method where you can call runTest() with any die in the
     * ACCEPTABLE_DIE array.
     *
     * @param args
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException {
        runTest(4);
        runTest(6);
        runTest(8);
        runTest(10);
        runTest(12);
        runTest(20);
    }

}
