package model.API;

/**
 * Last Updated: December 3, 2019
 *
 * This classes purpose is to hold all the methods used by the various API calls
 * that are not specific to each API.
 *
 * Contributors: Brandon Pozil, Jon Bacon
 */
import java.io.*;
import java.net.*;

/**
 * This API Adapter class will serve as the main class that will take care of
 * any request grabbing and string cleaning for the D&DMonsterAPI.
 *
 * @param _url
 * @param _edited
 * @return
 * @throws MalformedURLException
 * @throws ProtocolException
 * @throws IOException
 * @throws InterruptedException
 */
public abstract class APIAdapter implements QuoteInterface {

    /**
     * This method calls out to an API. The _url should be declared in each
     * individual implementation for each API. The StringBuffer appends the
     * taken JSON data and returns it.
     *
     * @param _url
     * @param _edited
     * @return
     * @throws MalformedURLException
     * @throws ProtocolException
     * @throws IOException
     * @throws InterruptedException
     */
    public static StringBuffer getRequest(String _url, StringBuffer _edited) throws MalformedURLException, ProtocolException, IOException, InterruptedException {
        URL apiURL = new URL(_url);
        HttpURLConnection apiConnection = (HttpURLConnection) apiURL.openConnection();
        apiConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        apiConnection.setRequestMethod("GET");
        BufferedReader apiDataIn = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
        String in = apiDataIn.readLine();
        _edited.append(in);
        return _edited;
    }

    /**
     * This method is specifically to clean the D&D strings. It splits the
     * string at space and replaces them with +'s and replaces . However, this
     * could easily be adapted to an interface for each individual
     * implementation. This proved not to be necessary as we only really needed
     * it for 1 of our 3 API's.
     *
     * @param _toBeCleaned
     * @return
     */
    public static String cleanString(String _toBeCleaned) {
        String name = "";
        String[] cleaned = _toBeCleaned.split(" ");
        if (!_toBeCleaned.contains(" ")) {
            return _toBeCleaned.substring(0, 1).toUpperCase() + _toBeCleaned.substring(1);
        } else {
            StringBuffer cleanString = new StringBuffer();
            for (int i = 0; i < cleaned.length; i++) {
                String cleanStrings = cleaned[i].substring(0, 1).toUpperCase() + cleaned[i].substring(1);
                name = cleanString.append(cleanStrings).append(" ").toString();
            }
            return (name.substring(0, name.length() - 1).replaceAll(" ", "+"));
        }
    }
}
