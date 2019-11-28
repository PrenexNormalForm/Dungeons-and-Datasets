package model.datastore;
/*
Last updated 21 November 2019

This defines the interface for an object that stores and loads characters on
the disk.

Contributors:
Eva Moniz
 */

import java.io.File;
import model.characters.Characters;

/**
 * DatastoreAdapter defines the interface for an object that stores and loads
 * characters on the disk.
 *
 * @author Eva Moniz
 */
public interface DatastoreAdapter {

    /**
     * Save a character to the given file path.
     *
     * @param _character The character to store
     * @param _file The path where the character should be saved
     * @return Whether the character was saved successfully
     */
    public boolean saveCharacter(Characters _character, File _file);

    /**
     * Load a character from the given file path.
     *
     * @param _file The string identifying the character
     * @return The path where the character file can be found
     */
    public Characters loadCharacter(File _file);
}
