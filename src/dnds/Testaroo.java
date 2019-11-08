package dnds;
import com.google.gson.Gson;
import java.io.IOException;
import model.characters.CharacterClass;
import model.characters.Characters;
import model.items.Item;
/**
 *
 * @author Eva Moniz
 */
public class Testaroo {

    public static void main(String[] args) throws IOException {
        Characters character = new Characters();
        character.setCharacterClass(CharacterClass.BARD);
        character.setName("asdfasdf");
        character.setLevel(13);
        character.setWisdom(20);

        Item item = new Item();
        item.setName("hallo");
        item.setCost(123);
        item.setWeight(123123);
        character.addItem(item);

//        Gson gson = new Gson();
//        String serialized = gson.toJson(character);
//        System.out.println(serialized);
//        Characters deserialized = gson.fromJson(serialized, Characters.class);
//        System.out.println(deserialized);
        TestGson.saveCharacter(character, "testaroo");
        character = TestGson.loadCharacter("testaroo");
        System.out.println(character);
    }
}
