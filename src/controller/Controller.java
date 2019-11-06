package controller;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.characters.CharacterClass;
import model.characters.CharacterData;
import model.characters.CharacterProperty;
import model.characters.Characters;
/**
 *
 * @author Eva Moniz
 */
public class Controller {

    private static Map<UUID, Characters> openCharacters = new HashMap<>();
    private static ViewConnector viewConnector;

    public static void setViewConnector(ViewConnector _viewConnector) {
        Controller.viewConnector = _viewConnector;
    }

    public static void createNewCharacter() {
        Characters character = new Characters();
        Controller.openCharacters.put(character.getUUID(), character);
        Controller.updateCharacterView(character);
    }

    public static <T> void updateCharacterProperty(UUID _uuid, CharacterProperty _property, T _value) {
        Characters character = Controller.openCharacters.get(_uuid);
        try {
            Method setter = Characters.class.getMethod(_property.getSetterName(), _property.getType());
            setter.invoke(character, _value);
            Controller.updateCharacterView(character);
        } catch (ReflectiveOperationException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, _property.toString(), ex);
        }
    }

    private static void updateCharacterView(Characters _character) {
        CharacterData data = new CharacterData(_character);
        Controller.viewConnector.sendCharacterData(data);
    }
}
