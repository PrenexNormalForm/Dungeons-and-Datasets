package controller;
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

}
