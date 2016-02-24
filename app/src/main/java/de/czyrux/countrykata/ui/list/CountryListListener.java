package de.czyrux.countrykata.ui.list;

import de.czyrux.countrykata.ui.list.model.CountryItemViewModel;

public interface CountryListListener {
    void onCountryClicked(CountryItemViewModel country, int position);
}
