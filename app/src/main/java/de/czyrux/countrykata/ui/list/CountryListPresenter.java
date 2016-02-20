package de.czyrux.countrykata.ui.list;

import java.util.List;

import de.czyrux.countrykata.core.domain.Callback;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.ui.Presenter;
import de.czyrux.countrykata.ui.TransformerUtil;
import de.czyrux.countrykata.ui.list.model.CountryTransformer;
import de.czyrux.countrykata.ui.list.model.CountryItemViewModel;

public class CountryListPresenter implements Presenter<CountryListView>, CountryListListener{

    private final CountryService service;
    private final CountryTransformer transformer;
    private final CountryListNavigator navigator;

    private CountryListView view;
    private List<Country> countryList;

    public CountryListPresenter(CountryService service, CountryTransformer transformer, CountryListNavigator navigator) {
        this.service = service;
        this.transformer = transformer;
        this.navigator = navigator;
    }

    @Override
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

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroyed() { }

    private void handleSuccess(List<Country> countryList) {
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
