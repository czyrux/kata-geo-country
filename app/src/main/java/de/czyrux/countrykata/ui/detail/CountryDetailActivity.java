package de.czyrux.countrykata.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.Country;

public class CountryDetailActivity extends AppCompatActivity implements CountryDetailListener {

    public static void launch(Context context, String countryCode) {
        Intent intent = new Intent(context, CountryDetailActivity.class);
        intent.putExtra(CountryDetailFragment.ARG_CODE, countryCode);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_country_activity);

        if (savedInstanceState == null) {
            String countryCode = getIntent().getStringExtra(CountryDetailFragment.ARG_CODE);
            if (countryCode == null) {
                finish();
                return;
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, CountryDetailFragment.newInstance(countryCode))
                    .commit();
        }
    }

    @Override
    public void onExploreCountryRegion(Country country) {
        // TODO
    }

    @Override
    public void onExploreCountryNeighbours(Country country) {
        // TODO
    }
}
