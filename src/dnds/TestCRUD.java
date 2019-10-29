package dnds;

/*
Last updated: October 29th, 2019

This is the beginning of our CRUD operations. We will be saving locally and
delimiting files with the '!' character.

Contributors:
Brandon Pozil
Jonathon Bacon
 */

import com.opencsv.CSVWriter;
import model.characters.Characters;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * This class will handle all the CRUD operations starting with create. This
 * uses a CSVWriter library to write a local CSV file, delimited with '!'.
 *
 */
public class TestCRUD {
    final static String USER = System.getProperty("user.name");
    final static String DIR = "C:\\Users\\" + USER + "\\Downloads";
    final static String SAVE_APPEND = "'s-Save.txt";

    //method used for creating a save given a character
    public static void createSave(Characters _char) throws IOException {
        try {
            List<String[]> characterData = new ArrayList<>();
            characterData.add(new String[]{_char.SaveString()});
            File newSave = new File(DIR,_char.getName() + SAVE_APPEND);
            FileWriter output = new FileWriter(newSave);
            CSVWriter writer = new CSVWriter(output, '!', CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "");
            writer.writeAll(characterData);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method used for reading a save file
    public static String read(String _file) {
        String data = "";
        try{
            File location = new File(DIR, _file + SAVE_APPEND);
            FileReader reader = new FileReader(location);
            int i;
            while((i=reader.read()) != -1){
                data+= ((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data;
    }
}
