package de.czyrux.countrykata.ui.list.model;

import android.support.annotation.NonNull;

import java.util.Locale;

import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryImageBuilder;
import de.czyrux.countrykata.ui.Transformer;

public class CountryTransformer implements Transformer<Country, CountryUIModel> {

    @Override
    public CountryUIModel transform(@NonNull Country country) {
        String imageUrl = CountryImageBuilder.obtainImageUrl(country);
        String population = String.format(Locale.GERMAN, "%,d", country.getPopulation());

        return new CountryUIModel(country.getName(), imageUrl, population, country.getSubregion(), country.getAlpha2Code());
    }
}
