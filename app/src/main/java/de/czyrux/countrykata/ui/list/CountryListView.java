package de.czyrux.countrykata.ui.list;

import java.util.List;

import de.czyrux.countrykata.ui.list.model.CountryUIModel;

public interface CountryListView {

    void showProgressBar();

    void hideProgressBar();

    void showCountryList(List<CountryUIModel> countryList);

    void showEmptyText();

    void showError();
}
