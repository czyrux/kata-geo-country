package de.czyrux.countrykata.ui.detail;

import de.czyrux.countrykata.ui.detail.model.CountryDetailViewModel;

public interface CountryDetailView {

    void populateCountry(CountryDetailViewModel country);

    void showErrorMessage(Throwable error);

    void showProgressBar();

    void hideProgressBar();
}
