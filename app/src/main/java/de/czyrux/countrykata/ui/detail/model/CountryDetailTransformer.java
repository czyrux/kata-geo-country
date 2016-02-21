package de.czyrux.countrykata.ui.detail.model;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryImageBuilder;
import de.czyrux.countrykata.ui.Transformer;

public class CountryDetailTransformer implements Transformer<Country, CountryDetailViewModel> {

    private static final String NAME_SEPARATOR = ", ";
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.getDefault());

    @VisibleForTesting
    static final String ARRAY_NO_CONTENT = "-";

    private final LocationDataExtractor locationDataExtractor;
    private final TranslationsDataExtractor translationsDataExtractor;

    public CountryDetailTransformer(LocationDataExtractor locationDataExtractor, TranslationsDataExtractor translationsDataExtractor) {
        this.locationDataExtractor = locationDataExtractor;
        this.translationsDataExtractor = translationsDataExtractor;
    }

    @Override
    public CountryDetailViewModel transform(@NonNull Country model) {

        CountryDetailViewModel viewModel = new CountryDetailViewModel();
        // TODO Injected
        viewModel.flagImageUrl = CountryImageBuilder.obtainImageUrl(model);
        viewModel.name = model.getNativeName();
        viewModel.alternativeName = getAlternativeName(model);
        viewModel.capital = model.getCapital();
        viewModel.demonym = model.getDemonym();
        viewModel.population = NUMBER_FORMAT.format(model.getPopulation());
        viewModel.area = NUMBER_FORMAT.format(model.getArea());
        viewModel.gini = NUMBER_FORMAT.format(model.getGini());

        viewModel.locationViewModel = locationDataExtractor.extract(model);
        viewModel.translationsViewModel = translationsDataExtractor.extract(model);

        viewModel.callingCodes = getArrayAsStringOrDefault(model.getCallingCodes());
        viewModel.topLevelDomain = getArrayAsStringOrDefault(model.getTopLevelDomain());
        viewModel.currency = getArrayAsStringOrDefault(model.getCurrencies());
        viewModel.languages = getArrayAsStringOrDefault(model.getLanguages());
        viewModel.alphaCodes = getAlphaCodes(model);

        return viewModel;
    }

    @NonNull
    private String getAlphaCodes(@NonNull Country model) {
        if (model.getAlpha3Code()== null) {
            return model.getAlpha2Code();
        }

        return model.getAlpha2Code() + NAME_SEPARATOR + model.getAlpha3Code();
    }

    @NonNull
    private String getArrayAsStringOrDefault(String[] array) {
        return array != null && array.length > 0 ? Arrays.toString(array) : ARRAY_NO_CONTENT;
    }

    @NonNull
    private String getAlternativeName(@NonNull Country model) {
        StringBuilder altNameBuilder = new StringBuilder();
        altNameBuilder.append(model.getName());

        if (model.getAlternativeSpellings() != null && model.getAlternativeSpellings().length > 0) {
            for (String name : model.getAlternativeSpellings()) {
                altNameBuilder.append(NAME_SEPARATOR).append(name);
            }
        }
        return altNameBuilder.toString();
    }
}
