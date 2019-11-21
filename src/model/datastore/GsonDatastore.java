package model.datastore;
/*
Last updated 21 November 2019

Saves and loads characters to and from the disk using Gson to encode the objects
as JSON data.

Contributors:
Brandon Pozil
Jonathan Bacon
Eva Moniz
 */

import java.io.File;
import model.characters.Characters;

/**
 * Saves and loads characters to and from the disk using Gson to encode the
 * objects as JSON data.
 *
 * @author Eva Moniz
 */
public class GsonDatastore implements DatastoreAdapter {

    @Override
    public boolean saveCharacter(Characters _character, File _file) {
        System.out.println("STUB: save character " + _character.getName());
        return false;
    }

    @Override
    public Characters loadCharacter(File _file) {
        System.out.println("STUB: load character from " + _file);
        return null;
    }

}
