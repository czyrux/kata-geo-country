Kata Geo Country for Android 
=======
This kata will cover MVP pattern (Model View Presenter) in Android.

> Consumes API: http://restcountries.eu/

> Images can be obtained from: http://www.geonames.org/flags/x/ + countryCode + .gif

---

## Task

- Create Presenter and move all the presentation logic in there.
- Create UI model.
- Modify item content
 - Add region as: <region, subregion> or nothing if the fields are empty.
 - Set population in buckets: "less than 1 mill", rounded amount if >1mill and < 50 mill (10.315 --> ~ 10mill) or "more than 50 mill"

- Handle navigation
- Add new Activity: CountryDetailActivity following MVP.

## Open Source Libraries

 - Retrofit https://square.github.io/retrofit/
 - Picasso https://square.github.io/picasso/
 - Butterknife http://jakewharton.github.io/butterknife/

## Version
1.0.0

