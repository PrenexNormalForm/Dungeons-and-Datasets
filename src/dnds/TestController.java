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
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Scanner;
import model.characters.CharacterClass;
import model.characters.Characters;
import model.items.Armor;
import model.items.DamageType;
import model.items.Item;
import model.items.ItemType;
import model.items.Weapon;
import model.items.WeaponProperties;
import model.items.WeaponType;
import static model.utilities.DiceRollUtility.rollD6;

/**
 *
 * Class to hold controller methods THIS IS A TESTING CONTROLLER
 */
public class TestController {

    //static Scanner SCANNER = new Scanner(System.in);
    final static int MAX_RETRIES = 4;
// <editor-fold defaultstate="collapsed" desc="CRUD">

    public static String promptForCharacter() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter the name of the character you want to load: ");
        String input = kb.nextLine();
        return input;
    }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="character creation">
    //method for creating a new character
    public static Characters createCharacter() {
        HashMap<String, Object> map = getInput();
        String name = (String) map.get("name");
        String klass = (String) map.get("class");
        int strength = (int) map.get("strength");
        int dexterity = (int) map.get("dexterity");
        int constitution = (int) map.get("constitution");
        int intelligence = (int) map.get("intelligence");
        int wisdom = (int) map.get("wisdom");
        int charisma = (int) map.get("charisma");
        return new Characters(klass, name, strength, dexterity, constitution, intelligence, wisdom, charisma);
    }

    //method for gathering input about character creation
    private static HashMap<String, Object> getInput() {
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Integer> stats = new HashMap<>();
        Scanner kb = new Scanner(System.in);
        int attempts = 0;
        System.out.println("Enter Character name: ");
        String input = kb.nextLine();
        map.put("name", input);
        System.out.println("Enter your desired class: ");
        //calls the method to get the desired class
        input = getProperty(kb, CharacterClass.class).toLowerCase();
        //calls the method for getting the stat rolls
        stats = rollHelper(kb, stats);
        map.put("class", input.substring(0, 1) + input.substring(1));
        map.putAll(stats);
        return map;
    }
    //helper method for dice rolling
    private static HashMap rollHelper(Scanner _kb, HashMap _stats) {
        System.out.println("Would you like to manually role or have us roll? (manuel or auto)");
        String rollType = _kb.next();
        int counter = 0;
        while (!rollType.toLowerCase().equals("auto") && !rollType.toLowerCase().equals("manuel") && counter < MAX_RETRIES) {
            System.out.println("Invalid choice. please choose auto or manuel");
            rollType = _kb.next();
            counter++;
        }
        if (counter == MAX_RETRIES) {
            System.out.println("Maximum retries exeeded. Exiting program");
            System.exit(0);
        }
        switch (rollType.toLowerCase()) {
            case "auto":
                _stats = autoRolls();
                break;
            case "manuel":
                _stats = userRolls(_kb);
        }
        return _stats;
    }

    //method for using the built in dice roller to gather stat rolls
    private static HashMap<String, Integer> autoRolls() {
        Integer[] rolls = {0, 0, 0, 0};
        String[] statNames = {"strength", "dexterity", "constitution", "intelligence", "wisdom", "charisma"};
        HashMap<String, Integer> stats = new HashMap<>();
        //rolls for each stats 4 times and removes the lowest roll and sums them for that stat
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < 4; k++) {
                rolls[k] = rollD6();
            }
            //sorts the rolls in reverse order
            Arrays.sort(rolls, Collections.reverseOrder());
            //removes the lowest roll
            rolls[rolls.length - 1] = 0;
            int sum = rolls[0] + rolls[1] + rolls[2] + rolls[3];
            stats.put(statNames[i], sum);
        }
        return stats;
    }

    //method for gathering the users rolls
    private static HashMap<String, Integer> userRolls(Scanner _kb) {
        String[] statNames = {"strength", "dexterity", "constitution", "intelligence", "wisdom", "charisma"};
        HashMap<String, Integer> stats = new HashMap<>();
        System.out.println("Please roll now");
        //for loop that gets all the different stat rolls from user
        for (int i = 0; i < 6; i++) {
            int attempts = 0;
            System.out.print("Please enter your roll for " + statNames[i] + ": ");
            Integer roll = _kb.nextInt();
            //allows for repeated attempts at rolls
            while (roll > 18 && attempts < 5 || roll < 3 && attempts < MAX_RETRIES) {
                attempts++;
                System.out.println("Invalid roll please enter a number between 3-18");
                roll = _kb.nextInt();
            }
            //triggers if someone is being a meme
            if (attempts == MAX_RETRIES) {
                System.out.println("Fine have it your way. everyone went to mordor and died. the end. are you happy?");
                System.exit(0);
            }
            stats.put(statNames[i], roll);
        }
        return stats;
    }
    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="item creation">
    public static Item promptForItem() {
        Scanner kb = new Scanner(System.in);
        //gets which item that the user wants to make
        System.out.println("What type of item do you wish to create");
        String input = getProperty(kb, ItemType.class).toLowerCase();
        Item created;
        //switch case for calling the appropriate methods
        switch (input) {
            case "weapon":
                created = createWeapon(kb);
                break;
            case "armor":
                created = createArmor(kb);
                break;
            default:
                created = createItem(kb);
        }
        kb.close();
        return created;
    }

    // <editor-fold defaultstate="collapsed" desc="weapons">
    //master method for handling weapon creation
    private static Weapon createWeapon(Scanner _kb) {
        System.out.print("Enter your weapons name: ");
        //this line exists to expend the extra new line. the input gathering breaks otherwise
        _kb.nextLine();
        String name = _kb.nextLine();
        System.out.print("Item Weight: ");
        int weight = _kb.nextInt();
        System.out.print("Item Cost: ");
        int cost = _kb.nextInt();
        System.out.println("What is your weapons type: ");
        String type = getProperty(_kb, WeaponType.class);
        System.out.print("What is your weapons damage attack roll: ");
        String attackRoll = _kb.next();
        System.out.println("What type of damage does your weapon do: ");
        String damageType = getProperty(_kb, DamageType.class);
        System.out.println("What properties does your weapon have: ");
        String properties = getProperty(_kb, WeaponProperties.class);
        //creates a new weapon and returns it
        return new Weapon(name, cost, weight, type, attackRoll, damageType, properties);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="armor">
    private static Armor createArmor(Scanner _kb) {
        System.out.print("Enter your armors name: ");
        //this line exists to expend the extra new line. the input gathering breaks otherwise
        _kb.nextLine();
        String name = _kb.nextLine();
        System.out.print("Weight: ");
        int weight = _kb.nextInt();
        System.out.print("Cost: ");
        int cost = _kb.nextInt();
        System.out.print("Armor class: ");
        int armorClass = _kb.nextInt();
        //creates a new armor item and returns it
        return new Armor(name, cost, weight, armorClass);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="items">
    private static Item createItem(Scanner _kb) {
        System.out.print("Item name: ");
        //this line exists to expend the extra new line. the input gathering breaks otherwise
        _kb.nextLine();
        String name = _kb.nextLine();
        System.out.print("Cost: ");
        int cost = _kb.nextInt();
        System.out.print("Weight: ");
        int weight = _kb.nextInt();
        //creates a new item and returns it
        return new Item(name, cost, weight);
    }

    // </editor-fold>
    //method used for checking of an enum contains a value
    public static <E extends Enum<E>> boolean contains(Class<E> _enumClass, String value) {
        try {
            return EnumSet.allOf(_enumClass)
                    .contains(Enum.valueOf(_enumClass, value));
        } catch (Exception e) {
            return false;
        }
    }

    //generic method for getting property choices that match given enums
    private static <E extends Enum<E>> String getProperty(Scanner _kb, Class<E> _enumClass) {
        System.out.println(EnumSet.allOf(_enumClass));
        String property = _kb.next().toUpperCase();
        int counter = 0;
        //checks if the enum contains the property and if the counter is less than the max retries
        while (!contains(_enumClass, property.toUpperCase()) && counter < MAX_RETRIES) {
            System.out.println("That is not a valid option");
            System.out.println("Choose one of the below types");
            System.out.println(EnumSet.allOf(_enumClass));
            property = _kb.next().toUpperCase();
            counter++;
        }
        if (counter == MAX_RETRIES) {
            System.out.println("maximum tries exceeded");
            System.exit(0);
        }
        return property;
    }
    // </editor-fold>
}
