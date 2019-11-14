package view;
/*
Last updated November 7, 2019

This is the view controller for the primary application window.

Contributors:
Eva Moniz
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.characters.CharacterData;
import model.utilities.Resources;
/**
 * This is the view controller for the primary application window.
 *
 * @author Eva Moniz
 */
public class MainViewController {

    private static final int MAX_DICE_REPETITIONS = 10;

    /**
     * The list of strings contained in the chat box of the window.
     */
    private ObservableList<String> chat;

    //JFX components defined in the FXML
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
    @FXML
    private ListView chatListView;
    @FXML
    private Button d4Button;
    @FXML
    private Button d6Button;
    @FXML
    private Button d8Button;
    @FXML
    private Button d10Button;
    @FXML
    private Button d12Button;
    @FXML
    private Button d20Button;

    /**
     * Stores the open character views mapped by UUID.
     */
    private Map<UUID, CharacterViewController> openCharacters;

    /**
     * Initializes the JFX component.
     */
    @FXML
    private void initialize() {
        //Initialize openCharacters to an empty map.
        this.openCharacters = new HashMap<>();

        //Set the welcome tab as the tab open upon launching the program.
        this.tabs.getSelectionModel().select(this.welcomeTab);

        //Assign the behavior associated with the "plus" tab.
        this.newCharacterTab.setOnSelectionChanged(e -> this.plusTabSelected(e));

        //CJonfigure the welcome tab to display the welcome page.
        try {
            WebEngine engine = this.welcomeWebView.getEngine();
            String urlString = Resources.getResourceUrl(Constants.WELCOME_PAGE).toString();
            engine.load(urlString);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Set dice repetiton spinner to only allow values 1 to MAX_DICE_REPETITIONS.
        SpinnerValueFactory spinnerValFac = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1, MainViewController.MAX_DICE_REPETITIONS, 1);
        this.diceRepetitionSpinner.setValueFactory(spinnerValFac);

        //Initialize the chat list view. Create the observable list and assign it.
        this.chat = FXCollections.observableArrayList();
        this.chatListView.setItems(this.chat);

        //Add event listeners to buttons;
        this.d4Button.setOnAction(e -> this.rollDieButton(4));
        this.d6Button.setOnAction(e -> this.rollDieButton(6));
        this.d8Button.setOnAction(e -> this.rollDieButton(8));
        this.d10Button.setOnAction(e -> this.rollDieButton(10));
        this.d12Button.setOnAction(e -> this.rollDieButton(12));
        this.d20Button.setOnAction(e -> this.rollDieButton(20));
    }

    /**
     * Receives and handles character data from the backend. This will update an
     * already-opened view or create a new view depending on the UUID of the
     * character.
     *
     * @param _characterData The CharacterData object to display.
     */
    public void receiveCharacterData(CharacterData _characterData) {
        boolean isNewCharacter = false;
        //Create the character tab if it doesn't already exist.
        if (!this.openCharacters.containsKey(_characterData.getUuid())) {
            isNewCharacter = true;
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
        //Route the character data to the appropriate controller by UUID.
        CharacterViewController character = this.openCharacters.get(_characterData.getUuid());
        character.receiveCharacterData(_characterData, isNewCharacter);
    }

    /**
     * Creates a new tab associated with the given character UUID.
     *
     * @param _uuid The character uuid
     * @return The controller for the tab content
     * @throws MalformedURLException
     * @throws IOException
     */
    private CharacterViewController createCharacterTab(UUID _uuid) throws MalformedURLException, IOException {
        //Load the character view fxml.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getFxmlUrl(Constants.CHARACTER_FXML));
        Node characterNode = loader.load();
        CharacterViewController characterViewController = loader.getController();

        //Create a new tab and insert it into the second-to-last position in the
        //tab pane, just behind the 'plus' tab.
        Tab tab = new Tab("", characterNode);
        int tabPos = this.tabs.getTabs().size() - 1;
        this.tabs.getTabs().add(tabPos, tab);
        characterViewController.setTab(tab);

        //Select the tab.
        this.tabs.getSelectionModel().select(tab);

        return characterViewController;
    }

    /**
     * Handle the selection of the "plus" tab, which creates a new character.
     *
     * @param _e The selection event
     */
    private void plusTabSelected(Event _e) {
        if (this.newCharacterTab.isSelected()) {
            DNDSApplication.getViewConnector().inputCreateCharacter();
        }
    }

    /**
     * Display the given message string in the chat box.
     *
     * @param _message The message to display.
     */
    public void displayMessage(String _message) {
        this.chat.add(_message);
        //Scroll to the bottom
        this.chatListView.scrollTo(this.chat.size() - 1);
    }

    /**
     * Listening method for the dice roll buttons.
     *
     * @param _die The number of sides on the die
     */
    public void rollDieButton(int _die) {
        //Get the number of repetitions from the spinner and send the input to the controller.
        int repetitions = (Integer) this.diceRepetitionSpinner.getValue();
        DNDSApplication.getViewConnector().rollDie(repetitions, _die);
    }
}
