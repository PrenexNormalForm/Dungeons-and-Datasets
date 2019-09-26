package gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    Parent root;
    Stage stage;
    String title = "Dungeons & DataSheets";

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            root = FXMLLoader.load(getClass().getResource("startWindow.fxml"));

            stage = primaryStage;
            stage.setTitle(title);
            constants.stage = stage;

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(600);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}