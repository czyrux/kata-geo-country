package de.czyrux.countrykata.core.inject;

import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;

public interface DependenciesFactory {
    CountryService createCountryService();

    ImageLoader createImageLoader();
}
