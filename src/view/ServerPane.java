package view;
/*
Last updated 2019-10-22

This class is a custom JFX control for the server selection/hosting/chat
component of the main window.

Contributors:
Eva Moniz
 */

import model.utilities.Resources;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * This class is a custom JFX control for the server selection/hosting/chat pane
 * component of the main window.
 *
 * @author Eva Moniz
 */
public class ServerPane extends SwitchPane {

    // FXML resource locations
    private static final String DISCONNECTED_PANE_FXML = "components/server_pane/disconnected";
    private static final String HOST_PANE_FXML = "components/server_pane/host";
    private static final String CLIENT_PANE_FXML = "components/server_pane/client";

    // View String identifiers
    private static final String DISCONNECTED = "disconnected";
    private static final String HOST = "host";
    private static final String CLIENT = "client";

    // Different views this ServerPane can show
    private Node disconnectedView;
    private Node hostView;
    private Node clientView;

    // Controllers for each view
    private ServerPaneDisconnectedController disconnectedController;

    public ServerPane() {
        try {
            // Initialize all views for the ServerPane.
            this.initializeDisconnectedView();
            this.initializeHostView();

            // Set the view to the default on startup.
            this.switchView(ServerPane.DISCONNECTED);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads the disconnected view of the {@code ServerPane}. Does all necessary
     * initialization of this view.
     *
     * @throws MalformedURLException
     * @throws IOException
     */
    private void initializeDisconnectedView() throws MalformedURLException, IOException {
        // Load FXML file for the disconnected view.
        URL disconnectedUrl = Resources.getFxmlUrl(
                ServerPane.DISCONNECTED_PANE_FXML);
        FXMLLoader disconnectedLoader = new FXMLLoader(disconnectedUrl);

        //Initialize the node and controller fields.
        this.disconnectedView = disconnectedLoader.load();
        this.disconnectedController = disconnectedLoader.getController();

        //Add the view to this SwitchPane as a switchable view
        this.addView(ServerPane.DISCONNECTED, this.disconnectedView);

        //Register event handlers with the view.
        this.disconnectedController.setStartServerAction(event -> startServerInput());
    }

    /**
     * Loads the host view of the {@code ServerPane}. Does all necessary
     * initialization of this view.
     *
     * @throws MalformedURLException
     * @throws IOException
     */
    private void initializeHostView() throws MalformedURLException, IOException {
        // Load FXML file for the host view.
        URL hostUrl = Resources.getFxmlUrl(ServerPane.HOST_PANE_FXML);
        FXMLLoader hostLoader = new FXMLLoader(hostUrl);

        // Initialize the node and controller fields.
        this.hostView = hostLoader.load();

        //Add the view to this SwitchPane as a switchable view
        this.addView(ServerPane.HOST, this.hostView);
    }

    /**
     * This function is called upon user input to start the server. It
     * communicates with the program controller that the user wishes to start
     * the server.
     */
    private void startServerInput() {
        this.switchView(ServerPane.HOST);
        //TODO: notify program controller that the user wishes to start the
        //server.
    }

}
