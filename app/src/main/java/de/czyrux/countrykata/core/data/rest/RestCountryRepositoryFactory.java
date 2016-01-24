package de.czyrux.countrykata.core.data.rest;

import de.czyrux.countrykata.core.domain.country.CountryRepository;
import retrofit.RestAdapter;

public class RestCountryRepositoryFactory {

    private static final String API_URL = "http://restcountries.eu/rest/v1";

    public static CountryRepository create() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setErrorHandler(new RetrofitErrorHandler())
                .build();

        CountryApi restApi = restAdapter.create(CountryApi.class);

        return new RestCountryRepository(restApi);
    }
}
