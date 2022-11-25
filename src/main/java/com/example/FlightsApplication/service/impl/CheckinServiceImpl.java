package com.example.FlightsApplication.service.impl;

import com.example.FlightsApplication.cache.LRUCache;
import com.example.FlightsApplication.exception.CheckinNotFoundException;
import com.example.FlightsApplication.model.CheckIn;
import com.example.FlightsApplication.repo.CheckinRepo;
import com.example.FlightsApplication.service.CheckinService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.FlightsApplication.enums.CheckStatus.SUCCEED;

@Service
@Slf4j
@AllArgsConstructor
public class CheckinServiceImpl implements CheckinService {

    private final CheckinRepo checkinRepo;

    private final LRUCache cache;

    /**
     * Check status of checkin
     *
     * @param destinationId Destination ID
     * @param baggageId     Baggage ID
     * @return Boolean flag
     */
    @Override
    public Boolean checkStatus(Integer destinationId, String baggageId) throws CheckinNotFoundException {
        log.info("CheckStatus started with parameters destinationId: {} , baggageId: {}",
                destinationId, baggageId);

        Boolean result = checkIsSucceed(destinationId, baggageId);

        log.info("CheckStatus finished");
        return result;
    }

    /**
     * Check that checkin succeeded
     *
     * @param destinationId Destination ID
     * @param baggageId     Baggage ID
     * @return Boolean flag
     */
    private Boolean checkIsSucceed(Integer destinationId, String baggageId) throws CheckinNotFoundException {
        String key = destinationId.toString() + ":" + baggageId;
        Optional<CheckIn> checkin = getFromCacheIfIsExist(key);
        if (!checkin.isPresent()) {
            if (!checkinRepo.getStorage().containsKey(key)) {
                throw new CheckinNotFoundException("Checkin not found for key: " + key);
            }
            return checkinRepo.getStorage().get(key).getCheckStatus().equals(SUCCEED);
        }
        return checkin.get().getCheckStatus().equals(SUCCEED);
    }

    private Optional<CheckIn> getFromCacheIfIsExist(String key) {
        return cache.get(key);
    }

    private void addToCache(String key, CheckIn checkin) {
        cache.put(key, checkin);
    }
}
