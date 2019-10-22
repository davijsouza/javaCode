package com.souche.salt_common_01.service.impl;

import com.souche.salt_common_01.entity.RunJob;
import com.souche.salt_common_01.entity.RunJobExample;
import com.souche.salt_common_01.entity.ScriptsExample;
import com.souche.salt_common_01.mapper.JobMapper;
import com.souche.salt_common_01.mapper.RunJobMapper;
import com.souche.salt_common_01.mapper.ScriptsMapper;
import com.souche.salt_common_01.service.RunJobService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RunjobServiceImpl implements RunJobService {
    @Autowired
    private JobMapper jobMapper;
     @Autowired
    private ScriptsMapper scriptsMapper;
     @Autowired
    private ScriptsService scriptsService;
 @Autowired
    private RunJobMapper runJobMapper;

    @Override
    public String addRunJob(String jobId, String step1, String step2, String step3) {
        RunJob runJob=new RunJob();
        String rId="runJobId_"+ IDUtils.genItemId();
        String jobLogId="jobLogID"+ IDUtils.genItemId();
        runJob.setId(rId);
       // runJob.setMode(step1);
        runJob.setJobId(jobId);
        runJob.setJobName(jobMapper.selectByPrimaryKey(jobId).getName());
        runJob.setStep1(step1);
        runJob.setStep2(step2);
        runJob.setStep3(step3);
        runJob.setStep(0);
        runJob.setJobLogId(jobLogId);
        ScriptsExample scriptsExample=new ScriptsExample();
        scriptsExample.or().andIdDeletedEqualTo(jobId);
        StringBuffer totalStep=new StringBuffer();
        if(!"disable".equals(step1)){
            totalStep.append("step1").append(" ");
        }
        if(!"disable".equals(step2)){
            totalStep.append("step2").append(" ");
        }
        if(!"disable".equals(step3)){
            totalStep.append("step3").append(" ");
        }
        runJob.setTotalStep(totalStep.toString());
        runJob.setStepStatus("running");
        runJob.setStartTime(new Date());
        runJob.setUser(scriptsService.getLoginName());
        runJob.setFinish("no");
        runJobMapper.insertSelective(runJob);
        return rId;
    }

    @Override
    public List<RunJob> queryRJob() {
        RunJobExample runJobExample=new RunJobExample();
        runJobExample.setOrderByClause("startTime desc");
        runJobExample.or().andFinishEqualTo("no").andUserEqualTo(scriptsService.getLoginName());

        return runJobMapper.selectByExample(runJobExample);
    }

    @Override
    public void rjobFinish(String runJobId) {
        RunJob runJob=runJobMapper.selectByPrimaryKey(runJobId);
       if("finish".equals(runJob.getMode())){
            runJob.setId(runJobId);
            runJob.setFinish("yes");
            runJobMapper.updateByPrimaryKeySelective(runJob);
        }

    }

    @Override
    public Integer deleteRunJob(String runJobId) {

        return runJobMapper.deleteByPrimaryKey(runJobId);
    }
}
