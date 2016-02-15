package de.czyrux.countrykata.ui;

import android.support.annotation.NonNull;

public interface Transformer<M,T> {

    T transform(@NonNull M model);
}
