package gui;
/*
Last updated 9/27/2019

java.application.Application child for javafx. Initializes and starts the GUI.

Contributors:
Jonathan Bacon
Eva Moniz
 */

import utilities.Resources;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@code Application} class that controls the view.
 *
 * @author Eva Moniz
 */
public class DNDSApplication extends Application {

    //The minimum bounds for the window
    private static final int MIN_WINDOW_HEIGHT = 400;
    private static final int MIN_WINDOW_WIDTH = 500;

    /**
     * The root node of the entire view
     */
    private Parent root;
    /**
     * The main application window
     */
    private Stage stage;
    /**
     * The title of the application
     */
    private String title = "Dungeons & DataSheets";

    /**
     * Starts the application, and the logic thread.
     *
     * @param primaryStage the application window
     */
    @Override
    public void start(Stage primaryStage) {

        try {
            this.root = FXMLLoader.load(Resources.getFxmlUrl(Constants.START_WINDOW));

            this.stage = primaryStage;
            this.stage.setTitle(title);
            this.stage.setMinHeight(DNDSApplication.MIN_WINDOW_HEIGHT);
            this.stage.setMinWidth(DNDSApplication.MIN_WINDOW_WIDTH);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
