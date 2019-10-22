package com.souche.salt_common_01.service.impl;

import com.souche.salt_common_01.controller.SctiptsController;
import com.souche.salt_common_01.controller.TimeJobController;
import com.souche.salt_common_01.entity.JobsLog;
import com.souche.salt_common_01.entity.Scripts;
import com.souche.salt_common_01.entity.ScriptsExample;
import com.souche.salt_common_01.mapper.ScriptsMapper;
import com.souche.salt_common_01.service.ClientService;
import com.souche.salt_common_01.utils.IDUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class SchedulerQuartzJob1 implements Job{


    @Autowired
    private TimeJobController timeJobController;




    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

            System.out.println("开始："+System.currentTimeMillis());

            String jobID=arg0.getJobDetail().getKey().getName();
            timeJobController.doJob(jobID,"Tjob",null);


        System.out.println("结束："+System.currentTimeMillis());



    }








}
