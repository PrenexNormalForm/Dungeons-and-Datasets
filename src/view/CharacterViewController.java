package view;
/*
Last updated November 4, 2019

This is the view controller for a character sheet. There is a separate instance
for each opened character sheet.

Contributors:
Eva Moniz
 */

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import model.characters.CharacterData;

/**
 * Controls the view of an open character sheet.
 *
 * @author Eva Moniz
 */
public class CharacterViewController {

    /**
     * The tab that the character view is enclosed in
     */
    private Tab tab;

    @FXML
    private void initialize() {

    }

    protected void receiveCharacterData(CharacterData _data) {
        //stub
        System.out.println("Received character data " + _data);
    }

    protected void setTab(Tab _tab) {
        this.tab = _tab;
    }
}
