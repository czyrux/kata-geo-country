package de.czyrux.countrykata.core.data.image;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.czyrux.countrykata.core.domain.image.ImageLoader;

public class PicassoImageLoader implements ImageLoader{
    @Override
    public void load(String uri, ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load(uri)
                .into(imageView);
    }
}
