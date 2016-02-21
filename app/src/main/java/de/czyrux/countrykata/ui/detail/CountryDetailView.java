package de.czyrux.countrykata.ui.detail;

import de.czyrux.countrykata.core.domain.country.Country;

public interface CountryDetailView {

    void populateCountry(Country country);

    void showErrorMessage(Throwable error);

    void showProgressBar();

    void hideProgressBar();
}
