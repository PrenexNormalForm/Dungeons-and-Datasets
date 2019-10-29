package dnds;
/*
Last updated Oct 29, 2019

A test design for our controllers which will be linked to the UI

Contributors:
Jonathan Bacon
Brandon Pozil
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import model.characters.CharacterClass;
import model.characters.Characters;
import static model.utilities.DiceRollUtility.rollD6;

/**
 *
 * Class to hold controller methods THIS IS A TESTING CONTROLLER
 */
public class TestController {
// <editor-fold defaultstate="collapsed" desc="CRUD"> 
    public static String promptForCharacter(){
        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter the name of the character you want to load:");
        String input = kb.nextLine();
        return input;
    }
    
// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="character creation">    
    //method for creating a new character
    public static Characters createCharacter(){
        HashMap<String, Object> map = getInput();
        String name = (String) map.get("name");
        String klass = (String) map.get("class");
        int strength = (int) map.get("strength");
        int dexterity = (int) map.get("dexterity");
        int constitution = (int) map.get("constitution");
        int intelligence = (int) map.get("intelligence");
        int wisdom = (int) map.get("wisdom");
        int charisma = (int) map.get("charisma");
        return new Characters(klass,name,strength,dexterity,constitution,intelligence,wisdom,charisma);
    }
    //method for creating a character from a save
    public static Characters savedCharacter(String[] _vals){
        String name = (String) _vals[0];
        String klass = (String) _vals[1];
        int strength = Integer.parseInt(_vals[2]);
        int dexterity = Integer.parseInt(_vals[3]);
        int constitution = Integer.parseInt(_vals[4]);
        int intelligence = Integer.parseInt(_vals[5]);
        int wisdom = Integer.parseInt(_vals[6]);
        int charisma = Integer.parseInt(_vals[7]);
        return new Characters(klass,name,strength,dexterity,constitution,intelligence,wisdom,charisma);
    }
    //method for gathering input about character creation
    private static HashMap <String, Object> getInput(){
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Integer> stats = new HashMap<>();
        Scanner kb = new Scanner(System.in);
        int attempts = 0;
        System.out.println("Enter Character name: ");
        String input = kb.nextLine();
        map.put("name", input);
        //calls the method to get the desired class
        do { 
            input = classGrabber(kb);
            attempts++;
        } while(input.equals("invalid") && attempts < 4);
        
        if(attempts == 4){
            System.out.println("Ya cant choose that as a class ya nerd.");
            System.exit(0);
        }
        stats = rollHelper(kb, stats);
        map.put("class", input.substring(0, 1) + input.substring(1));
        map.putAll(stats);
        return map;
    }
    
    //method used for getting the class from the user
    private static String classGrabber(Scanner _kb){
        String input;
        ArrayList<String> klasses = new ArrayList<>();
        System.out.println("Classes");
        System.out.println(Arrays.toString(CharacterClass.values()).toLowerCase());
        System.out.print(": ");
        input = _kb.nextLine();
        
        for(CharacterClass k : CharacterClass.values()){
            klasses.add(k.toString().toLowerCase());
        }
        if(klasses.contains(input)){
            return input;
        }
        return "invalid";
    }
    //helper method for dice rolling
    private static HashMap rollHelper(Scanner _kb, HashMap _stats){
        System.out.println("Would you like to manually role or have us roll? (manuel or auto)");
        String rollType = _kb.nextLine();
        if(rollType.toLowerCase().equals("auto")){
            _stats = autoRolls();
        } else if(rollType.toLowerCase().equals("manuel")){
            _stats = userRolls(_kb);
        } else {
            System.out.println("you failed. try again");
            System.exit(0);
        }
        return _stats;
    }
    //method for using the built in dice roller to gather stat rolls
    private static HashMap <String, Integer> autoRolls(){
        Integer[] rolls = {0,0,0,0};
        String[] statNames = {"strength", "dexterity", "constitution", "intelligence", "wisdom", "charisma"};
        HashMap<String, Integer> stats = new HashMap<>();
        //rolls for each stats 4 times and removes the lowest roll and sums them for that stat
        for(int i = 0; i < 6; i++){
            for(int k = 0; k < 4; k++){
                rolls[k] = rollD6();
            }
            Arrays.sort(rolls, Collections.reverseOrder());
            rolls[rolls.length-1] = 0;
            int sum = rolls[0]+rolls[1]+rolls[2]+rolls[3];
            stats.put(statNames[i], sum);
        }
        return stats;
    }
    //method for gathering the users rolls
    private static HashMap <String, Integer> userRolls(Scanner _kb){
        String[] statNames = {"strength", "dexterity", "constitution", "intelligence", "wisdom", "charisma"};
        HashMap<String, Integer> stats = new HashMap<>();
        System.out.println("Please roll now");
        //for loop that gets all the different stat rolls from user
        for(int i = 0; i < 6; i++){
            int attempts = 0;
            System.out.print("Please enter your roll for " + statNames[i] +": ");
            Integer roll = _kb.nextInt();
            //allows for repeated attempts at rolls
            while(roll > 18 && attempts < 5 || roll < 3 && attempts < 4){
                attempts++;
                System.out.println("Invalid roll please enter a number between 3-18");
                roll = _kb.nextInt();
            }
            //triggers if someone is being a meme
            if(attempts == 4){
                System.out.println("Fine have it your way. everyone went to mordor and died. the end. are you happy?");
                System.exit(0);
            }
            stats.put(statNames[i], roll);
        }
        return stats;
    }
    // </editor-fold>
}
