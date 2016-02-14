package de.czyrux.countrykata.ui;

public interface Presenter <V>{
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
}
