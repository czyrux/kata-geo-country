package de.czyrux.countrykata.ui.detail.model;

public class CountryDetailViewModel {

    String flagImageUrl;
    String name;
    String alternativeName;
    String capital;
    String population;
    String demonym;
    String area;
    String gini;
    String callingCodes;
    String topLevelDomain;
    String currency;
    String languages;
    String alphaCodes;
    CountryLocationViewModel locationViewModel;
    CountryTranslationsViewModel translationsViewModel;

    public String getFlagImageUrl() {
        return flagImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public String getCapital() {
        return capital;
    }

    public String getDemonym() {
        return demonym;
    }

    public String getPopulation() {
        return population;
    }

    public String getArea() {
        return area;
    }

    public String getGini() {
        return gini;
    }

    public CountryLocationViewModel getLocationViewModel() {
        return locationViewModel;
    }

    public CountryTranslationsViewModel getTranslationsViewModel() {
        return translationsViewModel;
    }

    public String getCallingCodes() {
        return callingCodes;
    }

    public String getTopLevelDomain() {
        return topLevelDomain;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLanguages() {
        return languages;
    }

    public String getAlphaCodes() {
        return alphaCodes;
    }
}
