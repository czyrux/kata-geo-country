package de.czyrux.countrykata.ui.detail;

import de.czyrux.countrykata.core.domain.country.Country;

public interface CountryDetailListener {

    void onExploreCountryRegion(Country country);

    void onExploreCountryNeighbours(Country country);
}
