package com.example.FlightsApplication.service;

import com.example.FlightsApplication.exception.CheckinNotFoundException;

public interface CheckinService {

    Boolean checkStatus(Integer destinationId, String baggageId) throws CheckinNotFoundException;

}
