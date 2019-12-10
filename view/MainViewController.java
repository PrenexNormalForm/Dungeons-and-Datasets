package view;

/*
Last updated December 10, 2019

This is the view controller for the primary application window.

Contributors:
Jonathan Bacon
Eva Moniz
 */
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import model.chat.GroupChat;
import model.utilities.Resources;

/**
 * This is the view controller for the primary application window.
 *
 */
public class MainViewController {

    private static final int LOWER_QUOTE_BOUND = 1;
    private static final int UPPER_QUOTE_BOUND = 3;
    private static final int MAX_DICE_REPETITIONS = 10;
    private boolean chatRunning;
    private String name = "";
    private static ListProperty<String> chatProperty = new SimpleListProperty<>();
    private GroupChat GROUPCHAT;
    private int GROUP = -1;
    private String ROOM = "";
    private static final String monsterUUID = "99999999-8cf0-11bd-b23e-10b96e4ef00d";
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
    private Label mottoLabel;
    @FXML
    private TextField messageTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField groupTextField;
    @FXML
    private TextField roomTextField;
    @FXML
    private Button joinChatButton;
    @FXML
    private Button leaveChatButton;
    @FXML
    private TextField DnDSearchField;
    @FXML
    private Label monsterFoundLabel;

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
        this.mottoLabel.setText("Inspirational Quote: " + getMotto());

        //Assign the behavior associated with the "plus" tab.
        this.newCharacterTab.setOnSelectionChanged(e -> this.plusTabSelected());

