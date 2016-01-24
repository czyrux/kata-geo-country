package de.czyrux.countrykata.core.domain.image;

import android.widget.ImageView;

public interface ImageLoader {

    void load(String uri, ImageView imageView);
}
