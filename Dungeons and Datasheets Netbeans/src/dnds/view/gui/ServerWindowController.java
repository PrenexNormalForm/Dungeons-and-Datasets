package dnds.view.gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class ServerWindowController extends Controller{
    @FXML
    private void goBack() throws IOException{
        Constants.PREVIOUS_WINDOW = Constants.SERVER_WINDOW;
        super.load(Constants.PREVIOUS_WINDOW);
    }
}
