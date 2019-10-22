package com.souche.salt_common_01.config;

import com.souche.salt_common_01.controller.NginxController;
import com.souche.salt_common_01.controller.SaltMcheController;
import com.souche.salt_common_01.controller.SyncController;
import com.souche.salt_common_01.controller.TimeJobController;
import com.souche.salt_common_01.entity.SaltMche;
import com.souche.salt_common_01.service.SyncService;
import com.souche.salt_common_01.service.TimeJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class SchedulerQuartzJob2 implements Job{
@Autowired
private NginxController nginxController;
@Autowired
private SaltMcheController saltMcheController;
@Autowired
private TimeJobController timeJobController;
@Autowired
private SyncService syncService;


    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        System.out.println("开始："+System.currentTimeMillis());

        String name=arg0.getJobDetail().getKey().getName();
        if ("updateNginx".equals(name)){
            nginxController.initialize();
        }else if("updateSalt".equals(name)){
             saltMcheController.initSaltMche();
        }else if("failNum".equals(name)){
            timeJobController.dayFailNum();
        }else if("syhm".equals(name)){
             syncService.syhosts();
             syncService.syncData();
             //syncService.addRedisInstance();
//             syncService.slbInstance();
//             syncService.nodeInstance();
//             syncService.mysqlInstance();
//             syncService.userInstance();
//             syncService.syResource();

        }

        System.out.println("结束："+System.currentTimeMillis());

    }



}
