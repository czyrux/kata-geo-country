package de.czyrux.countrykata.ui.presenter;

public interface Presenter <V>{
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
}
