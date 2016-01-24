package de.czyrux.countrykata.core.domain.country;

import android.support.annotation.NonNull;

public class CountryImageBuilder {

    private static final String URL_BASE = "http://www.geonames.org/flags/x/";
    private static final String EXTENSION = ".gif";

    // Single StringBuilder to avoid GC. It is call on MainUiThread, so it does not need to be thread safe.
    private static final StringBuilder URL_BUILDER = new StringBuilder();

    @NonNull
    public static String obtainImageUrl(@NonNull Country country) {
        URL_BUILDER.setLength(0);
        return URL_BUILDER
                .append(URL_BASE)
                .append(country.getAlpha2Code().toLowerCase())
                .append(EXTENSION)
                .toString();
    }
}
