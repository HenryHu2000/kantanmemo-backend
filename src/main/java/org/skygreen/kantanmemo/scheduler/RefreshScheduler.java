package org.skygreen.kantanmemo.scheduler;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;
import org.skygreen.kantanmemo.data.LearningProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class RefreshScheduler {
    public static final Logger LOG = LoggerFactory.getLogger(RefreshScheduler.class);
    @Inject
    Map<Long, LearningProcess> learningProcessMap;

    @Scheduled(cron = "0 0 0 * * ?")
    void dailyRefresh(ScheduledExecution execution) {
        learningProcessMap.clear();
        LOG.info("Daily refresh finished.");
    }
}
