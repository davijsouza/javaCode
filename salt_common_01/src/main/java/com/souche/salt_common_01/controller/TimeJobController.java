package com.souche.salt_common_01.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.entity.*;
import com.souche.salt_common_01.mapper.*;
import com.souche.salt_common_01.service.ClientService;
import com.souche.salt_common_01.service.ScriptVarSevice;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.service.TimeJobService;
import com.souche.salt_common_01.utils.HttpClientUtil;
import com.souche.salt_common_01.utils.IDUtils;
import com.souche.salt_common_01.utils.RestUtil1;
import com.souche.salt_common_01.utils.TaotaoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Api(description = "定时任务的CRUD",tags = {"timeJob作业相关接口"})
@RestController
@Slf4j
public class TimeJobController {
    @Autowired
    private TimeJobService timeJobService;
    @Autowired
    private ScriptsService scriptsService;
    @Autowired
    private TimeJobsMapper timeJobsMapper;
    @Autowired
    private QuartzApiController quartzApiController;

    @PostMapping("/lantu/salt/timeJob")
    @ApiOperation(value = "新增一个Timejob")
    public String addTimeJob(
            @RequestParam String jobName,
           // @RequestParam String  user,
            @RequestParam String   script,
            @RequestParam String doTime,
            @RequestParam String cron,
            @RequestParam Integer totalNum
            ){
       return timeJobService.addTimeJob(jobName,scriptsService.getLoginName(),script,doTime,totalNum,cron);

    }

 /*   @GetMapping("/lantu/salt/timeJob")
    @ApiOperation(value = "查询所有Timejob")
    public List<TimeJobs> queryTimeJob(
            ){
       return timeJobService.queryTimeJob();

    }
*/

    @PostMapping("/lantu/salt/doJob")
    public void doJob(String jobID,String jobType,String user){

       TimeJobs timeJobs= timeJobsMapper.selectByPrimaryKey(jobID);
       if(timeJobs.getTotalNum()==0){

           timeJobService.doJob(jobID,jobType);
           timeJobs.setDoneNum(timeJobs.getDoneNum()+1);
           timeJobsMapper.updateByPrimaryKey(timeJobs);
       }else{

           if(timeJobs.getDoneNum()+1<timeJobs.getTotalNum()){
               timeJobService.doJob(jobID,jobType);
               timeJobs.setDoneNum(timeJobs.getDoneNum()+1);

           }else {
               timeJobService.doJob(jobID,jobType);
               timeJobs.setDoneNum(timeJobs.getDoneNum()+1);
               quartzApiController.deleteJob(timeJobs.getId(),"group1");
               timeJobs.setStatus("stop");
           }
           timeJobsMapper.updateByPrimaryKey(timeJobs);

       }



        }

    @PutMapping("/lantu/salt/updateCron")
    public Integer updateCron(String id,String doTime,String cron){

    return timeJobService.updateCron(id,doTime,cron);
        }


    @GetMapping ("/lantu/salt/timeName")
    public Boolean isTimeJobName(String timeJobName){

        return  timeJobService.ifTimeJobName(timeJobName);
    }

    @GetMapping ("/lantu/salt/startJob")
    public void startJob(String id,Integer totalNum){

          timeJobService.startJob(id,totalNum);
    }

    @GetMapping ("/lantu/salt/stopJob")
    public void stopJob(String id){

        timeJobService.stopJob(id);
    }

    @DeleteMapping("/lantu/salt/delTimeJob/{id}")
    public String deleteJob(@PathVariable String id){

       return timeJobService.deleteJob(id);
    }

    @GetMapping ("/lantu/salt/timeJobBy")
    public List<TimeJobs> timeJobBy(String name,String status,String jobId){

        return timeJobService.timeJobBy(name,status,jobId);
    }

    @GetMapping ("/lantu/salt/timeJobById")
    public  TimeJobs timeJobBy(String jobId){
        log.info(jobId);
        return timeJobService.getTimeJobById(jobId);
    }

    @GetMapping ("/lantu/salt/dayFailNum")
    public void dayFailNum() {
        timeJobService.dayFailNum();
    }
    @GetMapping ("/lantu/salt/failJobs")
    public TaotaoResult failJobs() {
      return   timeJobService.failJobs();
    }
}






