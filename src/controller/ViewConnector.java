package controller;

import java.util.UUID;
import model.characters.CharacterData;
import model.characters.CharacterProperty;

/**
 *
 * @author Eva Moniz
 */
public abstract class ViewConnector {

    public abstract void sendCharacterData(CharacterData _data);

    public abstract void inputCreateCharacter();

    public abstract <T> void inputCharacterProperty(UUID uuid, CharacterProperty property, T value);

}
