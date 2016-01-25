package de.czyrux.countrykata.ui;

import java.util.List;

import javax.inject.Inject;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;

import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import de.czyrux.countrykata.CountryApp;
import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.Callback;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.di.ActivityComponent;
import de.czyrux.countrykata.di.ActivityModule;
import de.czyrux.countrykata.di.DaggerActivityComponent;

public class CountryListActivity extends AppCompatActivity {

    @Inject
    CountryService countryService;

    @Inject
    ImageLoader imageLoader;

    @Bind(R.id.country_list)
    RecyclerView countryListView;

    @Bind(R.id.country_list_progressbar)
    ProgressBar progressBar;

    @Bind(R.id.country_list_empty)
    TextView emptyTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityComponent component =
            DaggerActivityComponent.builder()                                                    //
                                   .appComponent(((CountryApp) getApplication()).getComponent()) //
                                   .activityModule(new ActivityModule(this))                     //
                                   .build();

        component.inject(this);

        setContentView(R.layout.activity_country_list);
        ButterKnife.bind(this);

        setupViews();
    }

    private void setupViews() {
        countryListView.setLayoutManager(new LinearLayoutManager(this));
        countryListView.setAdapter(new CountryAdapter(imageLoader));

        emptyTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        countryService.getAllCountries(new Callback<List<Country>>() {
                @Override
                public void onSuccess(final List<Country> response) {
                    progressBar.setVisibility(View.GONE);
                    ((CountryAdapter) countryListView.getAdapter()).addCountries(response);
                }

                @Override
                public void onFailure(final Throwable error) {
                    Toast.makeText(CountryListActivity.this, "oops!", Toast.LENGTH_SHORT).show();
                    Log.d("Tag", Log.getStackTraceString(error));
                }
            });
    }
}
