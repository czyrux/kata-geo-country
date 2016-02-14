package de.czyrux.countrykata.ui.list;

import java.util.List;

import de.czyrux.countrykata.core.domain.country.Country;

public interface CountryListView {

    void showProgressBar();

    void hideProgressBar();

    void showCountryList(List<Country> countryList);

    void showEmptyText();

    void showError();
}
