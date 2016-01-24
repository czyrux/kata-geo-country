package de.czyrux.countrykata.core.domain.country;

import java.util.List;

import de.czyrux.countrykata.core.domain.Callback;

public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void getAllCountries(Callback<List<Country>> callback) {
        countryRepository.getAllCountries(callback);
    }
}
