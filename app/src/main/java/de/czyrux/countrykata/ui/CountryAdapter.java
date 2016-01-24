package de.czyrux.countrykata.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.country.Country;
import de.czyrux.countrykata.core.domain.country.CountryImageBuilder;
import de.czyrux.countrykata.core.domain.image.ImageLoader;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private final ImageLoader imageLoader;
    private final List<Country> countries;

    public CountryAdapter(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        this.countries = new ArrayList<>(20);
    }

    public void addCountries(List<Country> countries) {
        this.countries.addAll(countries);
        this.notifyDataSetChanged();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        Country country = countries.get(position);

        String imageUrl = CountryImageBuilder.obtainImageUrl(country);
        imageLoader.load(imageUrl, holder.image);
        holder.name.setText(country.getName());
        holder.population.setText(String.format(Locale.GERMAN, "%,d", country.getPopulation()));
        holder.region.setText(country.getRegion() + ", " + country.getSubregion());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.country_item_image)
        ImageView image;
        @Bind(R.id.country_item_name)
        TextView name;
        @Bind(R.id.country_item_population)
        TextView population;
        @Bind(R.id.country_item_region)
        TextView region;

        CountryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
