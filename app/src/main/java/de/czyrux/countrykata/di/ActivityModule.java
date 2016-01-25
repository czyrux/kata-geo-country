package de.czyrux.countrykata.di;

import android.app.Activity;

import dagger.Module;

@ActivityScope
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(final Activity activity) {
        this.activity = activity;
    }
}
