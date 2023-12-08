package com.example.hk_ht.Util;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
@EnableScheduling
public class Timing {

    @Scheduled(cron = "0/5 * * * * ?")
    public void testTiming(){
        System.out.println("执行定时任务"+ new Date());
    }

}
