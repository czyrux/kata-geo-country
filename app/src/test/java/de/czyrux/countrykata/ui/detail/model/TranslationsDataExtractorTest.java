package de.czyrux.countrykata.ui.detail.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import de.czyrux.countrykata.core.domain.country.Country;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TranslationsDataExtractorTest {

    private TranslationsDataExtractor extractor;

    @Before
    public void setUp() throws Exception {
        extractor = new TranslationsDataExtractor();
    }

    @Test(expected = NullPointerException.class)
    public void extractor_Should_ThrowNpe_When_CountryNull() {
        extractor.extract(null);
    }


    @Test
    public void extractor_Should_CreateViewModelWithoutTranslations_When_CountryHasNoTranslations() {
        Country country = new Country();
        country.setTranslations(new HashMap<String, String>());

        CountryTranslationsViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertTrue(viewModel.getTranslations().isEmpty());
    }

    @Test
    public void extractor_Should_CreateViewModelWithoutTranslations_When_CountryHasNullTranslations() {
        Country country = new Country();
        country.setTranslations(null);

        CountryTranslationsViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertTrue(viewModel.getTranslations().isEmpty());
    }

    @Test
    public void extractor_Should_CreateViewModelWithTranslations_When_CountryHasTranslations() {
        Map<String, String> translations = new HashMap<>();
        translations.put("DE", "Deutschland");
        translations.put("EN", "Germany");

        Country country = new Country();
        country.setTranslations(translations);

        CountryTranslationsViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertTrue(viewModel.getTranslations().size() == 2);
    }
}