package de.czyrux.countrykata.core.data.image;

import android.util.Log;
import android.widget.ImageView;

import de.czyrux.countrykata.core.domain.image.ImageLoader;

public class LogNoOpImageLoader implements ImageLoader {
    @Override
    public void load(String uri, ImageView imageView) {
        Log.d("ImageLoader", uri);
    }

}
