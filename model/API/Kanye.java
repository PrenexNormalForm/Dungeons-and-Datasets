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

/**
 * This Kanye class calls the Kanye API (Yes it does exist).
 * This API can only pull one quote at a time. So we gotta work with that.
 */

public class Kanye {
    /**
     * Enumerated values to avoid magic numbers and strings.
     */
    private static final String SIGNATURE = " - Our Lord and Savior Kanye";
    private static final String KANYE = "https://api.kanye.rest";
    private static StringBuffer stringIn = new StringBuffer();

    /**
     * Method to print the Kanye quote from the API.
     * @param _kanyeString
     * @return The Almighty Kanye String.
     */
    public static String printKanyeQuote(JSONObject _kanyeString) {
        return "\"" + _kanyeString.getString("quote") + "\"\n" + Kanye.SIGNATURE;
    }

    /**
     * Method to grab the data from the API. The exceptions must be here
     * to prevent any craziness happening with the URL.
     * @return The Glorious String.
     * @throws MalformedURLException
     * @throws IOException
     * @throws ProtocolException
     * @throws InterruptedException
     */
     public static String getKanye() throws MalformedURLException, IOException, ProtocolException, InterruptedException {
        getRequest(Kanye.KANYE,Kanye.stringIn);
        JSONObject returnData = new JSONObject(Kanye.stringIn.toString());
        return printKanyeQuote(returnData);
    }

}
