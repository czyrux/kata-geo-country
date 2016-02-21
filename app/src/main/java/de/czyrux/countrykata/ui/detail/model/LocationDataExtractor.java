package de.czyrux.countrykata.ui.detail.model;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import de.czyrux.countrykata.core.domain.country.Country;

public class LocationDataExtractor {

    private static final String SEPARATOR = ", ";

    @VisibleForTesting
    static final String UNKNOWN = "Unknown";

    @VisibleForTesting
    static final String NO_NEIGHBOURS = "None";

    public CountryLocationViewModel extract(@NonNull Country country) {

        CountryLocationViewModel viewModel = new CountryLocationViewModel();

        viewModel.locationTitle = country.getRegion() + SEPARATOR + country.getSubregion();

        if (country.getLatlong() != null && country.getLatlong().length == 2) {
            viewModel.latitude = String.valueOf(country.getLatlong()[0]);
            viewModel.longitude = String.valueOf(country.getLatlong()[1]);
        } else {
            viewModel.latitude = UNKNOWN;
            viewModel.longitude = UNKNOWN;
        }

        if (country.getBorders() != null && country.getBorders().length > 0) {

            StringBuilder neighboursCountries = new StringBuilder();
            for (String countryCode : country.getBorders()) {
                if (neighboursCountries.length() != 0) {
                    neighboursCountries.append(SEPARATOR);
                }

                neighboursCountries.append(countryCode);
            }

            viewModel.neighbours = neighboursCountries.toString();
            viewModel.showExploreNeighbours = true;
        } else {
            viewModel.neighbours = NO_NEIGHBOURS; // TODO (R.string.detail_no_neighbours
            viewModel.showExploreNeighbours = false;
        }

        return viewModel;
    }
}
