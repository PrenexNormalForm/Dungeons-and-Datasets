package dnds.view.gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class ServerWindowController extends Controller{
    String currentScreen = Constants.SERVER_WINDOW;
    @FXML
    private void goBack() throws IOException{
        super.loadPreviousScreen(currentScreen);
    }
}
