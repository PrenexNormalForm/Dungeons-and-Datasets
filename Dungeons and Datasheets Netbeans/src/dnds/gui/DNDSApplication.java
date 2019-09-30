package dnds.gui;
/*
Last updated 9/27/2019

java.application.Application child for javafx. Initializes and starts the GUI.

Contributors:
Jonathan Bacon
Eva Moniz
 */

import dnds.Resources;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DNDSApplication extends Application {

    Parent root;
    Stage stage;
    String title = "Dungeons & DataSheets";

    @Override
    public void start(Stage primaryStage) {

        try {
            root = FXMLLoader.load(Resources.getFxmlUrl(Constants.START_WINDOW));

            stage = primaryStage;
            stage.setTitle(title);
            Constants.PREVIOUS_WINDOW = Constants.START_WINDOW;
            Constants.stage = stage;

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(600);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
