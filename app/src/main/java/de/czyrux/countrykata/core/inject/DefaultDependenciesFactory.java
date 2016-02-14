package de.czyrux.countrykata.core.inject;

import de.czyrux.countrykata.core.data.image.PicassoImageLoader;
import de.czyrux.countrykata.core.data.rest.RestCountryRepositoryFactory;
import de.czyrux.countrykata.core.domain.country.CountryRepository;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;

public class DefaultDependenciesFactory implements DependenciesFactory{

    @Override
    public CountryService createCountryService() {
        CountryRepository countryRepository = RestCountryRepositoryFactory.create();
        return new CountryService(countryRepository);
    }

    @Override
    public ImageLoader createImageLoader() {
        return new PicassoImageLoader();
    }
}
