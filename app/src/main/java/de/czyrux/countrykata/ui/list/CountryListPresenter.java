package de.czyrux.countrykata.ui.list;

import java.util.List;

import de.czyrux.countrykata.core.domain.Callback;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.ui.TransformerUtil;
import de.czyrux.countrykata.ui.list.model.CountryItemTransformer;
import de.czyrux.countrykata.ui.list.model.CountryItemViewModel;

public class CountryListPresenter implements CountryListContract.Presenter {

    private final CountryService service;
    private final CountryItemTransformer transformer;
    private final CountryListNavigator navigator;

    private CountryListContract.View view;
    private List<Country> countryList;
    private boolean isLoading;

    public CountryListPresenter(CountryService service, CountryItemTransformer transformer, CountryListNavigator navigator) {
        this.service = service;
        this.transformer = transformer;
        this.navigator = navigator;
        isLoading = false;
    }

    @Override
    public void onViewAttached(final CountryListContract.View view) {
        this.view = view;
        if (countryList == null) {
            this.view.showProgressBar();
            if (!isLoading) {
                isLoading = true;
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
            }
        } else {
            handleSuccess(countryList);
        }
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroyed() {
        // Some clean ups
    }

    private void handleSuccess(List<Country> countryList) {
        this.isLoading = true;
        this.countryList = countryList;
        if (this.view == null) {
            return;
        }

        this.view.hideProgressBar();

        if (countryList == null || countryList.isEmpty()) {
            this.view.showEmptyText();
        } else {
            List<CountryItemViewModel> viewModelList = TransformerUtil.transform(countryList, transformer);
            this.view.showCountryList(viewModelList);
        }
    }

    private void handleError(Throwable error) {
        if (this.view == null) {
            return;
        }

        this.view.hideProgressBar();
        this.view.showError();
    }


    @Override
    public void onCountryClicked(CountryItemViewModel country, int position) {
        navigator.navigateToDetail(country.getAlphaCode());
    }
}
