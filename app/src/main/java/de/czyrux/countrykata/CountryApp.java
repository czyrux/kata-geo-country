package de.czyrux.countrykata;

import android.app.Application;

import de.czyrux.countrykata.di.AppComponent;
import de.czyrux.countrykata.di.AppModule;
import de.czyrux.countrykata.di.DaggerAppComponent;

public class CountryApp extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
