package de.czyrux.countrykata.di;

import dagger.Component;

import de.czyrux.countrykata.core.domain.country.CountryService;
import de.czyrux.countrykata.core.domain.image.ImageLoader;

@Component(modules = AppModule.class)
public interface AppComponent {

    CountryService countryService();

    ImageLoader imageLoader();
}
