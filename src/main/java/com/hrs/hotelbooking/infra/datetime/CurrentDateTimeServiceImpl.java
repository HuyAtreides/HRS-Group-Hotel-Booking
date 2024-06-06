package com.hrs.hotelbooking.infra.datetime;

import com.hrs.hotelbooking.application.CurrentDateTimeService;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class CurrentDateTimeServiceImpl implements CurrentDateTimeService {
    @Override
    public Instant getCurrentDateTime() {
        return Instant.now();
    }
}
