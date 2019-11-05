package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.characters.CharacterData;
import model.characters.Characters;
/**
 *
 * @author Eva Moniz
 */
public class Controller {

    private static Map<String, Characters> openCharacters = new HashMap<>();
    private static ViewConnector viewConnector;

    public static void setViewConnector(ViewConnector _viewConnector) {
        Controller.viewConnector = _viewConnector;
    }

    public static void createNewCharacter() {
        Characters character = new Characters();
        Controller.openCharacters.put(character.getUUID(), character);
        Controller.updateCharacterView(character);
    }

    private static void updateCharacterView(Characters _character) {
        CharacterData data = new CharacterData(_character);
        Controller.viewConnector.sendCharacterData(data);
    }
}
