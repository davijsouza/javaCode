
package com.souche.salt_common_01.config;



import com.souche.salt_common_01.controller.TimeJobController;
import com.souche.salt_common_01.entity.TimeJobs;
import com.souche.salt_common_01.entity.TimeJobsExample;
import com.souche.salt_common_01.mapper.TimeJobsMapper;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

@Configuration
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private QuartzScheduler quartzScheduler;
    @Autowired
    private TimeJobsMapper timeJobsMapper;


/**
     * 初始启动quartz
     */

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
         // quartzScheduler.startJob2();
        //  quartzScheduler.startJob3();
          //sy
           quartzScheduler.startJob5();

          //quartzScheduler.startJob4(); //丁丁统计失败任务

        try {
            //读取我的数据库  获得要执行的任务名称 和执行时间，然后添加进去
            TimeJobsExample timeJobsExample=new TimeJobsExample();
            timeJobsExample.or().andStatusEqualTo("running");
            List<TimeJobs> timeJobs=timeJobsMapper.selectByExample(timeJobsExample);
            for (TimeJobs tjob:timeJobs
                 ) {
                   quartzScheduler.startJob(tjob.getId(),tjob.getCron());
            }
            System.out.println("任务已经启动...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

