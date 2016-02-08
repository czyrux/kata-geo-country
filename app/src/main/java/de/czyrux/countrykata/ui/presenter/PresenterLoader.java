package de.czyrux.countrykata.ui.presenter;

import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

public final class PresenterLoader<T extends Presenter> extends Loader<T>{

    private final PresenterFactory<T> factory;
    private T presenter;

    public PresenterLoader(Context context, PresenterFactory<T> factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {
        Log.e("loader", "onStartLoading");

        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        Log.e("loader", "onForceLoad");
        presenter = factory.create();
        deliverResult(presenter);
    }

    @Override
    protected void onStopLoading() {
        Log.e("loader", "onStopLoading");
        // called on activity stop
    }

    @Override
    protected void onReset() {
        Log.e("loader", "onReset");
        presenter.onDestroyed();
        presenter = null;
    }
}