        //Set dice repetiton spinner to only allow values 1 to MAX_DICE_REPETITIONS.
        SpinnerValueFactory spinnerValFac = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1, MainViewController.MAX_DICE_REPETITIONS, 1);
        this.diceRepetitionSpinner.setValueFactory(spinnerValFac);

        //Initialize the chat list view. Create the observable list and assign it.
        this.chatListView.itemsProperty().bind(chatProperty);
        MainViewController.chatProperty.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                chatScroll();
            }
        });
        //method used for setting dice button listeners
        setDice();
        //disables the small X's on each tab
        this.tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    /**
     * Method used for setting up dice listeners
     */
    private void setDice() {
        //Add event listeners to buttons;
        this.d4Button.setOnAction(e -> {
            this.rollDieButton(4);
        });
        this.d6Button.setOnAction(e -> {
            this.rollDieButton(6);
        });
        this.d8Button.setOnAction(e -> {
            this.rollDieButton(8);
        });
        this.d10Button.setOnAction(e -> {
            this.rollDieButton(10);
        });
        this.d12Button.setOnAction(e -> {
            this.rollDieButton(12);
        });
        this.d20Button.setOnAction(e -> {
            this.rollDieButton(20);
        });
    }

    /**
     * a method used for checking if a character tab has been closed and then
     * remove its index from the opened characters hashmap
     *
     * @param _tab
     */
    private void closeTab(Tab _tab) {
        _tab.getTabPane().getTabs().remove(_tab);
    }

    /**
     * Receives the monster data from the backend
     *
     * @param _monster
     */
    public void receiveMonsterdata(String _monster) {

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
     */
    private void plusTabSelected() {
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
        if (_characterUUID.toString().equals(MainViewController.monsterUUID)) {
            this.tabs.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
        } else {
            this.tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        }
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
     * This handles the randomized quote
     */
    private String getMotto() {
        int random = ThreadLocalRandom.current().nextInt(MainViewController.LOWER_QUOTE_BOUND, MainViewController.UPPER_QUOTE_BOUND);
        switch (random) {
            case 1:
                try {
                    return model.API.Swanson.getSwanson();
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                    return "Mr Swanson is unavailable for a quote at this time.";
                }

            case 2:
                try {
                    return model.API.Kanye.getKanye();
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                    return "Kanye is unavailable for a quote at this time.";
                }

            default:
                return "I've been tampered with";
        }
    }

    /**
     * Listening method for the dice roll buttons.
     *
     * @param _die The number of sides on the die
     */
    private void rollDieButton(int _die) {
        //Get the number of repetitions from the spinner and send the input to the controller.
        int repetitions = (Integer) this.diceRepetitionSpinner.getValue();
        String roll = DNDSApplication.getViewConnector().inputRollDye(repetitions, _die);
        //if statement to see if a message with the roll needs to be sent to chat
        if (chatRunning) {
            try {
                this.GROUPCHAT.playerMessage(roll);
            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ChatLog.addComment(roll);
        }
    }

    /**
     * Opens a save file dialog and sends the resulting filename to the
     * controller.
     *
     */
    @FXML
    private void saveAs() {
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
     */
    @FXML
    private void load() {
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
    public static void removeCharacter(UUID _uuid) {
        MainViewController.openCharacters.remove(_uuid);
    }

    /**
     * This handles applying settings
     *
     * @throws IOException
     */
    @FXML
    private void applySettings() throws IOException, InterruptedException {
        Boolean nameChanged = false;
        Boolean needsUpdate = false;
        String oldName = "";
        if (chatRunning) {
            if (!this.name.equals(getName())) {
                oldName = this.name;
                this.name = getName();
                nameChanged = true;
            }
            if (!this.ROOM.equals(this.roomTextField.getText())) {
                this.ROOM = this.roomTextField.getText();
                needsUpdate = true;
            }
            if (!(this.GROUP == getGroup())) {
                this.GROUP = getGroup();
                needsUpdate = true;
            }
            if (needsUpdate && nameChanged) {
                this.GROUPCHAT.updateName(this.name);
                this.GROUPCHAT.update(this.GROUP, this.ROOM, oldName);
            } else if (needsUpdate) {
                this.GROUPCHAT.update(this.GROUP, this.ROOM, this.name);
            } else if (nameChanged) {
                this.GROUPCHAT.updateName(this.name);
                this.GROUPCHAT.nameChangedMessage(oldName);
            }
        }

    }

    /**
     * This handles the joining chat
     *
     * @throws IOException
     */
    @FXML
    private void joinChat() throws IOException {
        //checks if the chat is currently running
        if (this.chatRunning) {
            ChatLog.addComment("Chat already joined");
        } else {
            this.name = getName();
            this.GROUP = getGroup();
            this.ROOM = this.roomTextField.getText();
            this.GROUPCHAT = new GroupChat(this.name, this.GROUP, this.ROOM);
            //calls to the groupchat to start the server with the passed in name
            this.GROUPCHAT.start();
            //sets the chat running status to true
            this.chatRunning = true;
            //updates the UI window
            updateChat();
        }
    }

    /**
     * This handles stopping the thread chat
     *
     * @throws IOException
     */
    @FXML
    private void stopChat() throws IOException {
        this.GROUPCHAT.stop();
        this.chatRunning = false;
    }

    /**
     * This handles getting the name from the settings box
     */
    private String getName() {
        if (this.name == "" && this.nameTextField.getText().trim().isEmpty()) {
            return "DEFAULT";
        } else {
            return this.nameTextField.getText();
        }
    }

    /**
     * This handles getting the group from the settings box
     */
    private int getGroup() {
        if (this.GROUP == -1 && this.groupTextField.getText().trim().isEmpty()) {
            return -1;
        } else if (isStringInt(this.groupTextField.getText())) {
            return Integer.parseInt(this.groupTextField.getText());
        } else {
            return -1;
        }
    }

    /**
     * This method checks if a string can be converted to integer
     *
     * @param s
     * @return
     */
    public boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * This method updates the linked property of chat
     */
    public static void updateChat() {
        //gets the chatlog
        ArrayList<String> log = ChatLog.getLog();
        //sets the bound chat property to a new observable array list
        MainViewController.chatProperty.set(FXCollections.observableArrayList(log));
    }

    /**
     * This method scrolls the chat to the bottom when receiving new chats
     */
    private void chatScroll() {
        //tells the UI to run this command when the thread is available
        Platform.runLater(() -> this.chatListView.scrollTo(chatListView.getItems().size()));
    }

    /**
     * This method handles sending comments/messages to the chat
     *
     * @throws IOException
     */
    @FXML
    private void addComment() throws IOException {
        //checks to see if the chat is running
        if (this.chatRunning) {
            //sends the message that has been typed in the comment box
            this.GROUPCHAT.playerMessage(this.messageTextField.getText());
            //clears the comment box
            this.messageTextField.setText("");
        }
    }

    /**
     * This is a method used for the chat box to submit messages when the enter
     * key is pressed
     *
     * @throws IOException
     */
    @FXML
    private void onEnter() throws IOException {
        addComment();
    }

    /**
     * This handles the search monster feature.
     *
     * @exception IOException
     * @exception MalformedURLException
     * @InterruptedException
     */
    @FXML
    private void searchMonster() throws IOException, ProtocolException, MalformedURLException, InterruptedException {
        if (!this.DnDSearchField.getText().trim().isEmpty()) {
            String search = this.DnDSearchField.getText();
            String monster = model.API.DandDMonsterAPI.doesMonsterExist(search);
            if (monster.equals("")) {
                this.monsterFoundLabel.setText("Monster not found. Check spelling.");
            } else {
                Platform.runLater(() -> {
                    try {
                        this.launchMonsterWindow(monster);
                    } catch (IOException ex) {
                        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                        this.monsterFoundLabel.setText("An error occured. possible network issues.");
                    }
                });
            }
        } else {
            this.monsterFoundLabel.setText("No Search was Entered.");
        }
    }

    /**
     * This handles pulling a random monster
     *
     * @exception IOException
     * @exception ProtocolException
     * @exception InterruptedException
     */
    @FXML
    private void randomMonster() throws IOException, MalformedURLException, ProtocolException, InterruptedException {
        String monster = model.API.DandDMonsterAPI.getRandomMonster();
        Platform.runLater(() -> {
            try {
                this.launchMonsterWindow(monster);
            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                this.monsterFoundLabel.setText("An error occured. possible network issues.");
            }
        });
    }

    /**
     * This handles launching a separate monster window.
     *
     * @param _monster
     * @exception MalformedURLException
     * @exception IOException
     */
    private void launchMonsterWindow(String _monster) throws MalformedURLException, IOException {
        Constants.MONSTER = _monster;
        //Load the character view fxml.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getFxmlUrl(Constants.MONSTER_FXML));
        Node monsterNode = loader.load();
        UUID uuid = UUID.fromString(MainViewController.monsterUUID);

        //Create a new tab and insert it into the second-to-last position in the
        //tab pane, just behind the 'plus' tab.
        Tab tab = new Tab("Monster", monsterNode);
        int tabPos = this.tabs.getTabs().size() - 1;
        this.tabs.getTabs().add(tabPos, tab);
        //Keep track of the currently opened character with selection change events
        tab.setOnSelectionChanged(e -> this.characterTabSelectionChanged(tab, uuid));

        //Select the tab.
        this.tabs.getSelectionModel().select(tab);

    }
}
