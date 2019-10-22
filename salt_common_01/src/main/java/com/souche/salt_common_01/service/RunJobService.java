package com.souche.salt_common_01.service;

import com.souche.salt_common_01.entity.RunJob;

import java.util.List;

public interface RunJobService {
    String addRunJob(String jobId, String step1, String step2, String step3);

    List<RunJob> queryRJob();

    void rjobFinish(String runJobId);

    Integer deleteRunJob(String runJobId);
}
