package dnds;

/*
Last updated Oct 29, 2019

A test design for our controllers which will be linked to the UI

Contributors:
Jonathan Bacon
Brandon Pozil
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Scanner;
import model.characters.CharacterClass;
import model.characters.Characters;
import model.items.Armor;
import model.items.properties.DamageAttackRoll;
import model.items.properties.DamageType;
import model.items.GenericItem;
import model.items.Item;
import model.items.Potion;
import model.items.properties.ItemType;
import model.items.Weapon;
import model.items.properties.PotionProperties;
import model.items.properties.WeaponProperties;
import model.items.properties.WeaponType;
import static model.utilities.DiceRollUtility.rollD6;

/**
 *
 * Class to hold controller methods THIS IS A TESTING CONTROLLER
 */
public class TestController {

    final static int MAX_RETRIES = 4;
// <editor-fold defaultstate="collapsed" desc="CRUD">

    public static String promptForCharacter(Scanner _kb) {
        System.out.println("Please enter the name of the character you want to load: ");
        _kb.nextLine();
        String input = getName(_kb);
        return input;
    }

    public static String getName(Scanner _kb){
        String name = "";
        while(_kb.hasNextLine()){
            name = _kb.nextLine();
            if(name != null)
                break;
        }
        return name;
    }

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="character creation">
    //method for creating a new character
    public static Characters createCharacter(Scanner _kb) {
        HashMap<String, Object> map = getInput(_kb);
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
    private static HashMap<String, Object> getInput(Scanner _kb) {
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Integer> stats = new HashMap<>();
        System.out.println("Enter Character name: ");
        _kb.nextLine();
        String input = getName(_kb);
        map.put("name", input);
        System.out.println("Enter your desired class: ");
        //calls the method to get the desired class
        input = getProperty(_kb, CharacterClass.class).toLowerCase();
        //calls the method for getting the stat rolls
        stats = rollHelper(_kb, stats);
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

// <editor-fold defaultstate="collapsed" desc="Item Management">

// <editor-fold defaultstate="collapsed" desc="item creation">
    //method for getting the type of item from user.
    public static Item promptForItem() {
        Scanner kb = new Scanner(System.in);
        //gets which item that the user wants to make
        System.out.println("What type of item do you wish to create");
        //calls the method to get a user choice based on the class enum
        String input = getProperty(kb, ItemType.class).toLowerCase();
        Item created;
        //gets the name of the item from the user
        System.out.println("Enter your items name: ");
        kb.nextLine();
        String name = getName(kb);
        //calls the method for gathering cost and weight Weight at index 0 and Cost at index 1
        int[] arr = gatherWeightAndCost(kb);
        //switch case for calling the appropriate methods
        switch (input) {
            case "weapon":
                created = createWeapon(kb, name, arr[0], arr[1]);
                break;
            case "armor":
                created = createArmor(kb, name, arr[0], arr[1]);
                break;
            case "potion":
                created = createPotion(kb, name, arr[0], arr[1]);
                break;
            default:
                created = createGenericItem(name, arr[0], arr[1]);
        }
        kb.close();
        return created;
    }

    // <editor-fold defaultstate="collapsed" desc="weapons">
    //master method for handling weapon creation
    private static Weapon createWeapon(Scanner _kb, String _name, int _weight, int _cost) {
        System.out.print("What is your weapons type: ");
        //calls the method to get a user choice based on the class enum
        String type = getProperty(_kb, WeaponType.class);
        System.out.print("What is your weapons damage attack roll: ");
        String attackRoll = getProperty(_kb, DamageAttackRoll.class);
        System.out.print("What type of damage does your weapon do: ");
        //calls the method to get a user choice based on the class enum
        String damageType = getProperty(_kb, DamageType.class);
        System.out.print("What properties does your weapon have: ");
        //calls the method to get a user choice based on the class enum
        String properties = getProperty(_kb, WeaponProperties.class);
        //creates a new weapon and returns it
        return new Weapon(_name, _cost, _weight, type, attackRoll, damageType, properties);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="armor">
    //master method for creating armor
    private static Armor createArmor(Scanner _kb, String _name, int _weight, int _cost) {
        int armorClass = gatherArmorClass(_kb);
        //creates a new armor item and returns it
        return new Armor(_name, _cost, _weight, armorClass);
    }
    //method to get armor class and make sure its within the allowed range
    private static int gatherArmorClass(Scanner _kb){
        System.out.println("Armor class: ");
        int armorClass = _kb.nextInt();
        int counter = 0;
        while((armorClass < 1 || armorClass > 29) && counter < MAX_RETRIES){
            System.out.println("Invalid input. the armor class must be between 0 and 29");
            System.out.print("Armor Class: ");
            armorClass = _kb.nextInt();
            counter++;
        }
        //if the counter has reached the maximum allowed retries. exit program.
        if(counter == MAX_RETRIES){
            System.out.println("Exceeded allowed attempts. Exiting program");
            System.exit(0);
        }
        return armorClass;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="generic items">
    private static Item createGenericItem(String _name, int _weight, int _cost) {
        //creates a new item and returns it
        return new GenericItem(_name, _cost, _weight);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="potions">
    public static Potion createPotion(Scanner _kb, String _name, int _weight, int _cost){
        System.out.print("What property does your potion have: ");
        String property = getProperty(_kb, PotionProperties.class);
        System.out.println("What is the amount your potion restores/damages: ");
        int value = _kb.nextInt();

        return new Potion(_name, _cost, _weight, property, value);
    }

    // </editor-fold>

    //method for gathering the weight and cost of an item
    private static int[] gatherWeightAndCost(Scanner _kb){
        int[] arr = new int[2];
        System.out.print("Weight: ");
        arr[0] = _kb.nextInt();
        System.out.print("Cost: ");
        arr[1] = _kb.nextInt();
        return arr;
    }
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
        //prints out the available choices
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

// <editor-fold defaultstate="collapsed" desc="Item deletion">
    //master method used for removing items from a character inventory
    public static void removeItem(Scanner _kb, Characters _char){
        //calls the method to ask for the item to be deleted
        String item = promptForRemoveItem(_kb, _char);
        //deletes the item from the character inventory
        _char.removeItem(item);
    }
    //method for prompting the user for which item they wish to remove
    private static String promptForRemoveItem(Scanner _kb, Characters _char){
        System.out.println("Enter the name of the item you wish to delete: ");
        //returns the users choice
        return getName(_kb);
    }

// </editor-fold>

// </editor-fold>
}
