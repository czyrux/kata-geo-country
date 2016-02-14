package de.czyrux.countrykata.core.domain.country;

import java.util.List;

import de.czyrux.countrykata.core.domain.Callback;
import de.czyrux.countrykata.core.domain.exception.CountryNotFoundException;

public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void getAllCountries(Callback<List<Country>> callback) {
        countryRepository.getAllCountries(callback);
    }

    public void getCountryByCode(final String countryCode, final Callback<Country> callback) {
        countryRepository.getCountriesByCode(new String[]{countryCode}, new Callback<List<Country>>() {
            @Override
            public void onSuccess(List<Country> response) {
                if (response != null && response.size() >= 1) {
                    Country country = response.get(0);
                    callback.onSuccess(country);
                } else {
                    callback.onFailure(new CountryNotFoundException(countryCode));
                }
            }

            @Override
            public void onFailure(Throwable error) {
                callback.onFailure(error);
            }
        });
    }

}
