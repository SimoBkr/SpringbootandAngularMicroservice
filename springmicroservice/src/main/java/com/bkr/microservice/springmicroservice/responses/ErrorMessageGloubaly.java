package com.bkr.microservice.springmicroservice.responses;

import java.util.Date;

public class ErrorMessageGloubaly {

    private Date timestamp;
    private String message;

    public ErrorMessageGloubaly(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
