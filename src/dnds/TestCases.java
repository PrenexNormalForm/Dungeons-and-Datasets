/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnds;

import model.characters.Characters;

/**
 * class to house all the test cases
 */
public class TestCases {
    
    //test 01 case
    public static void test01() {
        Characters test = TestController.createCharacter();
        System.out.println(test.toString());
    }
}
