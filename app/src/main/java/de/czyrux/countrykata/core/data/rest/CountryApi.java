package de.czyrux.countrykata.core.data.rest;

import java.util.List;

import de.czyrux.countrykata.core.domain.country.Country;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface CountryApi {

    @GET("/all")
    void getAllCountries(Callback<List<Country>> callback);

    @GET("/alpha")
    void getCountriesByCodes(@Query("codes") String codes, Callback<List<Country>> callback);

    @GET("/region/{region}")
    void getCountriesByRegion(@Path("region") String region, Callback<List<Country>> callback);

    @GET("/lang/{language}")
    void getCountriesByLanguage(@Path("language") String language, Callback<List<Country>> callback);
}
