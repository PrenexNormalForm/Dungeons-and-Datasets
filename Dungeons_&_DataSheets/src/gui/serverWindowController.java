package gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class serverWindowController extends Controller{
    @FXML
    private void goBack() throws IOException{
        super.load("startWindow");
    }
}
