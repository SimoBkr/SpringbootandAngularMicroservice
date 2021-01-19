package com.bkr.microservice.springmicroservice.hundlers;

import com.bkr.microservice.springmicroservice.responses.ErrorMessageGloubaly;
import com.bkr.microservice.springmicroservice.responses.UserErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class UserExceptionHundlers{

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Object> HandleUserException(UserException ex , WebRequest request)
    {
        UserErrorMessage errorMessage = new UserErrorMessage(new Date(),ex.getMessage());

        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> HandleUserExceptionGloubaly(Exception ex , WebRequest request){

        UserErrorMessage errorMessage = new UserErrorMessage(new Date(),ex.getMessage());
//        ErrorMessageGloubaly errorMessageGloubaly = new ErrorMessageGloubaly(new Date(), ex.getMessage());

        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
