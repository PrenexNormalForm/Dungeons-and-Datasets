package view;
/*
Last updated November 6, 2019

This is the view controller for a character sheet. There is a separate instance
for each opened character sheet.

Contributors:
Eva Moniz
 */

import controller.ViewConnector;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
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

    private static final int SPINNER_MAX = 20;

    /**
     * The tab that the character view is enclosed in
     */
    @LinkedProperty(CharacterProperty.NAME)
    private Tab tab;
    /**
     * The UUID of the character
     */
    private UUID uuid;

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

    @FXML
    private void initialize() {
        //Initialize the content of the character view.
        this.classChoiceBox.getItems().addAll(CharacterClass.values());
        this.levelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, CharacterViewController.SPINNER_MAX));

        //Connect event listening code.
        ViewConnector viewConnector = DNDSApplication.getViewConnector();
        this.classChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                ($, $$, newVal) -> this.onClassSelection((CharacterClass) newVal));
        this.levelSpinner.valueProperty().addListener(
                ($, $$, val) -> viewConnector.inputCharacterProperty(this.uuid, CharacterProperty.LEVEL, val));
        this.createTextFieldListener(nameTextField,
                str -> viewConnector.inputCharacterProperty(this.uuid, CharacterProperty.NAME, str));
    }

    private void createTextFieldListener(TextField field, Consumer<String> consumer) {
        //Add listener to update value upon the text field losing focus.
        field.focusedProperty().addListener((focus, oldValue, newValue) -> {
            if (oldValue.booleanValue() && !newValue.booleanValue()) {
                consumer.accept(field.getText());
            }
        });
        //Add listener to text field to unfocus upon pressing the enter key.
        field.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                field.getParent().requestFocus();
            }
        });
    }

    protected void receiveCharacterData(CharacterData _data) {
        System.out.println(this + ": Received character data " + _data);

        //Update easy text-based properties automatically.
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                LinkedProperty listenAnnotation = field.getAnnotation(LinkedProperty.class);
                if (listenAnnotation != null) {
                    Object listeningObject = field.get(this);
                    CharacterProperty listenedProperty = listenAnnotation.value();
                    Object propertyValue = _data.getProperty(listenedProperty);
                    this.updatePropertyListeningObject(listeningObject, listenedProperty, propertyValue);
                }
            } catch (IllegalArgumentException | ReflectiveOperationException | SecurityException ex) {
                Logger.getLogger(CharacterViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void updatePropertyListeningObject(Object _object, CharacterProperty _property, Object _propertyValue)
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (_object.getClass().isAssignableFrom(Spinner.class)) {
            Spinner spinner = (Spinner) _object;
            spinner.getValueFactory().setValue(_propertyValue);
        } else if (_object.getClass().isAssignableFrom(ChoiceBox.class)) {
            ChoiceBox choiceBox = (ChoiceBox) _object;
            choiceBox.getSelectionModel().select(_propertyValue);
        } else {
            Method setTextMethod = _object.getClass().getMethod("setText", String.class);
            setTextMethod.invoke(_object, _propertyValue.toString());
        }
    }

    private void onClassSelection(CharacterClass newValue) {
        ViewConnector viewConnector = DNDSApplication.getViewConnector();
        viewConnector.inputCharacterProperty(this.uuid, CharacterProperty.CLASS, newValue);
    }

    /**
     * This annnotation type is used to hook jfx components to Character
     * properties that they should reflect.
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
