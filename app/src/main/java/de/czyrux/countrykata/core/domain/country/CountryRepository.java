package de.czyrux.countrykata.core.domain.country;

import java.util.List;

import de.czyrux.countrykata.core.domain.Callback;

public interface CountryRepository {
    void getAllCountries(Callback<List<Country>> callback);

    void getCountriesByCode(String[] countryCodes, Callback<List<Country>> callback);

    void getCountriesByRegion(String region, Callback<List<Country>> callback);

    void getCountriesByLanguage(String language, Callback<List<Country>> callback);
}
