package de.czyrux.countrykata.ui.list;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
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
import de.czyrux.countrykata.ui.list.model.CountryUIModel;
import de.czyrux.countrykata.ui.presenter.PresenterLoader;

public class CountryListActivity extends AppCompatActivity implements CountryListNavigator, CountryListView,
        LoaderManager.LoaderCallbacks<CountryListPresenter> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.country_list)
    RecyclerView countryListView;

    @Bind(R.id.country_list_progressbar)
    ProgressBar progressBar;

    @Bind(R.id.country_list_empty)
    TextView emptyTextView;

    private ImageLoader imageLoader;
    private CountryListPresenter presenter;
    private CountryService countryService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d("activity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_country_activity);
        ButterKnife.bind(this);

        imageLoader = Injector.imageLoader();
        countryService = Injector.countryService();
        setupViews();
        getSupportLoaderManager().initLoader(0, null, this);
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
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showCountryList(final List<CountryUIModel> countryList) {
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
        Toast.makeText(CountryListActivity.this, "Ups!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDetail(String alphaCode) {
        CountryDetailActivity.launch(this, alphaCode);
    }

    @Override
    public Loader<CountryListPresenter> onCreateLoader(int id, Bundle args) {
        Log.d("activity", "onCreateLoader");
        return new PresenterLoader<>(this, new CountrylistPresenterFactory(this, countryService));
    }

    @Override
    public void onLoadFinished(Loader<CountryListPresenter> loader, CountryListPresenter presenter) {
        Log.d("activity", "onLoadFinished");
        this.presenter = presenter;
    }

    @Override
    public void onLoaderReset(Loader<CountryListPresenter> loader) {
        Log.d("activity", "onLoaderReset");
        presenter = null;
    }
}
