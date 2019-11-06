package controller;
import model.characters.CharacterProperty;
import java.util.UUID;
import javafx.application.Platform;
import model.characters.CharacterData;
import view.MainViewController;
/**
 *
 * @author Eva Moniz
 */
public class JFXViewConnector extends ViewConnector {

    private MainViewController fxController;

    public JFXViewConnector(MainViewController _fxController) {
        this.fxController = _fxController;
    }

    @Override
    public void sendCharacterData(CharacterData _data) {
        Platform.runLater(() -> this.fxController.receiveCharacterData(_data));
    }

    @Override
    public void inputCreateCharacter() {
        Controller.createNewCharacter();
    }

    @Override
    public <T> void inputCharacterProperty(UUID _uuid, CharacterProperty _property, T _value) {
        Controller.updateCharacterProperty(_uuid, _property, _value);
    }

}
