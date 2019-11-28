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

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
        try{
            Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .serializeNulls()
                .create();
        //takes the object data and puts it into format
        String data = gson.toJson(_character, Characters.class);
        //writes the character to file and closes that file
        FileWriter writer = new FileWriter(_file);
        writer.write(data);
        writer.close();
        return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Characters loadCharacter(File _file) {
        System.out.println("STUB: load character from " + _file);
        try{
        FileReader reader = new FileReader(_file);
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
