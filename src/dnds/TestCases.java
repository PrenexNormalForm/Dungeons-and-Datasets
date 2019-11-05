package dnds;
/*
Last updated Oct 29, 2019

A collection of test cases to prove the logic works.

Contributors:
Jonathan Bacon
Brandon Pozil
 */
import java.io.IOException;
import model.characters.Characters;
import model.items.Item;

/**
 * class to house all the test cases
 */
public class TestCases {

    //test 01 case
    public static void test01() throws IOException {
        Characters test = TestController.createCharacter();
        System.out.println("Initial Character Creation Readout");
        System.out.println(test.toString());
        System.out.println("Saving Character");
        TestGson.saveCharacter(test, test.getName());
        System.out.println("Test case 01 finished");
    }
    //test 02 case
    public static void test02() throws IOException{
        String name = TestController.promptForCharacter();
        System.out.println("Loading Character");
        Characters loaded = TestGson.loadCharacter(name);
        System.out.println(loaded.toString());
        System.out.println("Test case 02 finished");
    }
    //test 03 case
    public static void test03() throws IOException{
        System.out.println("Gathering character to add item to");
        String name = TestController.promptForCharacter();
        Characters loaded = TestGson.loadCharacter(name);
        System.out.println("Generating item");
        Item item = TestController.promptForItem();
        System.out.println("Placing item into characters inventory");
        loaded.addItem(item);
        System.out.println("Item added. Saving Character");
        TestGson.saveCharacter(loaded, loaded.getName());
        System.out.println("Test case 03 finished");

    }
}
