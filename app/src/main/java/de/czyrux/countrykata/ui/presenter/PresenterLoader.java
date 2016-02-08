package de.czyrux.countrykata.ui.presenter;

import android.content.Context;
import android.support.v4.content.Loader;

public final class PresenterLoader<T extends Presenter> extends Loader<T>{

    private final PresenterFactory<T> factory;
    private T presenter;

    public PresenterLoader(Context context, PresenterFactory<T> factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {

        // if we already own an instance of our presenter, simply deliver it.
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        // Create the Presenter using the Factory
        presenter = factory.create();

        // Deliver the result
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        presenter.onDestroyed();
        presenter = null;
    }
}
