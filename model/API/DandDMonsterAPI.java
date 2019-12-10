package model.API;

/**
 * Last Updated: December 3, 2019
 *
 * This is the main class for the D&D API. This allows user to pull in random
 * monster data as well as spells (to be implemented soon). This will pull a
 * monster's info and display it. For ease of readability, stat rolls are not
 * included but could easily be put in the method.
 *
 * Contributors: Brandon Pozil, Jon Bacon
 */
import static model.API.APIAdapter.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;
import org.json.*;

public class DandDMonsterAPI {

    /*
    These strings are used for the API calls throughout the program.
    Max amount of monsters in the API is 325.
     */
    private static final String MONSTERS = "http://www.dnd5eapi.co/api/monsters/";
    private static final String MONSTER_SEARCH = "http://www.dnd5eapi.co/api/monsters/?name=";
    private static final int MAX_MONSTER = 325;
    private static final int MIN_MONSTER = 1;
    private static final int NEGATIVE_CHECK = 0;
    private static final String[] SPECIAL_ARRAYS = {"name", "desc", "attack_bonus"};
    private static final String[] BASIC_ARRAY = {"name", "size", "type", "hit_points",
    "armor_class", "hit_dice", "speed", "strength", "dexterity", "constitution",
    "intelligence", "wisdom", "charisma"};

    /**
     * This method finds a random monster from the API. The API stores monsters
     * with an index up to 325. Thus, a random number between 1 and 325 is used
     * to find a random monster and return some basic data about it. Strings are
     * immutable, thus we need a new string to carry all the data at the
     * beginning of the method. This method calls out to a helper method
     * getRequest() that will be explained later at the method. The try-catch is
     * needed to proceed if a monster has no special abilities. Otherwise, an
     * exception is thrown and the whole process stops.
     *
     * @return The monster data.
     * @throws MalformedURLException
     * @throws IOException
     * @throws ProtocolException
     * @throws InterruptedException
     */
    public static String getRandomMonster() throws MalformedURLException, IOException, ProtocolException, InterruptedException {
        String monsterString = "";
        int index = ThreadLocalRandom.current().nextInt(DandDMonsterAPI.MIN_MONSTER, DandDMonsterAPI.MAX_MONSTER + DandDMonsterAPI.MIN_MONSTER);
        if (index > DandDMonsterAPI.MAX_MONSTER) {
            System.out.println("There can only be so many monsters!");
            System.exit(1);
        }
        StringBuffer refreshedString = new StringBuffer();
        String toSearch = DandDMonsterAPI.MONSTERS + index;
        getRequest(toSearch, refreshedString);
        JSONObject returnData = new JSONObject(refreshedString.toString());
        String monsterBasics = getMonsterBasics(returnData).toString();
        String monsterStats = getMonsterStats(returnData).toString();
        try {
            JSONArray specialAbilities = returnData.getJSONArray("special_abilities");
            JSONArray actions = returnData.getJSONArray("actions");
            monsterString = getMonsterSpecials(specialAbilities) + getMonsterActions(actions);
        } catch (Exception e) {
            System.out.println("This monster doesn't have any special abilities/actions!");
        }
        return monsterBasics + monsterStats + monsterString;
    }

