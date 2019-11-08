package dnds;
/*
Last updated: October 29th, 2019

This is the beginning of our CRUD operations. We will be saving locally and
delimiting files with the '!' character.

Contributors:
Brandon Pozil
Jonathon Bacon
 */

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.characters.Characters;

/**
 * A method to test using Gson for CRUD operations
 */
public class TestGson {

    final static String USER = System.getProperty("user.name");
    final static String DIR = "C:\\Users\\" + USER + "\\Downloads\\DnDs-Saves\\";
    final static String SAVE_APPEND = "'s-Save.json";

    //method for saving a character
    public static void saveCharacter(Characters _toWrite, String _location) throws IOException {
        //creates a gson builder and sets the flag for pretty printing (human readable printing)
        checkForSaveFolder();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .serializeNulls()
                .create();
        //takes the object data and puts it into format
        String data = gson.toJson(_toWrite, Characters.class);
        //writes the character to file and closes that file
        FileWriter writer = new FileWriter(DIR + _location + SAVE_APPEND);
        writer.write(data);
        writer.close();
    }

    //method for loading a character from its save
    public static Characters loadCharacter(String _toDecode) throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(DIR + _toDecode + SAVE_APPEND);
        StringBuilder builder = new StringBuilder();
        int i;
        while ((i = reader.read()) != -1) {
            builder.append((char) i);
        }
        reader.close();
        System.out.println("Readout");
        System.out.println(builder.toString());
        System.out.println("Building character");
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .serializeNulls()
                .create();
        Characters loaded = gson.fromJson(builder.toString(), Characters.class);
        return loaded;

    }

    //method to check if the save folder is in place
    public static void checkForSaveFolder() {
        File path = new File(DIR);
        if (!path.exists()) {
            System.out.println("save folder not found, creating folder");
            path.mkdir();
        }
    }

}
