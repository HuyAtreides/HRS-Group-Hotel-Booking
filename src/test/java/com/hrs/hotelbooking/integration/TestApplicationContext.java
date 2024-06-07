package com.hrs.hotelbooking.integration;

import com.hrs.hotelbooking.application.AuthenticatedUserHolderService;
import com.hrs.hotelbooking.application.CurrentDateTimeService;
import com.hrs.hotelbooking.stub.StubAuthenticatedUserService;
import com.hrs.hotelbooking.stub.StubCurrentDateTimeService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
public class TestApplicationContext {

    @Bean
    @Profile("test")
    public AuthenticatedUserHolderService authenticatedUserHolderService() {
        return new StubAuthenticatedUserService();
    }

    @Bean
    @Profile("test")
    public CurrentDateTimeService currentDateTimeService() {
        return new StubCurrentDateTimeService();
    }
}
