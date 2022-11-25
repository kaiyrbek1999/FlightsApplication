package com.example.FlightsApplication.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckinNotFoundException extends Exception{

    private final String message;
}
