package de.czyrux.countrykata.core.domain.exception;

public class ConnectionTimeoutException extends Throwable {

    public ConnectionTimeoutException(String detailMessage) {
        super(detailMessage);
    }
}
