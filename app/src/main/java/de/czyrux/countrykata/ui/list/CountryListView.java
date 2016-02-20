package de.czyrux.countrykata.ui.list;

import java.util.List;

import de.czyrux.countrykata.ui.list.model.CountryItemViewModel;

public interface CountryListView {

    void showProgressBar();

    void hideProgressBar();

    void showCountryList(List<CountryItemViewModel> countryList);

    void showEmptyText();

    void showError();
}
