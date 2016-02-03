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
    public void getCountriesByCode(String[] countryCodes, final Callback<List<Country>> callback) {

        StringBuilder codesQuery = new StringBuilder();
        for (String code : countryCodes) {
            if (codesQuery.length() != 0) {
                codesQuery.append(';');
            }

            codesQuery.append(code);
        }

        restApi.getCountriesByCodes(codesQuery.toString(), new retrofit.Callback<List<Country>>() {
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
    public void getCountriesByRegion(String region, final Callback<List<Country>> callback) {
        restApi.getCountriesByRegion(region, new retrofit.Callback<List<Country>>() {
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
    public void getCountriesByLanguage(String language, final Callback<List<Country>> callback) {
        restApi.getCountriesByLanguage(language, new retrofit.Callback<List<Country>>() {
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

}
