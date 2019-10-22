package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.entity.ExecuteLog;
import com.souche.salt_common_01.entity.RunJob;
import com.souche.salt_common_01.service.ClientService;
import com.souche.salt_common_01.service.RunJobService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.service.TimeJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "需要执行的执行",tags = "runJob接口")
public class RunJobController {

    @Autowired
    private RunJobService runJobService;

    @PostMapping("/lantu/salt/runJob")
    @ApiOperation(value = "新增一个执行的作业")
    public  String jobScript(
              @RequestParam("jobId") String jobId,
              @RequestParam("step1") String step1,
              @RequestParam("step2") String step2,
              @RequestParam("step3") String step3


    ){
         return runJobService.addRunJob(jobId,step1,step2,step3);

    }

    @GetMapping("/lantu/salt/queryRJob")
    @ApiOperation(value = "查询runjob")
    public List<RunJob> queryRJob(
//            @RequestParam("jobId") String jobId

    ){
        return runJobService.queryRJob();

    }

    @GetMapping("/lantu/salt/rjobFinish")
    @ApiOperation(value = "更改runjob结束")
    public void rjobFinish(
               String runJobId
    ){
          runJobService.rjobFinish(runJobId);

    }

    @DeleteMapping("/lantu/salt/runJob")
    @ApiOperation(value = "删除runjob")
    public Integer deleteRunJob(
            String runJobId
    ){
      return   runJobService.deleteRunJob(runJobId);

    }


}
