package com.login.authentication.exceptions;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final Throwable throwable;
    private final ZonedDateTime timestamp;

    public ApiException(String message,
                        HttpStatus httpStatus,
                        Throwable throwable,
                        ZonedDateTime timestamp) {
        super();
        this.message = message;
        this.httpStatus = httpStatus;
        this.throwable = throwable;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

}
