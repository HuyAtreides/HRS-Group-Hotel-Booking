package com.hrs.hotelbooking.stub;

import com.hrs.hotelbooking.application.CurrentDateTimeService;
import java.time.Instant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class StubCurrentDateTimeService implements CurrentDateTimeService {

    @Override
    public Instant getCurrentDateTime() {
        return Instant.parse("2024-03-09T00:00:00Z");
    }
}
