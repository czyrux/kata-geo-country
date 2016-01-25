package de.czyrux.countrykata.di;

import dagger.Module;
import dagger.Provides;

import de.czyrux.countrykata.core.data.image.PicassoImageLoader;
import de.czyrux.countrykata.core.data.rest.RestCountryRepositoryFactory;
import de.czyrux.countrykata.core.domain.country.CountryRepository;
import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;

@AppScope
@Module
public class AppModule {

    @AppScope
    @Provides
    public CountryService provideCountryService() {
        CountryRepository countryRepository = RestCountryRepositoryFactory.create();
        return new CountryService(countryRepository);
    }

    @AppScope
    @Provides
    public ImageLoader provideImageLoader() {
        return new PicassoImageLoader();
    }
}
