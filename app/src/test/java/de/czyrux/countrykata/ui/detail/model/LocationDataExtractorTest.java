package de.czyrux.countrykata.ui.detail.model;

import org.junit.Before;
import org.junit.Test;

import de.czyrux.countrykata.core.domain.country.Country;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class LocationDataExtractorTest {

    private LocationDataExtractor extractor;

    @Before
    public void setUp() throws Exception {
        extractor = new LocationDataExtractor();
    }

    @Test(expected = NullPointerException.class)
    public void extract_Should_ThrowNpe_When_CountryIsNull() {
        extractor.extract(null);
    }

    @Test
    public void extract_Should_CreateProperLocationTitle() {
        Country country = new Country();
        country.setRegion("aRegion");
        country.setSubregion("aSubRegion");

        CountryLocationViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getLocationTitle(), "aRegion, aSubRegion");
    }

    @Test
    public void extract_Should_CreateProperLatitudeAndLongitude() {
        Country country = new Country();
        country.setLatlong(new float[]{12.3f, 1.f});

        CountryLocationViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getLatitude(), "12.3");
        assertEquals(viewModel.getLongitude(), "1.0");
    }

    @Test
    public void extract_Should_NotCrash_When_NoLatLong() {
        Country country = new Country();
        country.setLatlong(new float[]{});

        CountryLocationViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getLatitude(), LocationDataExtractor.UNKNOWN);
        assertEquals(viewModel.getLongitude(), LocationDataExtractor.UNKNOWN);
    }

    @Test
    public void extract_Should_NotCrash_When_LatLongNull() {
        Country country = new Country();
        country.setLatlong(null);

        CountryLocationViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertEquals(viewModel.getLatitude(), LocationDataExtractor.UNKNOWN);
        assertEquals(viewModel.getLongitude(), LocationDataExtractor.UNKNOWN);
    }

    @Test
    public void extract_Should_BuildNeighbourList_When_CountryHasNeighbours() {
        Country country = new Country();
        country.setBorders(new String[]{"aCountry", "anotherCountry"});

        CountryLocationViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertTrue(viewModel.showExploreNeighbours());
        assertEquals(viewModel.getNeighbours(), "aCountry, anotherCountry");
    }

    @Test
    public void extract_Should_HideNeighbourAndSetTitle_When_CountryHasNoNeighbours() {
        Country country = new Country();
        country.setBorders(new String[]{});

        CountryLocationViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertFalse(viewModel.showExploreNeighbours());
        assertEquals(viewModel.getNeighbours(), LocationDataExtractor.NO_NEIGHBOURS);
    }

    @Test
    public void extract_Should_HideNeighbourAndSetTitle_When_CountryNeighboursIsNull() {
        Country country = new Country();
        country.setBorders(null);

        CountryLocationViewModel viewModel = extractor.extract(country);

        assertNotNull(viewModel);
        assertFalse(viewModel.showExploreNeighbours());
        assertEquals(viewModel.getNeighbours(), LocationDataExtractor.NO_NEIGHBOURS);
    }

}