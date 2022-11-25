package com.example.FlightsApplication.repo;

import com.example.FlightsApplication.model.Passenger;
import com.example.FlightsApplication.model.Ticket;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Getter
public class TicketRepo {

    private Map<Integer, Ticket> storage;

    public TicketRepo() {
        storage = Map.of(4342, new Ticket(4342, new Passenger(5125, "Mark", "White", "mwhite@gmail.com")),
                1424, new Ticket(1424, new Passenger(1242, "Suisse", "Clark", "sclark@gmail.com")),
                5345, new Ticket(5345, new Passenger(5135, "Adam", "Smith", "asmith@gmail.com")),
                5234, new Ticket(5234, new Passenger(5635, "Mishel", "Martinez", "mmartinez@gmail.com")),
                2344, new Ticket(2344, new Passenger(2342, "Edward", "Norton", "enorton@gmail.com")),
                4451, new Ticket(4451, new Passenger(7632, "Lara", "Swift", "lswift@gmail.com")),
                5252, new Ticket(5252, new Passenger(3512, "Mariya", "Moles", "mmoles@gmail.com")),
                4532, new Ticket(4532, new Passenger(8665, "Luis", "Lawrence", "llawrence@gmail.com")));
    }
}