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

/**
 * class to house all the test cases
 */
public class TestCases {

    //test 01 case
    public static void test01() throws IOException {
        Characters test = TestController.createCharacter();
        System.out.println(test.toString());
        TestCRUD.createSave(test);
    }
    //test 02 case
    public static void test02(){
        String name = TestController.promptForCharacter();
        String save = TestCRUD.read(name);
        String[] arr = save.split("!");
        Characters test = TestController.savedCharacter(arr);
        System.out.println(test.toString());

    }
}
