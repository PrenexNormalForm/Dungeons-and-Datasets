package controller;

import model.characters.CharacterData;

/**
 *
 * @author Eva Moniz
 */
public abstract class ViewConnector {

    public abstract void sendCharacterData(CharacterData _data);

    public abstract void inputCreateCharacter();
}
