package de.czyrux.countrykata.core.domain;

public interface Callback<T> {
    void onSuccess(T response);

    void onFailure(Throwable error);
}
