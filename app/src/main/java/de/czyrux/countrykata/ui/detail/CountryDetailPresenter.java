package de.czyrux.countrykata.ui.detail;

import de.czyrux.countrykata.core.domain.Callback;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.ui.Presenter;
import de.czyrux.countrykata.ui.detail.model.CountryDetailTransformer;

public class CountryDetailPresenter implements Presenter<CountryDetailView> {

    private final CountryService service;
    private final String countryCode;
    private final CountryDetailNavigator navigator;
    private final CountryDetailTransformer transformer;

    private CountryDetailView view;
    private Country country;

    public CountryDetailPresenter(CountryService service, String countryCode, CountryDetailNavigator navigator, CountryDetailTransformer transformer) {
        this.service = service;
        this.countryCode = countryCode;
        this.navigator = navigator;
        this.transformer = transformer;
    }

    @Override
    public void onViewAttached(CountryDetailView view) {
        this.view = view;

        if (country == null) {
            view.showProgressBar();
            service.getCountryByCode(countryCode, new Callback<Country>() {
                @Override
                public void onSuccess(Country country) {
                   handleSuccess(country);
                }

                @Override
                public void onFailure(Throwable error) {
                    handleError(error);
                }
            });
        } else {
            handleSuccess(country);
        }
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroyed() {

    }

    private void handleSuccess(Country country) {
        this.country = country;

        if (this.view == null) {
            return;
        }

        this.view.hideProgressBar();
        this.view.populateCountry(transformer.transform(country));
    }

    private void handleError(Throwable error) {
        CountryDetailPresenter.this.view.showErrorMessage(error);
    }


    public void onExploreCountryRegion() {
        navigator.onExploreCountryRegion(country);
    }

    public void onExploreCountryNeighbours() {
        navigator.onExploreCountryNeighbours(country);
    }
}
