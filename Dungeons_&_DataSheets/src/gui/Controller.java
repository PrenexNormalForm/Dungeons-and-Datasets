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
import javafx.stage.Stage;


public class Controller {
    static Stage currentStage;
    @FXML
    public void load(String _screen) throws IOException{
        Stage window = (Stage) currentStage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(_screen+".fxml"));

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
    public static void setStage(Stage current){
        currentStage = current;
    }
}
