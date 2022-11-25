package com.example.FlightsApplication.model;

import com.example.FlightsApplication.enums.CheckStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CheckIn {

    private Integer destinationId;

    private String baggageId;

    private CheckStatus checkStatus;
}
