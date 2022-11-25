package com.example.FlightsApplication.service;

import com.example.FlightsApplication.cache.LRUCache;
import com.example.FlightsApplication.exception.CheckinNotFoundException;
import com.example.FlightsApplication.model.CheckIn;
import com.example.FlightsApplication.model.Passenger;
import com.example.FlightsApplication.model.Ticket;
import com.example.FlightsApplication.repo.TicketRepo;
import com.example.FlightsApplication.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketRepo ticketRepo;

    @Mock
    private LRUCache lruCache;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    public void testTicketService() {
        Passenger passenger = new Passenger(5125, "Mark", "White", "mwhite@gmail.com");
        Ticket ticket = new Ticket(4342, passenger);
        when(lruCache.get(any())).thenReturn(Optional.of(ticket));
        Boolean result = ticketService.checkTicket(4342);
        assertThat(result).isEqualTo(true);

        when(lruCache.get(any())).thenReturn(Optional.empty());
        when(ticketRepo.getStorage()).thenReturn(Map.of(4342, ticket));
        result = ticketService.checkTicket(4342);
        assertThat(result).isEqualTo(true);
    }
}