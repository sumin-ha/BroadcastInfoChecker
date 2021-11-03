package com.broadcastinfochecker.sm.service;

import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ScheduledService {

    @Scheduled(fixedDelay = 1000)
    public void testJob() {
        System.out.println("test scheduled - " + System.currentTimeMillis() / 1000);
    }
}
