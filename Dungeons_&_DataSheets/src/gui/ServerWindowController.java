package gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class ServerWindowController extends Controller{
    @FXML
    private void goBack() throws IOException{
        super.load(Constants.PREVIOUS_WINDOW);
    }
}
