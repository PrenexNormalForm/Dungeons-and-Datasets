package view;
/*
Last updated November 27, 2019

This is the view controller for a character sheet. There is a separate instance
for each opened character sheet.

Contributors:
Jonathan Bacon
Eva Moniz
 */

import controller.ViewConnector;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import model.characters.CharacterClass;
import model.characters.CharacterData;
import model.characters.CharacterProperty;

/**
 * Controls the view of an open character sheet.
 *
 * @author Eva Moniz
 */
public class CharacterViewController {

    /**
     * The maximum value for a spinner control.
     */
    private static final int SPINNER_MAX = 20;
    private static final int LEVEL_SPINNER_MIN = 1;
    private static final int SPINNER_MIN = 3;

    /**
     * The tab that the character view is enclosed in.
     */
    @LinkedProperty(CharacterProperty.NAME)
    private Tab tab;
    /**
     * The UUID of the character.
     */
    private UUID uuid;
    /**
     * True if character data has been sent to this view at least once.
     */
    private boolean hasReceivedInitialData = false;

    //JFX Controls defined in FXML
    @FXML
    @LinkedProperty(CharacterProperty.NAME)
    private TextField nameTextField;
    @FXML
    @LinkedProperty(CharacterProperty.RACE)
    private TextField raceTextField;
    @FXML
    @LinkedProperty(CharacterProperty.ALIGN)
    private TextField alignmentTextField;
    @FXML
    @LinkedProperty(CharacterProperty.CLASS)
    private ChoiceBox classChoiceBox;
    @FXML
    @LinkedProperty(CharacterProperty.LEVEL)
    private Spinner levelSpinner;
    @FXML
    @LinkedProperty(CharacterProperty.NAME)
    private Label nameLabel;
    @FXML
    @LinkedProperty(CharacterProperty.LEVEL)
    private Label levelLabel;
    @FXML
    @LinkedProperty(CharacterProperty.CLASS)
    private Label classLabel;
    @FXML
    @LinkedProperty(CharacterProperty.STRENGTH)
    private Label strengthLabel;
    @FXML
    @LinkedProperty(CharacterProperty.DEXTERITY)
    private Label dexterityLabel;
    @FXML
    @LinkedProperty(CharacterProperty.CONSTITUTION)
    private Label constitutionLabel;
    @FXML
    @LinkedProperty(CharacterProperty.INTELLIGENCE)
    private Label intelligenceLabel;
    @FXML
    @LinkedProperty(CharacterProperty.WISDOM)
    private Label wisdomLabel;
    @FXML
    @LinkedProperty(CharacterProperty.CHARISMA)
    private Label charismaLabel;
    @FXML
    @LinkedProperty(CharacterProperty.STRENGTH)
    private Spinner strengthSpinner;
    @FXML
    @LinkedProperty(CharacterProperty.DEXTERITY)
    private Spinner dexteritySpinner;
    @FXML
    @LinkedProperty(CharacterProperty.CONSTITUTION)
    private Spinner constitutionSpinner;
    @FXML
    @LinkedProperty(CharacterProperty.INTELLIGENCE)
    private Spinner intelligenceSpinner;
    @FXML
    @LinkedProperty(CharacterProperty.WISDOM)
    private Spinner wisdomSpinner;
    @FXML
    @LinkedProperty(CharacterProperty.CHARISMA)
    private Spinner charismaSpinner;
    @FXML
    @LinkedProperty(CharacterProperty.BACKSTORY)
    private TextField backstoryTextField;
    @FXML
    @LinkedProperty(CharacterProperty.INVENTORY)
    private TextField inventoryTextField;

