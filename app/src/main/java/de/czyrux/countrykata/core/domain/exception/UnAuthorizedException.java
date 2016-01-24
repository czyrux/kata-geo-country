package de.czyrux.countrykata.core.domain.exception;

public class UnAuthorizedException extends Throwable {
    public UnAuthorizedException(String detailMessage) {
        super(detailMessage);
    }
}
