package dnds.view.gui;
/*
Last updated 9/27/2019

java.application.Application child for javafx. Initializes and starts the GUI.

Contributors:
Jonathan Bacon
Eva Moniz
 */

import dnds.model.utilities.Resources;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DNDSApplication extends Application {

    private Pane root;
    private Stage stage;
    private String title = "Dungeons & DataSheets";
    private AnchorPane serverPaneAnchor;
    private AnchorPane contentPaneAnchor;
    private AnchorPane controlPaneAnchor;

    @Override
    public void start(Stage primaryStage) {

        try {
            this.root = FXMLLoader.load(Resources.getFxmlUrl(Constants.START_WINDOW));

            stage = primaryStage;
            stage.setTitle(title);
            Constants.PREVIOUS_WINDOW = Constants.START_WINDOW;
            Constants.stage = stage;

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            this.serverPaneAnchor = (AnchorPane) this.root.lookup("#server_pane");

            System.out.println(this.serverPaneAnchor);

            loadServerPane(Constants.DEFAULT_SERVER_PANE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadServerPane(String _serverPane) {
        try {
            Node serverPaneNode = FXMLLoader.load(Resources.getFxmlUrl(_serverPane));
            AnchorPane.setTopAnchor(serverPaneNode, 0.0);
            AnchorPane.setBottomAnchor(serverPaneNode, 0.0);
            AnchorPane.setLeftAnchor(serverPaneNode, 0.0);
            AnchorPane.setRightAnchor(serverPaneNode, 0.0);
            this.serverPaneAnchor.getChildren().clear();
            this.serverPaneAnchor.getChildren().add(serverPaneNode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
