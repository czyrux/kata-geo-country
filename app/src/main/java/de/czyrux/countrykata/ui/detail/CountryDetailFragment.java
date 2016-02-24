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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.core.inject.Injector;
import de.czyrux.countrykata.ui.BaseFragment;
import de.czyrux.countrykata.ui.detail.model.CountryDetailTransformerFactory;
import de.czyrux.countrykata.ui.detail.model.CountryDetailViewModel;
import de.czyrux.countrykata.ui.detail.model.CountryLocationViewModel;
import de.czyrux.countrykata.ui.detail.model.CountryTranslationsViewModel;

public class CountryDetailFragment extends BaseFragment implements CountryDetailView {

    static final String ARG_CODE = "code";

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

        detailPresenter = new CountryDetailPresenter(countryService, countryCode, detailNavigator, CountryDetailTransformerFactory.create());
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
    public void populateCountry(CountryDetailViewModel country) {

        // Main block
        imageLoader.load(country.getFlagImageUrl(), imageView);

        nativeNameTextView.setText(country.getName());
        nameTextView.setText(country.getAlternativeName());
        capitalTextView.setText(country.getCapital());
        demonymTextView.setText(country.getDemonym());
        populationTextView.setText(country.getPopulation());
        areaTextView.setText(country.getArea());
        giniTextView.setText(country.getGini());

        populateLocation(country.getLocationViewModel());
        populateTranslations(country.getTranslationsViewModel());
        populateMisc(country);
    }

    private void populateMisc(CountryDetailViewModel country) {
        callingCodesTextView.setText(country.getCallingCodes());
        topLevelDomainTextView.setText(country.getTopLevelDomain());
        currencyTextView.setText(country.getCurrency());
        languagesTextView.setText(country.getLanguages());
        alphaCodeTextView.setText(country.getAlphaCodes());
    }

    private void populateTranslations(CountryTranslationsViewModel translationsViewModel) {
        translationsLayout.removeAllViews();

        for (CountryTranslationsViewModel.Translation translation :
                translationsViewModel.getTranslations()) {
            View itemView = LayoutInflater.from(translationsLayout.getContext()).inflate(R.layout.detail_translation_item, translationsLayout, false);
            ((TextView) ButterKnife.findById(itemView, R.id.country_detail_translation_item_language)).setText(translation.getLanguageCode());
            ((TextView) ButterKnife.findById(itemView, R.id.country_detail_translation_item_translation)).setText(translation.getTranslation());

            translationsLayout.addView(itemView);
        }
    }

    private void populateLocation(CountryLocationViewModel location) {
        locationTextView.setText(location.getLocationTitle());

        timezonesTextView.setText(location.getTimezones());
        latitudeTextView.setText(location.getLatitude());
        longitudeTextView.setText(location.getLongitude());
        neighboursTextView.setText(location.getNeighbours());
        exploreNeighboursTextView.setVisibility(location.showExploreNeighbours() ? View.VISIBLE : View.GONE);
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
