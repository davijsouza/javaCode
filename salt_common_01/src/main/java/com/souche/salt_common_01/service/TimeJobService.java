package com.souche.salt_common_01.service;


import com.souche.salt_common_01.entity.TimeJobs;
import com.souche.salt_common_01.utils.TaotaoResult;

import java.util.List;

public interface TimeJobService {

    String addTimeJob(String jobName, String user, String script, String doTime, Integer totalNum, String cron);

    void  doJob(String jobID, String jobType);

    Boolean ifTimeJobName(String timeJobName);

    List<TimeJobs> queryTimeJob();

    void startJob(String id, Integer totalNum);

    void stopJob(String id);

    String deleteJob(String id);

    List<TimeJobs> timeJobBy(String name, String status, String jobId);


    Integer updateCron(String id, String doTime, String cron);

    TimeJobs getTimeJobById(String jobId);

    void dayFailNum();

    TaotaoResult failJobs();
}
