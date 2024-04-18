package org.agb.swapi.utility;

/**
 * Utility class to extract the id from a url.
 */
public class ExtractUrl {

    private ExtractUrl() {
        throw new IllegalStateException("Utility class");
    }

    public static String extractIdFromUrl(String url) {

        if(url == null || url.isEmpty()) {
            return "invalid"; // https://swapi.dev/api/planet/invalid/ -> invalid url
        }
        // Extract the id from the url : Example : https://swapi.dev/api/planet/1/ -> 1
        return url.substring(url.lastIndexOf("/") - 1);
    }
}
