package de.czyrux.countrykata.core.domain.country;

import java.util.List;

import de.czyrux.countrykata.core.domain.Callback;

public interface CountryRepository {
    void getAllCountries(Callback<List<Country>> callback);

    Country getCountryByCode(String countryCode);

    List<Country> getCountriesByCode(String[] countryCodes);

    List<Country> getCountriesByRegion(String region);

    List<Country> getCountriesByLanguage(String language);
}
