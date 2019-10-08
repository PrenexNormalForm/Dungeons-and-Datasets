/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnds.view.gui;
import dnds.model.utilities.Resources;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author Eva Moniz
 */
public class ServerPane extends AnchorPane {

    private Node disconnectedPane;

    public ServerPane() {
        try {
            URL disconnectedUrl = Resources.getFxmlUrl("components/server_pane/disconnected");
            this.disconnectedPane = FXMLLoader.load(disconnectedUrl);

            AnchorPane.setBottomAnchor(disconnectedPane, 0.0);
            AnchorPane.setTopAnchor(disconnectedPane, 0.);
            AnchorPane.setLeftAnchor(disconnectedPane, 0.);
            AnchorPane.setRightAnchor(disconnectedPane, 0.);

            this.getChildren().add(disconnectedPane);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
