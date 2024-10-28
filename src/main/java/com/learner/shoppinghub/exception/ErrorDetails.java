package com.learner.shoppinghub.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ErrorDetails {
    private HttpStatus status;
    private String message;
    private String timeStamp;
    private int statusCode;
}
