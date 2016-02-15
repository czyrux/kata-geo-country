package de.czyrux.countrykata.ui.list.model;

public class CountryUIModel {

    private final String name;
    private final String imageUrl;
    private final String population;
    private final String subregion;
    private final String alphaCode;

    public CountryUIModel(String name, String imageUrl, String population, String subregion, String alphaCode) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.population = population;
        this.subregion = subregion;
        this.alphaCode = alphaCode;

    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPopulation() {
        return population;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getAlphaCode() {
        return alphaCode;
    }
}
