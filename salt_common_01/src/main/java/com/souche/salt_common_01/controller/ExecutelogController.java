package com.souche.salt_common_01.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.souche.salt_common_01.entity.ExecuteLog;
import com.souche.salt_common_01.entity.JobScriptLog;
import com.souche.salt_common_01.entity.JobsLog;
import com.souche.salt_common_01.service.LogService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.TaotaoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@Api(tags = {"日志的相关接口"},description = "日志的CRUD")
@RestController
public class ExecutelogController {
    @Autowired
    private LogService logService;
    @Autowired
    private ScriptsService scriptsService;

    /*
     *   查询执行历史中某条job日志执行的
     *   n个脚本
     *   参数：jobID
     *   开始，结束时间
    * */
    @GetMapping("/lantu/salt/jobLog/queryScript")
    @ApiOperation(value = "查询某条job日志执行的脚本")
     public List<JobScriptLog> jobScriptQuery(@RequestParam(required = false)  String startTime,
                                              @RequestParam(required = false)   String endTime,
                                              @RequestParam String JobId
    ){

        return  logService.findJobScript(startTime,endTime,JobId);
    }
/*
* 查询某条job日志 下的具体某个脚本执行详情
*
* */
    @GetMapping("/lantu/salt/jobLog/executeLog")
    @ApiOperation(value = "查询执行日志")
    public List<Map<String,String>> jobLogScriptOutput(String jobLogId,
                          String scriptId){

        return logService.findJobScriptResult(jobLogId,scriptId);
    }




    /*查询所有的 jobLog*/
    @GetMapping("/lantu/salt/jobsLog/queryAll")
    @ApiOperation(value = "条件查询job日志")
        public PageInfo<JobsLog> findAllJobLog(String jobName, String user, String jobType, String status, @RequestParam(defaultValue = "1") Integer pageNum , @RequestParam(defaultValue = "30")Integer pageSize){
        //System.err.println(user);
        if(user==null||user.equals("")){
            user= scriptsService.getLoginName();
        }
        //System.err.println(user);
        return logService.queryAllJobsLog(jobName, user,jobType,status, pageNum, pageSize);

    }


    @GetMapping("/lantu/salt/countAll")
    public TaotaoResult countAll(){

        return logService.countAll();
    }

}
