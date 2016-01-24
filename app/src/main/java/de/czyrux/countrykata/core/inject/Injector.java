package de.czyrux.countrykata.core.inject;

import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;

/**
 * Class use as a centralize point for DI.
 */
public class Injector {
    private static Injector INSTANCE;
    private final CountryService countryService;
    private final ImageLoader imageLoader;

    public Injector(CountryService countryService, ImageLoader imageLoader) {
        this.countryService = countryService;
        this.imageLoader = imageLoader;
    }

    public static void using(DependenciesFactory factory) {
        CountryService countryService = factory.createCountryService();
        ImageLoader imageLoader = factory.createImageLoader();
        INSTANCE = new Injector(countryService, imageLoader);
    }

    private static Injector instance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("You need to setup Inject to use a valid DependenciesFactory");
        }
        return INSTANCE;
    }

    public static CountryService countryService() { return instance().countryService; }

    public static ImageLoader imageLoader() { return instance().imageLoader; }

}
