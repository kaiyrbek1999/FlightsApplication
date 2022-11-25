package com.example.FlightsApplication.service.impl;

import com.example.FlightsApplication.cache.LRUCache;
import com.example.FlightsApplication.model.Ticket;
import com.example.FlightsApplication.repo.TicketRepo;
import com.example.FlightsApplication.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final LRUCache cache;

    /**
     * Check that ticket exists
     *
     * @param ticketId Ticked ID
     * @return Boolean flag
     */
    @Override
    public Boolean checkTicket(Integer ticketId) {
        log.info("CheckTicket started with parameters ticketId: {}", ticketId);

        Boolean result = checkIsExists(ticketId);

        log.info("CheckTicket finished");
        return result;
    }

    private Boolean checkIsExists(Integer ticketId) {
        Boolean existInCache = checkIsExistsInCache(ticketId);
        if (!existInCache) {
            if (!checkIsExistsInStorage(ticketId)) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
        return Boolean.TRUE;
    }

    private Boolean checkIsExistsInCache(Integer ticketId) {
        Optional<Ticket> ticket = cache.get(ticketId);
        if (ticket.isPresent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean checkIsExistsInStorage(Integer ticketId) {
        boolean isExistsInStorage = ticketRepo.getStorage().containsKey(ticketId);

        if (!isExistsInStorage) {
            return Boolean.FALSE;
        }
        addToCache(ticketId, ticketRepo.getStorage().get(ticketId));
        return Boolean.TRUE;
    }

    private void addToCache(Integer ticketId, Ticket ticket) {
        cache.put(ticketId, ticket);
    }
}