    /**
     * This method functions practically the same as getRandomMonster() but it
     * allows the user to search for a monster by the index if they know it from
     * prior searching of the API. It also allows for quicker access of a
     * monster if they can't remember the name.
     *
     * @param _index The index needed to find a monster.
     * @return The information about the monster.
     * @throws ProtocolException
     * @throws IOException
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    public static String getMonster(int _index) throws ProtocolException, IOException, MalformedURLException, InterruptedException {
        String returnString = "";
        JSONArray specialAbilities = new JSONArray();
        JSONArray actions = new JSONArray();
        if (_index > DandDMonsterAPI.MAX_MONSTER || _index <= DandDMonsterAPI.NEGATIVE_CHECK) {
            System.out.println("Monsters need to be within 0-325!");
            System.exit(1);
        }
        StringBuffer refreshedString = new StringBuffer();
        String toSearch = DandDMonsterAPI.MONSTERS + _index;
        getRequest(toSearch, refreshedString);
        JSONObject returnData = new JSONObject(refreshedString.toString());
        String monsterBasics = getMonsterBasics(returnData).toString();
        String monsterStats = getMonsterStats(returnData).toString();
        try {
            specialAbilities = returnData.getJSONArray("special_abilities");
        } catch (Exception e) {
        }
        try {
            actions = returnData.getJSONArray("actions");
        } catch (Exception e) {
        }
        returnString = getMonsterSpecials(specialAbilities) + getMonsterActions(actions);
        return monsterBasics + monsterStats + returnString;
    }

    /**
     * This method allows users to check if a monster exists by typing a name.
     * Unfortunately, this does not account for typos but does account for
     * capitalization via the cleanString() method. The API is very particular
     * about the it wants it's string formatted. Thus, cleanString() takes care
     * of any formatting issues. This method uses getRequest() to find a count
     * that is returned when using the search function in the API. The index is
     * then returned and passed into getMonster().
     *
     * @param _search The String to be searched in the API.
     * @return The monster data.
     * @throws ProtocolException
     * @throws IOException
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    public static String doesMonsterExist(String _search) throws ProtocolException, IOException, MalformedURLException, InterruptedException {
        String cleaned = cleanString(_search);
        String toSearch = DandDMonsterAPI.MONSTER_SEARCH + cleaned;
        StringBuffer refreshedString = new StringBuffer();
        getRequest(toSearch, refreshedString);
        JSONObject returnData = new JSONObject(refreshedString.toString());
        int monsterFound = returnData.getInt("count");
        if (monsterFound == 0) {
            return "";
        } else {
            return getMonster(Integer.valueOf(getMonsterInfo(returnData)));
        }
    }

    /**
     * This method is a helper method for doesMonsterExist() that finds the
     * index found at the end of the URL for a search.
     *
     * @param _monsterInfo A JSONObject to be searched.
     * @return The index of that monster.
     */
    public static String getMonsterInfo(JSONObject _monsterInfo) {
        JSONArray monsterData = _monsterInfo.getJSONArray("results");
        JSONObject monsterDatainArray = monsterData.getJSONObject(0);
        String name = monsterDatainArray.getString("name");
        String index = monsterDatainArray.getString("url");
        index = index.replace(DandDMonsterAPI.MONSTERS, "");
        return index;
    }

    /**
     * This method takes a JSONArray and prints the values found at name, desc
     * and attack_bonus for every special ability a monster has.
     *
     * @param _specialAbilities A JSONArray of special abilities for any given
     * monster.
     * @return String version of the special abilities.
     */
    public static String getMonsterSpecials(JSONArray _specialAbilities) {
        StringBuffer specialAbilities = new StringBuffer();
        if (_specialAbilities.length() == DandDMonsterAPI.NEGATIVE_CHECK) {
            System.out.println("This monster has no special abilities!");
        } else {
            specialAbilities.append("Special Abilities!" + "\n");
            for (int i = 0; i < _specialAbilities.length(); i++) {
                int count = 0;
                JSONObject desc = _specialAbilities.getJSONObject(i);
                String name = "Name: " + desc.getString(DandDMonsterAPI.SPECIAL_ARRAYS[count]) + "\n";
                specialAbilities.append(name);
                count++;
                String description = "Description: " + desc.getString(DandDMonsterAPI.SPECIAL_ARRAYS[count]) + "\n";
                specialAbilities.append(description);
                count++;
                int attackBonus = desc.getInt(DandDMonsterAPI.SPECIAL_ARRAYS[count]);
                count++;
                String attack = "Attack Bonus: " + String.valueOf(attackBonus) + "\n";
                specialAbilities.append(attack).append("\n");
            }
        }
        return specialAbilities.toString();
    }

