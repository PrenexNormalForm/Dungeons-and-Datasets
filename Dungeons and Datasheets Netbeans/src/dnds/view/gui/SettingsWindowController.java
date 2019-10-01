/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnds.view.gui;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author jodba5
 */
public class SettingsWindowController extends Controller {
    String currentScreen = Constants.SETTINGS_WINDOW;
    @FXML
    private void goBack() throws IOException{
        super.loadPreviousScreen(currentScreen);
    }
    @FXML
    private void sizeSmall(){
        Constants.stage.setWidth(1024);
        Constants.stage.setHeight(576);
    }
    @FXML
    private void sizeMedium(){
        Constants.stage.setWidth(1280);
        Constants.stage.setHeight(720);
    }
    @FXML
    private void sizeLarge(){
        Constants.stage.setWidth(1920);
        Constants.stage.setHeight(1080);
    }
}
