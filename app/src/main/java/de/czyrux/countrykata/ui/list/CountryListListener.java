package de.czyrux.countrykata.ui.list;

import de.czyrux.countrykata.ui.list.model.CountryUIModel;

public interface CountryListListener {
    void onCountryClicked(CountryUIModel country, int position);
}
