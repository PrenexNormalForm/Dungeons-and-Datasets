package dnds.gui;

import dnds.Resources;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

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

    public static void main(String[] args) {
        launch(args);
    }
}
