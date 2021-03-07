package org.freecode.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Component for Spring managed
 */
@Component
public class SimpleScheduler {
    private static final SimpleDateFormat dtFmt = new SimpleDateFormat("HH:mm:ss");
    private final static Logger log = LoggerFactory.getLogger(SimpleScheduler.class);

    @Scheduled(fixedRateString = "${scheduler.rate}")
    /**
     * fixedRate: invoke at an intervals - 5 seconds
     * fixedDelay: delay to start
     * initialDelay: start after the delay value
     */
    public void scheduledTimeCheck() {
        log.info("The time is now: " + dtFmt.format(new Date()));
    }
}