    /**
     * Initializes the content of a new character view.
     */
    @FXML
    private void initialize() {
        //Add character classes to the character class choice box.
        this.classChoiceBox.getItems().addAll(CharacterClass.values());

        //Create spinner value factories for the various spinners.
        //https://github.com/EnterpriseQualityCoding
        this.levelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(CharacterViewController.LEVEL_SPINNER_MIN, CharacterViewController.SPINNER_MAX));
        this.strengthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(CharacterViewController.SPINNER_MIN, CharacterViewController.SPINNER_MAX));
        this.dexteritySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(CharacterViewController.SPINNER_MIN, CharacterViewController.SPINNER_MAX));
        this.constitutionSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(CharacterViewController.SPINNER_MIN, CharacterViewController.SPINNER_MAX));
        this.intelligenceSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(CharacterViewController.SPINNER_MIN, CharacterViewController.SPINNER_MAX));
        this.wisdomSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(CharacterViewController.SPINNER_MIN, CharacterViewController.SPINNER_MAX));
        this.charismaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(CharacterViewController.SPINNER_MIN, CharacterViewController.SPINNER_MAX));

        //Initialize controls that are linked to character properties.
        this.forPropertyLink(this::initializePropertyLinkedControl);
    }

    /**
     * Initialize some control that is linked to a property. The initialization
     * is different depending on the type of control.
     *
     * @param _control The control linked to the property
     * @param _property The property linked to the control
     */
    private void initializePropertyLinkedControl(Object _control, CharacterProperty _property) {
        ViewConnector viewConnector = DNDSApplication.getViewConnector();

        //Create input listeners depending on the type of input control.
        if (_control.getClass().isAssignableFrom(Spinner.class)) {
            Spinner spinner = (Spinner) _control;
            //Create the listener for spinner input of a property.
            spinner.valueProperty().addListener(
                    ($, $$, val) -> viewConnector.inputCharacterProperty(this.uuid, _property, val)
            );
        } else if (_control.getClass().isAssignableFrom(ChoiceBox.class)) {
            ChoiceBox choiceBox = (ChoiceBox) _control;
            //Create the listener for choicebox input of a property.
            choiceBox.getSelectionModel().selectedItemProperty().addListener(
                    ($, $$, val) -> viewConnector.inputCharacterProperty(this.uuid, _property, val)
            );
        } else if (_control.getClass().isAssignableFrom(TextField.class)) {
            TextField textField = (TextField) _control;
            //Create the listeners for textfield input of a property.
            this.createTextFieldListeners(textField,
                    str -> viewConnector.inputCharacterProperty(this.uuid, _property, str));
        }
    }

    /**
     * Creates a listener for the given text field that calls the given string
     * consumer with the text value of the text field. This consumer is called
     * when the user presses enter or the textfield loses focus.
     *
     * @param _textField The text field to assign listeners to
     * @param _consumer The consumer function to pass the text field entry
     */
    private void createTextFieldListeners(TextField _textField, Consumer<String> _consumer) {
        //Add listener to pass text to the consumer upon the text field losing focus.
        _textField.focusedProperty().addListener((focus, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                _consumer.accept(_textField.getText());
            }
        });
        //Add listener to text field to unfocus upon pressing the enter key.
        _textField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                _textField.getParent().requestFocus();
            }
        });
    }

    /**
     * Receives and displays updated character data. Input controls (text
     * fields, spinners, etc.) will not be updated unless _updateInputControls
     * is true. This is to prevent infinite feedback loops.
     *
     * @param _data The character data to display
     * @param _updateInputControls Whether input controls should update their
     * values to reflect the data
     */
    protected void receiveCharacterData(CharacterData _data, boolean _updateInputControls) {
        //this line prints out when character data is changed/recieved
        //System.out.println(this + ": Received character data " + _data);

        //Update controls with their linked properties
        this.forPropertyLink((obj, property) -> {
            this.updatePropertyLinkedControl(obj, property, _data.getProperty(property), _updateInputControls);
        });

        this.hasReceivedInitialData = true;
    }

    /**
     * Updates the display of a property-linked control with a new value.
     *
     * @param _control The control to update
     * @param _property The character property linked to the control
     * @param _propertyValue The value of the property
     * @param _updateInputControls Whether input controls should be updated
     * (usually this is not necessary)
     */
    private void updatePropertyLinkedControl(Object _control, CharacterProperty _property, Object _propertyValue, boolean _updateInputControls) {
        if (Spinner.class.isAssignableFrom(_control.getClass()) && _updateInputControls) {
            //If the object is a spinner, update its spinner value.
            Spinner spinner = (Spinner) _control;
            spinner.getValueFactory().setValue(_propertyValue);
        } else if (ChoiceBox.class.isAssignableFrom(_control.getClass()) && _updateInputControls) {
            //If the object is a choice box, update the choice selection.
            ChoiceBox choiceBox = (ChoiceBox) _control;
            choiceBox.getSelectionModel().select(_propertyValue);
        } else if (TextInputControl.class.isAssignableFrom(_control.getClass()) && _updateInputControls) {
            //If the object is a text input, update the text.
            TextInputControl textControl = (TextInputControl) _control;
            textControl.setText(_propertyValue.toString());
        } else if (Labeled.class.isAssignableFrom(_control.getClass())) {
            //If the object is a labeled object, update the label.
            Labeled labeledControl = (Labeled) _control;
            labeledControl.setText(_propertyValue.toString());
        } else if (Tab.class.isAssignableFrom(_control.getClass())) {
            //If the object is a tab, update the tab title.
            Tab tab = (Tab) _control;
            tab.setText(_propertyValue.toString());
        }
    }

    /**
     * Call some consumer function for each property-linked control. The
     * consumer takes the control and the character property as input.
     *
     * @param _consumer The consumer to call for each property-linked control
     */
    private void forPropertyLink(BiConsumer<Object, CharacterProperty> _consumer) {
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                LinkedProperty annotation = field.getAnnotation(LinkedProperty.class
                );
                if (annotation != null) {
                    Object linkedControl = field.get(this);
                    CharacterProperty linkedProperty = annotation.value();
                    if (linkedControl != null) {
                        _consumer.accept(linkedControl, linkedProperty);

                    }
                }
            } catch (IllegalArgumentException | ReflectiveOperationException | SecurityException ex) {
                Logger.getLogger(CharacterViewController.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        }
    }
    /**
     * This handles closing the character and removing its UUID from the openCharacters map
     * @param _e
     */
    @FXML
    private void closeCharacter(ActionEvent _e){
        MainViewController.removeCharacter(this.uuid);
        this.tab.getTabPane().getTabs().remove(this.tab);
    }

    /**
     * This annotation type is used to hook JFX controls to Character properties
     * that they should reflect.
     */
    @Retention(RetentionPolicy.RUNTIME)
    private static @interface LinkedProperty {

        /**
         * The character property linked to the control
         */
        CharacterProperty value();
    }
//=========================GETTERS==========================================\\
    protected UUID getUUID(){
        return this.uuid;
    }

//=========================SETTERS==========================================\\
    protected void setTab(Tab _tab) {
        this.tab = _tab;
    }

    protected void setUUID(UUID _uuid) {
        this.uuid = _uuid;
    }
}
