package de.czyrux.countrykata.core.domain.exception;

public class CountryNotFoundException extends Throwable {
    public CountryNotFoundException(String detailMessage) {
        super(detailMessage);
    }
}