    /**
     * Essentially the same method as above but to find a monster's actions. The
     * method was getting rather verbose so I had to break it down.
     *
     * @param _actions A JSONArray of every action a monster has.
     * @return String version of a monster's actions.
     */
    public static String getMonsterActions(JSONArray _actions) {
        StringBuffer actions = new StringBuffer();
        if (_actions.length() == DandDMonsterAPI.NEGATIVE_CHECK) {
            System.out.println("This monster has no abilities!");
        } else {
            actions.append("Actions!" + "\n");
        }
        for (int i = 0; i < _actions.length(); i++) {
            int counter = 0;
            JSONObject action = _actions.getJSONObject(i);
            String name = "Name: " + action.getString(DandDMonsterAPI.SPECIAL_ARRAYS[counter]) + "\n";
            actions.append(name);
            counter++;
            String description = "Description: " + action.getString(DandDMonsterAPI.SPECIAL_ARRAYS[counter]) + "\n";
            actions.append(description);
            counter++;
            int attackBonus = action.getInt(DandDMonsterAPI.SPECIAL_ARRAYS[counter]);
            String attack = "Attack Bonus: " + String.valueOf(attackBonus) + "\n";
            actions.append(attack).append("\n");
        }
        return actions.toString();
    }

    /**
     * This method returns a monster's basic information like name, size and the
     * type of monster. On the line that contains String type, the cleanString()
     * method is not used as it's one purpose is to clean the String for the
     * search. The API has type all lowercase, which looks funny compared to the
     * other information that is capitalized like a normal human being would do.
     * So the String nonsense on line 241 is for capitalizing the first letter
     * only.
     *
     * @param _monsterInfo The entire JSONObject that finds the name, size and
     * type of monster that was searched.
     * @return The String representation of a monster's basic info.
     */
    public static StringBuffer getMonsterBasics(JSONObject _monsterInfo) {
        StringBuffer monsterStats = new StringBuffer();
        JSONObject monsterParse = new JSONObject(_monsterInfo.toString());
        String name = "Name: " + monsterParse.getString("name") + "\n";
        monsterStats.append(name);
        String size = "Size: " + monsterParse.getString("size") + "\n";
        monsterStats.append(size);
        String type = "Type: " + monsterParse.getString("type").substring(0, 1).toUpperCase() + monsterParse.getString("type").substring(1) + "\n";
        monsterStats.append(type);
        return monsterStats;
    }

    /**
     * Much like the method above, this grabs all the monster's stats from a
     * JSONObject. The way the API has it's data formated is that all the
     * data below in a JSONObject as opposed to the JSONArray's above it.
     * @param _monsterStats
     * @return The string of monster stats.
     */

    public static StringBuffer getMonsterStats(JSONObject _monsterStats) {
        StringBuffer monsterStats = new StringBuffer();
        JSONObject stats = new JSONObject(_monsterStats.toString());
        String hitPoints = "Hit Points: " + stats.getInt("hit_points") + "\n";
        monsterStats.append(hitPoints);
        String armorClass = "Armor Class: " + stats.getInt("armor_class") + "\n";
        monsterStats.append(armorClass);
        String hitDie = "Hit Dice: " + stats.getString("hit_dice") + "\n";
        monsterStats.append(hitDie);
        String speed = "Speed: " + stats.getString("speed") + "\n";
        monsterStats.append(speed);
        String strength = "Strength: " + stats.getInt("strength") + "\n";
        monsterStats.append(strength);
        String dexterity = "Dexterity: " + stats.getInt("dexterity") + "\n";
        monsterStats.append(dexterity);
        String constitution = "Constitution: " + stats.getInt("constitution") + "\n";
        monsterStats.append(constitution);
        String intelligence = "Intelligence: " + stats.getInt("intelligence") + "\n";
        monsterStats.append(intelligence);
        String wisdom = "Wisdom: " + stats.getInt("wisdom") + "\n";
        monsterStats.append(wisdom);
        String charisma = "Charisma:" + stats.getInt("charisma") + "\n";
        monsterStats.append(charisma);
        return monsterStats;
    }

}
