package de.czyrux.countrykata.ui.list;

import java.util.ArrayList;
import java.util.List;

import de.czyrux.countrykata.core.domain.Callback;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryService;

public class CountryListPresenter {

    private final CountryService service;
    private CountryListView view;
    private List<Country> countryList;

    public CountryListPresenter(CountryService service) {
        this.service = service;
    }

    public void onViewAttached(final CountryListView view) {
        this.view = view;
        if (countryList == null) {
            this.view.showProgressBar();
            service.getAllCountries(new Callback<List<Country>>() {
                @Override
                public void onSuccess(List<Country> countries) {
                    handleSuccess(countries);
                }

                @Override
                public void onFailure(Throwable error) {
                    handleError(error);
                }
            });
        } else {
            handleSuccess(countryList);
        }
    }

    public void onViewDetached() {
        this.view = null;
    }

    private void handleError(Throwable error) {
        if (this.view == null) {
            return;
        }

        this.view.hideProgressBar();
        this.view.showError();
    }

    private void handleSuccess(List<Country> countryList) {
        this.countryList = countryList;
        if (this.view == null) {
            return;
        }

        this.view.hideProgressBar();

        if (countryList == null || countryList.isEmpty()) {
            this.view.showEmptyText();
        } else {
            this.view.showCountryList(new ArrayList<>(countryList.subList(0, 5)));
        }
    }
}
