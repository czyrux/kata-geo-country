package de.czyrux.countrykata.core.data.rest;


import java.util.List;

import de.czyrux.countrykata.core.domain.Callback;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryRepository;
import retrofit.RetrofitError;
import retrofit.client.Response;


class RestCountryRepository implements CountryRepository {

    private final CountryApi restApi;

    protected RestCountryRepository(CountryApi countryApi) {
        this.restApi = countryApi;
    }

    @Override
    public void getAllCountries(final Callback<List<Country>> callback) {
        restApi.getAllCountries(new retrofit.Callback<List<Country>>() {
            @Override
            public void success(List<Country> countries, Response response) {
                callback.onSuccess(countries);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onFailure(error);
            }
        });
    }

    @Override
    public Country getCountryByCode(String countryCode) {

        Country country = null;
        List<Country> countryList = getCountriesByCode(new String[]{countryCode});
        if (countryList != null && countryList.size() >= 1) {
            country = countryList.get(0);
        }

        return country;
    }

    @Override
    public List<Country> getCountriesByCode(String[] countryCodes) {

        StringBuilder codesQuery = new StringBuilder();
        for (String code : countryCodes) {
            if (codesQuery.length() != 0) {
                codesQuery.append(';');
            }

            codesQuery.append(code);
        }

        return restApi.getCountriesByCodes(codesQuery.toString());
    }

    @Override
    public List<Country> getCountriesByRegion(String region) {
        return restApi.getCountriesByRegion(region);
    }

    @Override
    public List<Country> getCountriesByLanguage(String language) {
        return restApi.getCountriesByLanguage(language);
    }

}
