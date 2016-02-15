package de.czyrux.countrykata.ui.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import de.czyrux.countrykata.ui.list.model.CountryTransformer;
import de.czyrux.countrykata.ui.list.model.CountryUIModel;

public class CountryListActivity extends AppCompatActivity implements CountryListListener, CountryListView {

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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_country_activity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        imageLoader = Injector.imageLoader();
        setupViews();

        CountryService countryService = Injector.countryService();
        presenter = new CountryListPresenter(countryService, new CountryTransformer());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewAttached(this);
    }

    @Override
    protected void onPause() {
        presenter.onViewDetached();
        super.onPause();
    }

    private void setupViews() {
        countryListView.setLayoutManager(new LinearLayoutManager(this));
        countryListView.setAdapter(new CountryAdapter(imageLoader, this));
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
        ((CountryAdapter) countryListView.getAdapter()).setCountries(countryList);
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
    public void onCountryClicked(final CountryUIModel country, final int position) {
        CountryDetailActivity.launch(this, country.getAlphaCode());
    }
}
