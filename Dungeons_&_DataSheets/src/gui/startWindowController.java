package gui;

import java.io.IOException;
import javafx.fxml.FXML;


public class startWindowController extends Controller {
    @FXML
    private void serverLaunchHelper() throws IOException{
        super.load("serverWindow");
        
    }
    @FXML
    private void localLaunchHelper() throws IOException{
        super.load("localWindow");
    }
    @FXML
    private void openSettingsHelper() throws IOException{
        super.load("settingsWindow");
    }

    
}
