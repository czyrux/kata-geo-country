package de.czyrux.countrykata;

import android.app.Application;

import de.czyrux.countrykata.core.inject.DefaultDependenciesFactory;
import de.czyrux.countrykata.core.inject.Injector;

public class CountryApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.using(new DefaultDependenciesFactory());
    }
}
