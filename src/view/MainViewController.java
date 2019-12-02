package view;
/*
Last updated November 14, 2019

This is the view controller for the primary application window.

Contributors:
Jonathan Bacon
Eva Moniz
 */

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.characters.CharacterData;
import model.utilities.Resources;
/**
 * This is the view controller for the primary application window.
 *
 * @author Eva Moniz
 */
public class MainViewController {

    private static final int MAX_DICE_REPETITIONS = 10;
    private boolean chatRunning;
    private String name = "";
    private static  ListProperty<String> chatProperty = new SimpleListProperty<>();
    /**
     * The list of strings contained in the chat box of the window.
     */
    private ObservableList<String> chat;
    /**
     * The UUID of the currently selected character tab.
     */
    private UUID selectedCharacter;

    //JFX components defined in the FXML
    @FXML
    private TabPane tabs;
    @FXML
    private Tab welcomeTab;
    @FXML
    private Tab settingsTab;
    @FXML
    private Tab newCharacterTab;
    @FXML
    private Spinner diceRepetitionSpinner;
    @FXML
    private ListView chatListView;
    @FXML
    private Label mottoLbl;
    @FXML
    private Button welcomeCloseBtn;
    @FXML
    private Button settingsApplyBtn;
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
    @FXML
    private Button welcomeCloseButton;
    @FXML
    private Label mottoLabel;
    @FXML
    private TextField messageTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button joinChatButton;

    /**
     * Stores the open character views mapped by UUID.
     */
    private static Map<UUID, CharacterViewController> openCharacters;

    /**
     * Initializes the JFX component.
     */
    @FXML
    private void initialize() {
        this.chatRunning = false;
        //Initialize openCharacters to an empty map.
        this.openCharacters = new HashMap<UUID, CharacterViewController>();

        //Set the welcome tab as the tab open upon launching the program.
        this.tabs.getSelectionModel().select(this.welcomeTab);
        //this.mottoLabel.setText("Motto - \" We are the best \"");

        //Assign the behavior associated with the "plus" tab.
        this.newCharacterTab.setOnSelectionChanged(e -> this.plusTabSelected(e));


        //Set dice repetiton spinner to only allow values 1 to MAX_DICE_REPETITIONS.
        SpinnerValueFactory spinnerValFac = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1, MainViewController.MAX_DICE_REPETITIONS, 1);
        this.diceRepetitionSpinner.setValueFactory(spinnerValFac);

        //Initialize the chat list view. Create the observable list and assign it.
        this.chat = FXCollections.observableArrayList();
        this.chatListView.setItems(this.chat);
        this.chatListView.itemsProperty().bind(chatProperty);

        //Add event listeners to buttons;
        this.d4Button.setOnAction(e -> this.rollDieButton(4));
        this.d6Button.setOnAction(e -> this.rollDieButton(6));
        this.d8Button.setOnAction(e -> this.rollDieButton(8));
        this.d10Button.setOnAction(e -> this.rollDieButton(10));
        this.d12Button.setOnAction(e -> this.rollDieButton(12));
        this.d20Button.setOnAction(e -> this.rollDieButton(20));
        this.welcomeCloseButton.setOnAction(e -> this.closeTab(e, this.welcomeTab));
        this.tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }
    /**
     * a method used for checking if a character tab has been closed and then remove its index
     * from the opened characters hashmap
     * @param _e
     * @param _tab
     */
    private void closeTab(Event _e, Tab _tab){
        _tab.getTabPane().getTabs().remove(_tab);
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

        //Keep track of the currently opened character with selection change events
        tab.setOnSelectionChanged(e -> this.characterTabSelectionChanged(tab, _uuid));

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
     * Updates the currently selected character field when a character tab is
     * selected or loses selection.
     *
     * @param _tab The tab that has had a selection change
     * @param _characterUUID The UUID of the tab's character
     */
    private void characterTabSelectionChanged(Tab _tab, UUID _characterUUID) {
        if (_tab.isSelected()) {
            //Select the character if the tab is selected.
            this.selectedCharacter = _characterUUID;
        } else if (this.selectedCharacter.equals(_characterUUID)) {
            //Deselect the character if the tab no longer has selection.
            this.selectedCharacter = null;
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
    private void rollDieButton(int _die) {
        //Get the number of repetitions from the spinner and send the input to the controller.
        int repetitions = (Integer) this.diceRepetitionSpinner.getValue();
        DNDSApplication.getViewConnector().inputRollDye(repetitions, _die);
    }

    /**
     * Opens a save file dialog and sends the resulting filename to the
     * controller.
     *
     * @param _e The event that causes the save
     */
    @FXML
    private void saveAs(ActionEvent _e) {
        if (this.selectedCharacter == null) {
            //Can't save a character that doesn't exist!
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter(Constants.FILE_TYPES_STRING, Constants.FILE_TYPES)
        );
        File file = fileChooser.showSaveDialog(DNDSApplication.getPrimaryStage());
        if (file != null) {
            DNDSApplication.getViewConnector().inputSaveAs(this.selectedCharacter, file);
        }
    }

    /**
     * Opens an open file dialog and sends the resulting file to the controller.
     *
     * @param _e The event that causes the open dialog
     */
    @FXML
    private void load(ActionEvent _e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file...");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter(Constants.FILE_TYPES_STRING, Constants.FILE_TYPES)
        );
        File file = fileChooser.showOpenDialog(DNDSApplication.getPrimaryStage());
        if (file != null) {
            DNDSApplication.getViewConnector().inputLoadFile(file);
        }
    }
   /**
     * This handles removing closed characters from the openCharacters map
     */
    public static void removeCharacter(UUID _uuid){
        MainViewController.openCharacters.remove(_uuid);
    }

    @FXML
    private void applySettings(ActionEvent _e) throws IOException{
        String temp = "";
        if(!this.name.isEmpty()){
            temp = this.name;
        }

        this.name = this.nameTextField.getText();
        if(chatRunning){
            nameUpdated(temp,this.name);
        }
    }

    @FXML
    private void joinChat() throws IOException{
        if(this.chatRunning){
            ChatLog.addComment("Chat already joined");
        } else if (this.name == "" && this.nameTextField.getText().trim().isEmpty()){
            ChatLog.addComment("Please select a username in settings");
        } else{
            this.name = this.nameTextField.getText();
            model.chat.GroupChat.startServer();
            joinMessage();
            this.chatRunning = true;
            updateChat();
        }
    }

    public static void updateChat(){
        //gets the chatlog
        ArrayList<String> log = ChatLog.getLog();
        //sets the bound chat property to a new observable array list
        MainViewController.chatProperty.set(FXCollections.observableArrayList(log));
    }

    @FXML
    private void addComment() throws IOException{
        if(this.chatRunning){
            System.out.println("Sending");
            model.chat.GroupChat.sendMessage(this.name + ": " + this.messageTextField.getText());
            //clears the comment box
            this.messageTextField.setText("");
        }
    }

    private void joinMessage() throws IOException{
        model.chat.GroupChat.sendMessage(this.name + " has joined the chat!");
    }

    private void nameUpdated(String _old, String _new) throws IOException{
        model.chat.GroupChat.sendMessage(_old + " has chnaged their name to " + _new);
    }

}
