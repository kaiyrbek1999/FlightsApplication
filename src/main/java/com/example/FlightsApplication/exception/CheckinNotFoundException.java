package com.example.FlightsApplication.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckinNotFoundException extends Exception {

    public CheckinNotFoundException(String message) {
        super(message);
    }
}
