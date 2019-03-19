package com.bdjr.tt.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description：定时任务ScheduledService
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.tt.service、
 * @email: cy880708@163.com
 * @date: 2019/3/19 下午7:08
 * @mofified By:
 */
@Component
public class ScheduledService {

    private static final Log logger = LogFactory.getLog(ScheduledService.class);

    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        logger.info("=====>>>>>使用cron  {}" + System.currentTimeMillis());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        logger.info("=====>>>>>使用fixedRate{}" + System.currentTimeMillis());
    }

    @Async
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        logger.info("=====>>>>>fixedDelay{}" + System.currentTimeMillis());
    }
}