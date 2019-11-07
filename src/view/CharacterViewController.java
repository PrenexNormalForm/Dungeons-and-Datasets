package view;
/*
Last updated November 7, 2019

This is the view controller for a character sheet. There is a separate instance
for each opened character sheet.

Contributors:
Eva Moniz
 */

import controller.ViewConnector;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
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

    /**
     * The tab that the character view is enclosed in.
     */
    @LinkedProperty(CharacterProperty.NAME)
    private Tab tab;
    /**
     * The UUID of the character.
     */
    private UUID uuid;

    //JFX Controls defined in FXML
    @FXML
    @LinkedProperty(CharacterProperty.NAME)
    private TextField nameTextField;
    @FXML
    private TextField raceTextField;
    @FXML
    @LinkedProperty(CharacterProperty.CLASS)
    private ChoiceBox classChoiceBox;
    @FXML
    @LinkedProperty(CharacterProperty.LEVEL)
    private Spinner levelSpinner;
    @FXML
    private TextField alignmentTextField;
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

    /**
     * Initializes the content of a new character view.
     */
    @FXML
    private void initialize() {
        //Add character classes to the character class choice box.
        this.classChoiceBox.getItems().addAll(CharacterClass.values());

        //Create spinner value factories for the various spinners.
        //https://github.com/EnterpriseQualityCoding
        this.levelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, CharacterViewController.SPINNER_MAX));
        this.strengthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, CharacterViewController.SPINNER_MAX));
        this.dexteritySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, CharacterViewController.SPINNER_MAX));
        this.constitutionSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, CharacterViewController.SPINNER_MAX));
        this.intelligenceSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, CharacterViewController.SPINNER_MAX));
        this.wisdomSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, CharacterViewController.SPINNER_MAX));
        this.charismaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, CharacterViewController.SPINNER_MAX));

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
     * Receives and displays updated character data.
     *
     * @param _data The character data to display
     */
    protected void receiveCharacterData(CharacterData _data) {
        System.out.println(this + ": Received character data " + _data);

        //Update controls with their linked properties
        this.forPropertyLink((obj, property) -> {
            this.updatePropertyLinkedControl(obj, property, _data.getProperty(property));
        });
    }

    /**
     * Updates the display of a property-linked control with a new value.
     *
     * @param _control The control to update
     * @param _property The character property linked to the control
     * @param _propertyValue The value of the property
     */
    private void updatePropertyLinkedControl(Object _control, CharacterProperty _property, Object _propertyValue) {
        if (_control.getClass().isAssignableFrom(Spinner.class)) {
            //If the object is a spinner, update its spinner value.
            Spinner spinner = (Spinner) _control;
            spinner.getValueFactory().setValue(_propertyValue);
        } else if (_control.getClass().isAssignableFrom(ChoiceBox.class)) {
            //If the object is a choice box, update the choice selection.
            ChoiceBox choiceBox = (ChoiceBox) _control;
            choiceBox.getSelectionModel().select(_propertyValue);
        } else {
            //By default, attempt to find a setText method in the object and pass
            //the property value to it.
            try {
                Method setTextMethod = _control.getClass().getMethod("setText", String.class);
                setTextMethod.invoke(_control, _propertyValue.toString());
            } catch (ReflectiveOperationException | SecurityException | IllegalArgumentException ex) {
                Logger.getLogger(CharacterViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                LinkedProperty listenAnnotation = field.getAnnotation(LinkedProperty.class);
                if (listenAnnotation != null) {
                    Object listeningObject = field.get(this);
                    CharacterProperty listenedProperty = listenAnnotation.value();
                    if (listeningObject != null) {
                        _consumer.accept(listeningObject, listenedProperty);
                    }
                }
            } catch (IllegalArgumentException | ReflectiveOperationException | SecurityException ex) {
                Logger.getLogger(CharacterViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This annotation type is used to hook JFX controls to Character properties
     * that they should reflect.
     */
    @Retention(RetentionPolicy.RUNTIME)
    private static @interface LinkedProperty {

        CharacterProperty value();
    }

    //=========================SETTERS==========================================
    protected void setTab(Tab _tab) {
        this.tab = _tab;
    }

    protected void setUUID(UUID _uuid) {
        this.uuid = _uuid;
    }
}
