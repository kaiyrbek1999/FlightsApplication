package com.example.FlightsApplication.service;

import com.example.FlightsApplication.cache.LRUCache;
import com.example.FlightsApplication.exception.CheckinNotFoundException;
import com.example.FlightsApplication.model.CheckIn;
import com.example.FlightsApplication.repo.CheckinRepo;
import com.example.FlightsApplication.service.impl.CheckinServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static com.example.FlightsApplication.enums.CheckStatus.SUCCEED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckinServiceImplTest {

    @Mock
    private CheckinRepo checkinRepo;

    @Mock
    private LRUCache lruCache;

    @InjectMocks
    private CheckinServiceImpl checkinService;

    @Test
    public void testCheckinService() throws CheckinNotFoundException {
        CheckIn checkIn = new CheckIn(512352, "SHSDDS", SUCCEED);
        when(lruCache.get(any())).thenReturn(Optional.of(checkIn));
        Boolean result = checkinService.checkStatus(512352,"SHSDDS");
        assertThat(result).isEqualTo(true);

        when(lruCache.get(any())).thenReturn(Optional.empty());
        when(checkinRepo.getStorage()).thenReturn(Map.of("512352:SHSDDS", checkIn));
        result = checkinService.checkStatus(512352,"SHSDDS");
        assertThat(result).isEqualTo(true);

        when(lruCache.get(any())).thenReturn(Optional.empty());
        when(checkinRepo.getStorage()).thenReturn(Map.of("5125:SHSDDS", checkIn));
        assertThrows(CheckinNotFoundException.class, ()->{
            checkinService.checkStatus(512352,"SHSDDS");
        });
    }
}