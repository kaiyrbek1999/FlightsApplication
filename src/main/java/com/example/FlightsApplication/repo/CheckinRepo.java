package com.example.FlightsApplication.repo;

import com.example.FlightsApplication.model.CheckIn;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.example.FlightsApplication.enums.CheckStatus.NOT_SUCCEED;
import static com.example.FlightsApplication.enums.CheckStatus.SUCCEED;

@Service
@Getter
public class CheckinRepo {

    private Map<String, CheckIn> storage;

    public CheckinRepo() {
        storage = Map.of("123456:RSDSHC", new CheckIn(123456, "RSDSHC", SUCCEED),
                "512352:SHSDDS", new CheckIn(512352, "SHSDDS", SUCCEED),
                "634634:HDDSHC", new CheckIn(634634, "HDDSHC", NOT_SUCCEED),
                "734574:SH245S", new CheckIn(734574, "SH245S", SUCCEED),
                "734736:HD1512", new CheckIn(734736, "HD1512", NOT_SUCCEED),
                "663144:HS63FH", new CheckIn(663144, "HS63FH", NOT_SUCCEED),
                "635213:HSDG43", new CheckIn(635213, "HSDG43", SUCCEED),
                "735472:JBFW53", new CheckIn(735472, "JBFW53", SUCCEED));
    }
}