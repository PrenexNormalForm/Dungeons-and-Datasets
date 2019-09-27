/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnds.gui;

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
}
