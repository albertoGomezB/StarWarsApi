package org.agb.swapi.utility;

/**
 * Utility class to extract the id from a url.
 */
public class ExtractUrl {

    private ExtractUrl() {
        throw new IllegalStateException("Utility class");
    }

    public static String extractLastOneCharacterFromUrl(String url) {

        if(url == null || url.isEmpty()) {
            return "invalid"; // https://swapi.dev/api/planet/invalid/ -> invalid url
        }
        // Extract the id from the url : Example : https://swapi.dev/api/planet/1/ -> 1
        return url.substring(url.lastIndexOf("/") - 1);
    }

    public static String extractLastTwoCharactersFromUrl(String url) {

        if(url == null || url.isEmpty()) {
            return "invalid"; // https://swapi.dev/api/planet/invalid/ -> invalid url
        }
        // Extract the id from the url : Example : https://swapi.dev/api/vehicle/14/ -> 14
        return url.substring(url.lastIndexOf("/") - 2);
    }


}
