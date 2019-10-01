package dnds.gui;
/*
Last updated 9/27/2019

Controls the switching of different scenes in the gui.

Contributors:
Jonathan Bacon
Eva Moniz
 */

import dnds.Resources;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Controller {

    public void load(String _screen) throws IOException {
        URL fxmlUrl = Resources.getFxmlUrl(_screen);
        Parent root = FXMLLoader.load(fxmlUrl);
        Scene scene = new Scene(root);
        Constants.stage.setScene(scene);
        Constants.stage.show();
    }
    public void loadPreviousScreen(String _current) throws IOException{
        load(Constants.PREVIOUS_WINDOW);
        setPrevious(_current);
    }
    public void setPrevious(String _Previous){
        Constants.PREVIOUS_WINDOW = _Previous;
    }
}
