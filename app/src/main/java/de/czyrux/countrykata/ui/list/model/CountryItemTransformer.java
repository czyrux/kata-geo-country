package de.czyrux.countrykata.ui.list.model;

import android.support.annotation.NonNull;

import java.util.Locale;

import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryImageBuilder;
import de.czyrux.countrykata.ui.Transformer;

public class CountryItemTransformer implements Transformer<Country, CountryItemViewModel> {

    @Override
    public CountryItemViewModel transform(@NonNull Country country) {
        String imageUrl = CountryImageBuilder.obtainImageUrl(country);
        String population = String.format(Locale.GERMAN, "%,d", country.getPopulation());

        return new CountryItemViewModel(country.getName(), imageUrl, population, country.getSubregion(), country.getAlpha2Code());
    }
}
