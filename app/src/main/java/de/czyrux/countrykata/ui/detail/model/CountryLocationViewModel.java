package de.czyrux.countrykata.ui.detail.model;

public class CountryLocationViewModel {

    String locationTitle;
    String latitude;
    String longitude;
    boolean showExploreNeighbours;
    String neighbours;
    String timezones;

    public String getLocationTitle() {
        return locationTitle;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public boolean showExploreNeighbours() {
        return showExploreNeighbours;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public String getTimezones() {
        return timezones;
    }
}
