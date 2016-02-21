package de.czyrux.countrykata.ui.detail.model;

import android.support.annotation.NonNull;

import java.util.Map;

import de.czyrux.countrykata.core.domain.country.Country;

public class TranslationsDataExtractor {

    public CountryTranslationsViewModel extract(@NonNull Country country) {
        CountryTranslationsViewModel viewModel = new CountryTranslationsViewModel();

        if (country.getTranslations() != null) {
            for (Map.Entry<String, String> translationEntry : country.getTranslations().entrySet()) {
                viewModel.translations.add(new CountryTranslationsViewModel.Translation(translationEntry.getKey(), translationEntry.getValue()));
            }
        }

        return viewModel;
    }
}
