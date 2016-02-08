package de.czyrux.countrykata.ui.presenter;

/**
 * Creates a Presenter object.
 * @param <T> presenter
 */
public interface PresenterFactory<T extends Presenter> {
    T create();
}
