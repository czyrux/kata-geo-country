package de.czyrux.countrykata.ui.detail;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryImageBuilder;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.core.inject.Injector;
import de.czyrux.countrykata.ui.BaseFragment;

public class CountryDetailFragment extends BaseFragment implements CountryDetailView {

    static final String ARG_CODE = "code";

    private final static NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.getDefault());

    @Bind(R.id.country_detail_progressbar)
    ProgressBar progressBar;

    // Main block
    @Bind(R.id.country_detail_flag)
    ImageView imageView;
    @Bind(R.id.country_detail_nativename)
    TextView nativeNameTextView;

    @Bind(R.id.country_detail_name)
    TextView nameTextView;
    @Bind(R.id.country_detail_capital)
    TextView capitalTextView;

    @Bind(R.id.country_detail_demonym)
    TextView demonymTextView;

    @Bind(R.id.country_detail_population)
    TextView populationTextView;

    @Bind(R.id.country_detail_area)
    TextView areaTextView;

    @Bind(R.id.country_detail_gini)
    TextView giniTextView;

    // Location block
    @Bind(R.id.country_detail_section_location)
    TextView locationTextView;
    @Bind(R.id.country_detail_timezones)
    TextView timezonesTextView;
    @Bind(R.id.country_detail_latitude)
    TextView latitudeTextView;
    @Bind(R.id.country_detail_longitude)
    TextView longitudeTextView;

    @Bind(R.id.country_detail_neighbour_countries)
    TextView neighboursTextView;
    @Bind(R.id.country_detail_explore_neighbours)
    View exploreNeighboursTextView;

    // Translations block
    @Bind(R.id.country_detail_translations)
    LinearLayout translationsLayout;

    // Misc block
    @Bind(R.id.country_detail_callingcodes)
    TextView callingCodesTextView;
    @Bind(R.id.country_detail_topleveldomain)
    TextView topLevelDomainTextView;
    @Bind(R.id.country_detail_currency)
    TextView currencyTextView;
    @Bind(R.id.country_detail_languages)
    TextView languagesTextView;
    @Bind(R.id.country_detail_alphacode)
    TextView alphaCodeTextView;

    private CountryDetailNavigator detailNavigator;
    private ImageLoader imageLoader;
    private CountryDetailPresenter detailPresenter;

    public static CountryDetailFragment newInstance(String countryCode) {
        CountryDetailFragment fragment = new CountryDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CODE, countryCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            detailNavigator = (CountryDetailNavigator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement CountryDetailListener");
        }
    }

    @Override
    public void onDetach() {
        detailNavigator = null;
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        CountryService countryService = Injector.countryService();
        imageLoader = Injector.imageLoader();
        String countryCode = getArguments().getString(ARG_CODE);

        detailPresenter = new CountryDetailPresenter(countryService, countryCode, detailNavigator);
    }

    @Override
    protected int getViewLayoutResourceId() {
        return R.layout.detail_fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        detailPresenter.onViewAttached(this);
    }

    @Override
    public void onStop() {
        detailPresenter.onViewDetached();
        super.onStop();
    }

    @OnClick(R.id.country_detail_explore_region)
    public void onExploreRegion() {
        detailPresenter.onExploreCountryRegion();

    }

    @OnClick(R.id.country_detail_explore_neighbours)
    public void onExploreNeighbours() {
        detailPresenter.onExploreCountryNeighbours();
    }

    @Override
    public void populateCountry(Country country) {

        // Main block
        imageLoader.load(CountryImageBuilder.obtainImageUrl(country), imageView);

        nativeNameTextView.setText(country.getNativeName());

        StringBuilder altNameBuilder = new StringBuilder();
        altNameBuilder.append(country.getName());

        for (String name : country.getAlternativeSpellings()) {
            altNameBuilder.append(", ").append(name);
        }

        nameTextView.setText(altNameBuilder.toString());

        capitalTextView.setText(country.getCapital());
        demonymTextView.setText(country.getDemonym());
        populationTextView.setText(NUMBER_FORMAT.format(country.getPopulation()));
        areaTextView.setText(NUMBER_FORMAT.format(country.getArea()));
        giniTextView.setText(NUMBER_FORMAT.format(country.getGini()));

        // Location block
        populateLocation(country);

        // Translations block
        populateTranslations(country);


        // Misc block
        populateMisc(country);

    }

    private void populateMisc(Country country) {
        callingCodesTextView.setText(Arrays.toString(country.getCallingCodes()));
        topLevelDomainTextView.setText(Arrays.toString(country.getTopLevelDomain()));
        currencyTextView.setText(Arrays.toString(country.getCurrencies()));
        languagesTextView.setText(Arrays.toString(country.getLanguages()));
        alphaCodeTextView.setText(country.getAlpha2Code() + ", " + country.getAlpha3Code());
    }

    private void populateTranslations(Country country) {
        translationsLayout.removeAllViews();

        if (country.getTranslations() != null) {
            for (Map.Entry<String, String> translationEntry : country.getTranslations().entrySet()) {
                View itemView = LayoutInflater.from(translationsLayout.getContext()).inflate(R.layout.detail_translation_item, translationsLayout, false);
                ((TextView) ButterKnife.findById(itemView, R.id.country_detail_translation_item_language)).setText(translationEntry.getKey());
                ((TextView) ButterKnife.findById(itemView, R.id.country_detail_translation_item_translation)).setText(translationEntry.getValue());

                translationsLayout.addView(itemView);
            }
        }
    }

    private void populateLocation(Country country) {
        locationTextView.setText(country.getRegion() + ", " + country.getSubregion());
        timezonesTextView.setText(Arrays.toString(country.getTimezones()));


        if (country.getLatlong() != null && country.getLatlong().length == 2) {
            latitudeTextView.setText(String.valueOf(country.getLatlong()[0]));
            longitudeTextView.setText(String.valueOf(country.getLatlong()[1]));
        }

        if (country.getBorders() != null && country.getBorders().length > 0) {

            StringBuilder neighboursCountries = new StringBuilder();
            for (String countryCode : country.getBorders()) {
                if (neighboursCountries.length() != 0) {
                    neighboursCountries.append(", ");
                }

                neighboursCountries.append(countryCode);
            }

            neighboursTextView.setText(neighboursCountries.toString());
        } else {
            neighboursTextView.setText(R.string.detail_no_neighbours);
            exploreNeighboursTextView.setVisibility(View.GONE);
        }
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
    public void showErrorMessage(Throwable error) {
        Toast.makeText(getActivity(), "Ups!", Toast.LENGTH_SHORT).show();
        Log.d("Tag", Log.getStackTraceString(error));
    }
}
