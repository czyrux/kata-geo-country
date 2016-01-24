package de.czyrux.countrykata.core.domain.country;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Country {

    @SerializedName("name")
    private String name;
    @SerializedName("capital")
    private String capital;
    @SerializedName("altSpellings")
    private String[] alternativeSpellings;
    @SerializedName("relevance")
    private float relevance;
    @SerializedName("region")
    private String region;
    @SerializedName("subregion")
    private String subregion;

    @SerializedName("population")
    private long population;

    @SerializedName("demonym")
    private String demonym;

    @SerializedName("area")
    private float area;

    @SerializedName("gini")
    private float gini;

    @SerializedName("timezones")
    private String[] timezones;

    @SerializedName("borders")
    private String[] borders;

    @SerializedName("nativeName")
    private String nativeName;

    @SerializedName("callingCodes")
    private String[] callingCodes;

    @SerializedName("topLevelDomain")
    private String[] topLevelDomain;

    @SerializedName("alpha2Code")
    private String alpha2Code;

    @SerializedName("alpha3Code")
    private String alpha3Code;

    @SerializedName("currencies")
    private String[] currencies;
    @SerializedName("languages")
    private String[] languages;

    @SerializedName("latlng")
    private float[] latlong;

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public float getRelevance() {
        return relevance;
    }

    public void setRelevance(float relevance) {
        this.relevance = relevance;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getGini() {
        return gini;
    }

    public void setGini(float gini) {
        this.gini = gini;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String[] getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(String[] callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String[] getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(String[] topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String[] currencies) {
        this.currencies = currencies;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String[] getAlternativeSpellings() {
        return alternativeSpellings;
    }

    public void setAlternativeSpellings(String[] alternativeSpellings) {
        this.alternativeSpellings = alternativeSpellings;
    }

    public float[] getLatlong() {
        return latlong;
    }

    public void setLatlong(float[] latlong) {
        this.latlong = latlong;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("name='").append(name).append('\'');
        sb.append(", capital='").append(capital).append('\'');
        sb.append(", alternativeSpellings=").append(Arrays.toString(alternativeSpellings));
        sb.append(", relevance=").append(relevance);
        sb.append(", region='").append(region).append('\'');
        sb.append(", subregion='").append(subregion).append('\'');
        sb.append(", population=").append(population);
        sb.append(", demonym='").append(demonym).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", gini=").append(gini);
        sb.append(", timezones=").append(Arrays.toString(timezones));
        sb.append(", borders=").append(Arrays.toString(borders));
        sb.append(", nativeName='").append(nativeName).append('\'');
        sb.append(", callingCodes=").append(Arrays.toString(callingCodes));
        sb.append(", topLevelDomain=").append(Arrays.toString(topLevelDomain));
        sb.append(", alpha2Code='").append(alpha2Code).append('\'');
        sb.append(", alpha3Code='").append(alpha3Code).append('\'');
        sb.append(", currencies=").append(Arrays.toString(currencies));
        sb.append(", languages=").append(Arrays.toString(languages));
        sb.append(", latlong=").append(Arrays.toString(latlong));
        sb.append('}');
        return sb.toString();
    }
}
