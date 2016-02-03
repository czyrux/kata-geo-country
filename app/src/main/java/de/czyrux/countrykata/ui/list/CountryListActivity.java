package de.czyrux.countrykata.ui.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.Callback;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.core.inject.Injector;
import de.czyrux.countrykata.ui.detail.CountryDetailActivity;

public class CountryListActivity extends AppCompatActivity implements CountryListListener {

    @Bind(R.id.country_list)
    RecyclerView countryListView;

    @Bind(R.id.country_list_progressbar)
    ProgressBar progressBar;

    @Bind(R.id.country_list_empty)
    TextView emptyTextView;

    private CountryService countryService;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_country_activity);
        ButterKnife.bind(this);

        countryService = Injector.countryService();
        imageLoader = Injector.imageLoader();

        setupViews();
    }

    private void setupViews() {
        countryListView.setLayoutManager(new LinearLayoutManager(this));
        countryListView.setAdapter(new CountryAdapter(imageLoader, this));

        emptyTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        countryService.getAllCountries(new Callback<List<Country>>() {
            @Override
            public void onSuccess(List<Country> response) {
                progressBar.setVisibility(View.GONE);
                ((CountryAdapter) countryListView.getAdapter()).addCountries(response);
            }

            @Override
            public void onFailure(Throwable error) {
                Toast.makeText(CountryListActivity.this, "Ups!", Toast.LENGTH_SHORT).show();
                Log.d("Tag", Log.getStackTraceString(error));
            }
        });
    }

    @Override
    public void onCountryClicked(Country country, int position) {
        CountryDetailActivity.launch(this, country.getAlpha2Code());
    }
}
