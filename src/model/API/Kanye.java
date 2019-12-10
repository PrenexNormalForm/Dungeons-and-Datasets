package model.API;

/**
 * Last Updated: December 3, 2019
 *
 * This pulls some sick ass Kanye quotes.
 *
 * Contributors: Brandon Pozil, Jon Bacon
 *
 */

import static model.API.APIAdapter.getRequest;
import java.io.*;
import java.net.*;
import org.json.*;

public class Kanye {
    private static final String SIGNATURE = " - Our Lord and Savior Kanye";
    private static final String KANYE = "https://api.kanye.rest";
    private static StringBuffer stringIn = new StringBuffer();

    public static String printKanyeQuote(JSONObject _kanyeString) {
        return "\"" + _kanyeString.getString("quote") + "\"\n" + Kanye.SIGNATURE;
    }

     public static String getKanye() throws MalformedURLException, IOException, ProtocolException, InterruptedException {
        getRequest(Kanye.KANYE,Kanye.stringIn);
        JSONObject returnData = new JSONObject(Kanye.stringIn.toString());
        return printKanyeQuote(returnData);
    }

}
