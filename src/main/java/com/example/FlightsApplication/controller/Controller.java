package com.example.FlightsApplication.controller;

import com.example.FlightsApplication.dto.CheckTicketResponseDto;
import com.example.FlightsApplication.dto.CheckinStatusResponseDto;
import com.example.FlightsApplication.exception.CheckinNotFoundException;
import com.example.FlightsApplication.service.CheckinService;
import com.example.FlightsApplication.service.TicketService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;


import static com.example.FlightsApplication.constants.Constants.API_V1;

@RestController
@RequestMapping(API_V1)
@Slf4j
@Validated
@RequiredArgsConstructor
public class Controller {

    private final CheckinService checkinService;

    private final TicketService ticketService;

    @GetMapping(value = "check-ticket/{ticketId}")
    @ApiOperation("Check ticket by ticketId")
    public ResponseEntity<CheckTicketResponseDto> checkTicket(@PathVariable("ticketId") @NotNull @Min(0) @Max(999999) Integer ticketId) {
        log.info("CheckTicket request with param ticketId : {}", ticketId);
        return ResponseEntity.ok(new CheckTicketResponseDto(ticketService.checkTicket(ticketId)));
    }

    @GetMapping(value = "checkin-status")
    @ApiOperation("Checkin status by destinationId and baggageId")
    public ResponseEntity<CheckinStatusResponseDto> checkinStatus(@RequestParam("destinationId") @NotNull @Min(0) @Max(999999) Integer destinationId,
                                                                  @RequestParam("baggageId") @NotNull @NotBlank @Size(max = 6) String baggageId) throws CheckinNotFoundException {
        log.info("CheckinStatus request with param destinationId : {} , baggageId : {}", destinationId, baggageId);
        return ResponseEntity.ok(new CheckinStatusResponseDto(checkinService.checkStatus(destinationId, baggageId)));
    }
}
