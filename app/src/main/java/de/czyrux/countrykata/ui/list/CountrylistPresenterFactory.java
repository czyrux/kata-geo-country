package de.czyrux.countrykata.ui.list;

import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.ui.list.model.CountryTransformer;
import de.czyrux.countrykata.ui.presenter.PresenterFactory;

public class CountrylistPresenterFactory implements PresenterFactory<CountryListPresenter> {

    private final CountryListNavigator navigator;
    private final CountryService countryService;

    public CountrylistPresenterFactory(CountryListNavigator navigator, CountryService countryService) {
        this.navigator = navigator;
        this.countryService = countryService;
    }

    @Override
    public CountryListPresenter create() {
        return new CountryListPresenter(countryService, new CountryTransformer(), navigator);
    }
}
