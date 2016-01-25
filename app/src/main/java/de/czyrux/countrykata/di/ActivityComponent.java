package de.czyrux.countrykata.di;

import dagger.Component;

import de.czyrux.countrykata.ui.CountryListActivity;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(CountryListActivity activity);

}
