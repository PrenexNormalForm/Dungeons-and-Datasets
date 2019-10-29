package view;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.utilities.Resources;
/**
 *
 * @author Eva Moniz
 */
public class MainViewController {

    @FXML
    private TabPane tabs;
    @FXML
    private Tab welcomeTab;
    @FXML
    private WebView welcomeWebView;
    @FXML
    private Spinner diceRepetitionSpinner;

    @FXML
    private void initialize() {
        // Set default open tab to the welcome tab
        this.tabs.getSelectionModel().select(this.welcomeTab);

        try {
            // Set the welcome tab to display the welcome page
            WebEngine engine = this.welcomeWebView.getEngine();
            String url = Resources.getResourceUrl(Constants.WELCOME_PAGE).toString();
            engine.load(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set dice repetiton spinner to only allow values 1 to 10
        SpinnerValueFactory spinnerValFac = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        this.diceRepetitionSpinner.setValueFactory(spinnerValFac);
    }
}
