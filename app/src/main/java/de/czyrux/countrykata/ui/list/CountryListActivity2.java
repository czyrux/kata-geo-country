package de.czyrux.countrykata.ui.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.core.inject.Injector;
import de.czyrux.countrykata.ui.detail.CountryDetailActivity;
import de.czyrux.countrykata.ui.list.model.CountryItemViewModel;
import de.czyrux.countrykata.ui.presenter.BasePresenterActivity;
import de.czyrux.countrykata.ui.presenter.PresenterFactory;

public class CountryListActivity2 extends BasePresenterActivity<CountryListContract.Presenter> implements CountryListNavigator, CountryListContract.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.country_list)
    RecyclerView countryListView;

    @Bind(R.id.country_list_progressbar)
    ProgressBar progressBar;

    @Bind(R.id.country_list_empty)
    TextView emptyTextView;

    private ImageLoader imageLoader;
    private CountryListContract.Presenter presenter;
    private CountryService countryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activity", "onCreate");

        // super will need this things before
        imageLoader = Injector.imageLoader();
        countryService = Injector.countryService();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_country_activity);
        ButterKnife.bind(this);
        setupViews();
    }

    @Override
    protected void onStart() {
        Log.d("activity", "onStart");
        super.onStart();
        presenter.onViewAttached(this);
    }

    @Override
    protected void onResume() {
        Log.d("activity", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("activity", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("activity", "onStop");
        presenter.onViewDetached();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("activity", "onDestroy");
        super.onDestroy();
    }

    private void setupViews() {
        setSupportActionBar(toolbar);
        countryListView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected PresenterFactory<CountryListContract.Presenter> getPresenterFactory() {
        return new CountrylistPresenterFactory(this, countryService);
    }

    @Override
    protected void onPresenterCreated(CountryListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showCountryList(List<CountryItemViewModel> countryList) {
        CountryAdapter adapter = (CountryAdapter) countryListView.getAdapter();
        if (adapter == null) {
            adapter = new CountryAdapter(imageLoader, presenter);
            countryListView.setAdapter(adapter);
        }

        adapter.setCountries(countryList);
    }

    @Override
    public void showEmptyText() {
        emptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        Toast.makeText(CountryListActivity2.this, "Ups!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDetail(String alphaCode) {
        CountryDetailActivity.launch(this, alphaCode);
    }
}
