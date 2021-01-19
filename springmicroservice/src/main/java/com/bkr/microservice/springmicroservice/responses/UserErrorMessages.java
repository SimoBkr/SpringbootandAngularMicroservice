package com.bkr.microservice.springmicroservice.responses;

public enum UserErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field."),
    RECORD_ALREADY_EXISTS("record already exists."),
    INRERNAL_SERVER_ERROR("internal server error."),
    NO_RECORD_FOUND("Record with provided id is not found.");


    private String errorMessage ;

    UserErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
