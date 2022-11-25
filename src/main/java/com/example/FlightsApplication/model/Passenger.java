package com.example.FlightsApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Passenger {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

}