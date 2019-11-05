package view;
/*
Last updated November 4, 2019

This is the view controller for the primary application window.

Contributors:
Eva Moniz
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.characters.CharacterData;
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
    private Tab newCharacterTab;
    @FXML
    private Spinner diceRepetitionSpinner;

    /**
     * Stores the open character views mapped by uuid
     */
    private Map<String, CharacterViewController> openCharacters;
    private FXMLLoader fxmlLoader;

    @FXML
    private void initialize() {
        //initialize openCharacters to an empty map
        this.openCharacters = new HashMap<>();
        //initialize the fxmlLoader
        this.fxmlLoader = new FXMLLoader();

        // Set default open tab to the welcome tab
        this.tabs.getSelectionModel().select(this.welcomeTab);

        // Set behavior for "plus" tab
        this.newCharacterTab.setOnSelectionChanged(e -> this.plusTabSelected(e));

        try {
            // Set the welcome tab to display the welcome page
            WebEngine engine = this.welcomeWebView.getEngine();
            String urlString = Resources.getResourceUrl(Constants.WELCOME_PAGE).toString();
            engine.load(urlString);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set dice repetiton spinner to only allow values 1 to 10
        SpinnerValueFactory spinnerValFac = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        this.diceRepetitionSpinner.setValueFactory(spinnerValFac);
    }

    public void receiveCharacterData(CharacterData _characterData) {
        //create the character tab if it's not already open
        if (!this.openCharacters.containsKey(_characterData)) {
            try {
                this.createCharacterTab(_characterData.getUuid());
            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        //update the associated character view
        this.openCharacters.get(_characterData.getUuid()).receiveCharacterData(_characterData);
    }

    private CharacterViewController createCharacterTab(String _uuid) throws MalformedURLException, IOException {
        this.fxmlLoader.setLocation(Resources.getFxmlUrl(Constants.CHARACTER_FXML));
        Node characterNode = this.fxmlLoader.load();
        CharacterViewController characterViewController = this.fxmlLoader.getController();
        Tab tab = new Tab("temp", characterNode);
        this.tabs.getTabs().add(tab);
        this.tabs.getSelectionModel().select(tab);
        characterViewController.setTab(tab);
        return characterViewController;
    }

    private void plusTabSelected(Event e) {
        if (this.newCharacterTab.isSelected()) {
            DNDSApplication.getViewConnector().inputCreateCharacter();
        }
    }
}
