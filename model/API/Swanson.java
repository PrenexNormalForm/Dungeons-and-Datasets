package model.API;

/**
 *Last Updated: December 3, 2019
 *
 * This calls awesome ass Ron Swanson quotes.
 *
 *Contributors: Brandon Pozil, Jon Bacon.
 */

import static model.API.APIAdapter.getRequest;
import java.io.*;
import java.net.*;
import org.json.*;

public class Swanson {
    private static final String SIGNATURE = " - Ron Swanson";
    private static final String MR_SWANSON = "https://ron-swanson-quotes.herokuapp.com/v2/quotes";
    private static StringBuffer singleStringIn = new StringBuffer();
    private static StringBuffer multiStringIn = new StringBuffer();

    public static String printQuotes(JSONArray _toBePrinted) {
        String results = "";
        for (int i = 0; i < _toBePrinted.length(); i++) {
            results = _toBePrinted.getString(i);
            //System.out.println(_toBePrinted.getString(i));
        }
        return "\"" + results + "\"";
    }

    public static String getSwanson() throws MalformedURLException, IOException, ProtocolException, InterruptedException {
        getRequest(Swanson.MR_SWANSON, Swanson.singleStringIn);
        JSONArray returnData = new JSONArray(singleStringIn.toString());
        return printQuotes(returnData) +"\n" + Swanson.SIGNATURE;
    }

    public static String getSwansons(int _numQuotes) throws MalformedURLException, IOException, ProtocolException, InterruptedException {
        String toFetch = Swanson.MR_SWANSON + "/" + _numQuotes;
        getRequest(toFetch, Swanson.multiStringIn);
        JSONArray returnData = new JSONArray(Swanson.multiStringIn.toString());
        return printQuotes(returnData);
    }

}
