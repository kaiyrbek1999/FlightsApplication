
package com.example.FlightsApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Ticket {

	private Integer id;

	private Passenger passenger;
}