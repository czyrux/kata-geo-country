package de.czyrux.countrykata.ui.detail.model;

public class CountryDetailTransformerFactory {

    private CountryDetailTransformerFactory() {
    }

    public static CountryDetailTransformer create() {
        return new CountryDetailTransformer(new LocationDataExtractor(), new TranslationsDataExtractor());
    }
}
