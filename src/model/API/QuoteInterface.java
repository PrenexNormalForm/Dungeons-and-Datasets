package model.API;

/**
 *Last Updated: December 3, 2019
 *
 * An interface so that any API adapter needs to clean respective strings
 * and updated getRequest() methods.
 *
 *Contributors: Brandon Pozil, Jon Bacon
 */

public interface QuoteInterface {

    public abstract String getRequest();

    public abstract String cleanString();

}
