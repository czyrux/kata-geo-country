package de.czyrux.countrykata.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.czyrux.countrykata.R;
import de.czyrux.countrykata.core.domain.image.ImageLoader;
import de.czyrux.countrykata.ui.list.model.CountryItemViewModel;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private final ImageLoader imageLoader;
    private final List<CountryItemViewModel> countries;
    private final CountryListListener listListener;

    public CountryAdapter(ImageLoader imageLoader, CountryListListener listListener) {
        this.imageLoader = imageLoader;
        this.listListener = listListener;
        this.countries = new ArrayList<>(20);
    }

    public void setCountries(List<CountryItemViewModel> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        this.notifyDataSetChanged();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country_item, parent, false), listListener);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        CountryItemViewModel country = countries.get(position);
        holder.bind(country,imageLoader);
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

        private final CountryListListener listListener;

        CountryViewHolder(View view, final CountryListListener listListener) {
            super(view);
            this.listListener = listListener;
            ButterKnife.bind(this, view);

        }

        public void bind(final CountryItemViewModel country, ImageLoader imageLoader) {
            imageLoader.load(country.getImageUrl(), image);
            name.setText(country.getName());
            population.setText(country.getPopulation());
            region.setText(country.getSubregion());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listListener.onCountryClicked(country, getAdapterPosition());
                }
            });
        }
    }
}
