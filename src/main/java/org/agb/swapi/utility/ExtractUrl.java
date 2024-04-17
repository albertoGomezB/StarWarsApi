package org.agb.swapi.utility;

public class ExtractUrl {

    private ExtractUrl() {
        throw new IllegalStateException("Utility class");
    }

    public static String extractIdFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
