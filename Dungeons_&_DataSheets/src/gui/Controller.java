/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Controller {
    @FXML
    public void load(String _screen) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(_screen+".fxml"));
        Scene scene = new Scene(root);
        constants.stage.setScene(scene);
        constants.stage.show();
    }
}
