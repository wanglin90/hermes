package com.wool.hermes.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wanglin on 17-1-17.
 */
@Component
public class ScheduleTask {

    public static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Scheduled(cron = "*/5 * * * * *")
    public void testSchedule() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        logger.info("schedule trigger at {}", df.format(new Date()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("schedule done ... ");
    }
}
