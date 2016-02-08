package de.czyrux.countrykata.ui.presenter;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BasePresenterActivity<P extends Presenter> extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<P> {

    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        Log.e("base-loader", "onCreateLoader");
        return new PresenterLoader<>(this, getPresenterFactory());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P data) {
        Log.e("base-loader", "onLoadFinished");
        onPresenterCreated(data);
    }

    @Override
    public final void onLoaderReset(Loader<P> loader) {
        Log.e("base-loader", "onLoaderReset");
    }

    protected abstract PresenterFactory<P> getPresenterFactory();

    protected abstract void onPresenterCreated(P presenter);
}
