package de.czyrux.countrykata.ui.detail.model;

import java.util.ArrayList;
import java.util.List;

public class CountryTranslationsViewModel {

    final List<Translation> translations = new ArrayList<>();

    public List<Translation> getTranslations() {
        return translations;
    }

    public static class Translation {
        private final String languageCode;
        private final String translation;

        public Translation(String languageCode, String translation) {
            this.languageCode = languageCode;
            this.translation = translation;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public String getTranslation() {
            return translation;
        }
    }
}
