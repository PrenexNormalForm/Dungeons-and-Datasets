package dnds;
/*
Last updated Oct 29, 2019

A collection of test cases to prove the logic works.

Contributors:
Jonathan Bacon
Brandon Pozil
 */
import java.io.IOException;
import java.util.Scanner;
import model.characters.Characters;
import model.items.Item;

/**
 * class to house all the test cases
 */
public class TestCases {
    static Scanner kb = new Scanner(System.in);

    public static void testChoice() throws IOException{
        System.out.println("Available tests: ");
        System.out.println("1 - create a new character");
        System.out.println("2 - load a character");
        System.out.println("3 - add an item to an existing character");
        System.out.println("4 - remove an item from an existing character");
        int input = kb.nextInt();
        switch(input){
            case 1:
                test01();
                break;
            case 2:
                test02();
                break;
            case 3:
                test03();
                break;
            case 4:
                test04();
                break;
            default:
                System.out.println("That is not a valid option! exiting program");
        }
    }

    //test case 01
    public static void test01() throws IOException {
        System.out.println("Test case 01 finished");
        Characters test = TestController.createCharacter(kb);
        System.out.println("Initial Character Creation Readout");
        System.out.println(test.toString());
        System.out.println("Saving Character");
        TestGson.saveCharacter(test, test.getName());
        System.out.println("Test case 01 finished");
    }
    //test case 02
    public static void test02() throws IOException{
        System.out.println("Test case 02 started");
        String name = TestController.promptForCharacter(kb);
        System.out.println("Loading Character");
        Characters loaded = TestGson.loadCharacter(name);
        System.out.println(loaded.toString());
        System.out.println("Test case 02 finished");

    }
    //test case 03
    public static void test03() throws IOException{
        System.out.println("Test case 03 started");
        System.out.println("Gathering character to add item to");
        String name = TestController.promptForCharacter(kb);
        Characters loaded = TestGson.loadCharacter(name);
        System.out.println("Generating item");
        Item item = TestController.promptForItem();
        System.out.println("Placing item into characters inventory");
        loaded.addItem(item);
        System.out.println("Item added. Saving Character");
        TestGson.saveCharacter(loaded, loaded.getName());
        System.out.flush();
        System.out.println("Test case 03 finished");
    }
    //test case 04
    public static void test04() throws IOException{
        String name = TestController.promptForCharacter(kb);
        System.out.println("Loading Character");
        Characters loaded = TestGson.loadCharacter(name);
        TestController.removeItem(kb, loaded);
        TestGson.saveCharacter(loaded, loaded.getName());
    }
}
