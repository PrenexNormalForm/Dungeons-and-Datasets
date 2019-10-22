package utilities;
/*
Last updated 10/08/2019

Manages the access of resources used by the program.

Contributors:
Eva Moniz
 */

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Manages the access of resources used by the program.
 *
 * This class cannot be instantiated.
 *
 * @author Eva Moniz
 */
public final class Resources {

    /**
     * The root directory of the resource structure.
     */
    private static final String RESOURCE_DIR = "/resources/";

    /**
     * Retrieve a {@link java.net.URL} object pointing to the FXML resource file
     * with the given name.
     *
     * @param _name The name (without the file extension) of the desired FXML
     * file
     * @return The URL to the FXML file
     * @throws MalformedURLException
     */
    public static URL getFxmlUrl(String _name) throws MalformedURLException {
        return Resources.getResourceUrl("fxml/" + _name + ".fxml");
    }

    /**
     * Retrieve a {@link java.net.URL} object pointing to the given resource.
     * Not recommended to call directly.
     *
     * @param _res_path The relative path to the resource from the resource root
     * directory
     * @return The URL to the resource
     * @throws MalformedURLException
     */
    public static URL getResourceUrl(String _res_path) throws MalformedURLException {
        return Resources.class.getResource(RESOURCE_DIR + _res_path);
    }

    private Resources() {
        //static only
    }
}
