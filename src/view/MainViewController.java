package view;
/*
Last updated November 6, 2019

This is the view controller for the primary application window.

Contributors:
Eva Moniz
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
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
    private Map<UUID, CharacterViewController> openCharacters;

    @FXML
    private void initialize() {
        //initialize openCharacters to an empty map
        this.openCharacters = new HashMap<>();

        // Set default open tab to the welcome tab
        this.tabs.getSelectionModel().select(this.welcomeTab);

        // Set behavior for "plus" tab
        this.newCharacterTab.setOnSelectionChanged(e -> this.plusTabSelected(e));

        // Set the welcome tab to display the welcome page
        try {
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
        //Create the character tab if it doesn't already exist.
        if (!this.openCharacters.containsKey(_characterData.getUuid())) {
            try {
                CharacterViewController character;
                character = this.createCharacterTab(_characterData.getUuid());
                character.setUUID(_characterData.getUuid());
                this.openCharacters.put(_characterData.getUuid(), character);
            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, _characterData.toString(), ex);
                return;
            }
        }
        //Update the associated character view.
        CharacterViewController character = this.openCharacters.get(_characterData.getUuid());
        character.receiveCharacterData(_characterData);
    }

    private CharacterViewController createCharacterTab(UUID _uuid) throws MalformedURLException, IOException {
        //Load Character view fxml.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getFxmlUrl(Constants.CHARACTER_FXML));
        Node characterNode = loader.load();
        CharacterViewController characterViewController = loader.getController();

        //Create a new tab and insert it into the second-to-last position in the
        //tab pane, just behind the 'plus' tab.
        Tab tab = new Tab("", characterNode);
        int tabPos = this.tabs.getTabs().size() - 1;
        this.tabs.getTabs().add(tabPos, tab);
        this.tabs.getSelectionModel().select(tab);

        //Select the tab.
        characterViewController.setTab(tab);

        return characterViewController;
    }

    private void plusTabSelected(Event e) {
        if (this.newCharacterTab.isSelected()) {
            DNDSApplication.getViewConnector().inputCreateCharacter();
        }
    }
}
