package com.souche.salt_common_01.service;

import com.souche.salt_common_01.entity.*;

import java.util.List;
import java.util.Map;

public interface JobService {
    /*新增一个job*/
    Integer addJob(AddJob addJob);

    List<Job> queryAllJobs(Map<String, String> param);


    Integer deleteJob(String id, String user);

    Integer updateJob(Job Job,List<String> scriptIds);

    SonJob queryJob(String id);

    Boolean existName(String name);

    List<Job> findPrivateJob(String user, String name, String creater, String lastmodifyuser);

    List<ScriptSon> queryJobScript(String id);

    Integer delStep(String id, String user);

    Integer updateJobName(String jobId, String jobName);
}
