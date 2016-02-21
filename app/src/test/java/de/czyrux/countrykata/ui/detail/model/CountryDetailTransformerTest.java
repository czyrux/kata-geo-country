package de.czyrux.countrykata.ui.detail.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.czyrux.countrykata.core.domain.country.Country;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountryDetailTransformerTest {

    @Mock
    LocationDataExtractor locationDataExtractor;

    @Mock
    TranslationsDataExtractor translationsDataExtractor;

    private CountryDetailTransformer transformer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        transformer = new CountryDetailTransformer(locationDataExtractor, translationsDataExtractor);
    }

    @Test(expected = NullPointerException.class)
    public void transform_Should_ThrowNpe_When_NullCountry() {
        transformer.transform(null);
    }

    @Test
    public void transform_Should_Transform_When_CountryIsOk() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setAlpha3Code("cc2");
        country.setNativeName("aNativeName");
        country.setAlternativeSpellings(new String[]{"anotherName"});
        country.setCapital("aCapital");
        country.setRegion("aRegion");
        country.setSubregion("aSubregion");
        country.setDemonym("aDemonym");
        country.setPopulation(1000L);
        country.setArea(25.4f);
        country.setGini(1000.25f);
        country.setCallingCodes(new String[]{"+34", "+42"});
        country.setTopLevelDomain(new String[]{"es", "co.uk"});
        country.setCurrencies(new String[]{"E", "TK"});
        country.setLanguages(new String[]{"DE", "EN"});

        CountryLocationViewModel fakeLocationViewModel = new CountryLocationViewModel();
        when(locationDataExtractor.extract(any(Country.class))).thenReturn(fakeLocationViewModel);

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertNotNull(viewModel.getFlagImageUrl());
        assertEquals(viewModel.getName(), country.getNativeName());
        assertEquals(viewModel.getAlternativeName(), "aName, anotherName");
        assertEquals(viewModel.getCapital(), country.getCapital());
        assertEquals(viewModel.getDemonym(), country.getDemonym());
        assertEquals(viewModel.getPopulation(), "1,000");
        assertEquals(viewModel.getArea(), "25.4");
        assertEquals(viewModel.getGini(), "1,000.25");
        assertEquals(viewModel.getCallingCodes(), "[+34, +42]");
        assertEquals(viewModel.getTopLevelDomain(), "[es, co.uk]");
        assertEquals(viewModel.getCurrency(), "[E, TK]");
        assertEquals(viewModel.getLanguages(), "[DE, EN]");
        assertEquals(viewModel.getAlphaCodes(), "cc, cc2");

        verify(locationDataExtractor).extract(country);
        assertEquals(viewModel.getLocationViewModel(), fakeLocationViewModel);
        verify(translationsDataExtractor).extract(country);
    }

    @Test
    public void transform_Should_SetOnlyName_When_NoAlternativeNames() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setAlternativeSpellings(null);

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getAlternativeName(), "aName");
    }

    @Test
    public void transform_Should_UseAlternativeName_When_AlternativeNamesAvailables() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setAlternativeSpellings(new String[]{"anotherName"});

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getAlternativeName(), "aName, anotherName");
    }


    @Test
    public void transform_Should_SetEmtpy_When_CountryHasNoCallingCodes() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setCallingCodes(new String[]{});

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getCallingCodes(), CountryDetailTransformer.ARRAY_NO_CONTENT);
    }

    @Test
    public void transform_Should_SetNothing_When_CountryHasNullCallingCodes() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setCallingCodes(null);

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getCallingCodes(), CountryDetailTransformer.ARRAY_NO_CONTENT);
    }

    @Test
    public void transform_Should_SetEmtpy_When_CountryHasNoTopDomain() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setTopLevelDomain(new String[]{});

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getTopLevelDomain(), CountryDetailTransformer.ARRAY_NO_CONTENT);
    }

    @Test
    public void transform_Should_SetNothing_When_CountryHasNullTopDomains() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setTopLevelDomain(null);

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getTopLevelDomain(), CountryDetailTransformer.ARRAY_NO_CONTENT);
    }

    @Test
    public void transform_Should_SetEmtpy_When_CountryHasNoCurrencies() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setCurrencies(new String[]{});

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getCurrency(), CountryDetailTransformer.ARRAY_NO_CONTENT);
    }

    @Test
    public void transform_Should_SetNothing_When_CountryHasNullCurrencies() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setCurrencies(null);

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getCurrency(), CountryDetailTransformer.ARRAY_NO_CONTENT);
    }

    @Test
    public void transform_Should_SetEmtpy_When_CountryHasNoLanguages() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setLanguages(new String[]{});

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getLanguages(), CountryDetailTransformer.ARRAY_NO_CONTENT);
    }

    @Test
    public void transform_Should_SetNothing_When_CountryHasNullLanguages() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setLanguages(null);

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getLanguages(), CountryDetailTransformer.ARRAY_NO_CONTENT);
    }

    @Test
    public void transform_Should_SetOnlyAlpha2Code_When_CountryHasNoAlpha3Code() {

        Country country = new Country();
        country.setName("aName");
        country.setAlpha2Code("cc");
        country.setAlpha3Code(null);

        CountryDetailViewModel viewModel = transformer.transform(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getAlphaCodes(), "cc");
    }

}
