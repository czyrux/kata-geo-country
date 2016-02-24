package de.czyrux.countrykata.ui.list;

import java.util.List;

import de.czyrux.countrykata.ui.list.model.CountryItemViewModel;

public interface CountryListContract {

    interface View {

        void showProgressBar();

        void hideProgressBar();

        void showCountryList(List<CountryItemViewModel> countryList);

        void showEmptyText();

        void showError();
    }

    interface Presenter extends de.czyrux.countrykata.ui.presenter.Presenter<View>, CountryListListener {

        @Override
        void onViewAttached(View view);

        @Override
        void onViewDetached();

        @Override
        void onDestroyed();

        @Override
        void onCountryClicked(CountryItemViewModel country, int position);
    }
}
