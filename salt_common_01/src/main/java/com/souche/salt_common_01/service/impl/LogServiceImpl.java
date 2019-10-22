package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.souche.salt_common_01.entity.*;
import com.souche.salt_common_01.mapper.ExecuteLogMapper;
import com.souche.salt_common_01.mapper.JobsLogMapper;
import com.souche.salt_common_01.mapper.ScriptsMapper;
import com.souche.salt_common_01.mapper.TimeJobsMapper;
import com.souche.salt_common_01.service.LogService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.Date;
import com.souche.salt_common_01.utils.TaotaoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Slf4j
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private ExecuteLogMapper executeLogMapper;
    @Autowired
    private JobsLogMapper jobsLogMapper;
    @Autowired
    private ScriptsMapper scriptsMapper;
    @Autowired
    private ScriptsService scriptsService;
    @Autowired
    private TimeJobsMapper timeJobsMapper;
    /*根据ID 查询日志记录*/
    @Override
    public ExecuteLog queryExecuteLog(int id) {

        return executeLogMapper.selectByPrimaryKey(id);
    }
    /*查询所有job日志*/
    @Override
    public PageInfo<JobsLog> queryAllJobsLog(String jobName, String user, String jobType, String status, Integer pageNum , Integer pageSize) {
        //user="刘明";
        Map<String,String> map=new HashMap<>();
        map.put("jobName",jobName);
        map.put("user",user);
        map.put("jobType",jobType);
        map.put("status",status);
        PageHelper.startPage(pageNum,pageSize);
        List<JobsLog> logs= jobsLogMapper.conditionQuery(map);
        PageInfo<JobsLog> pageInfo = new PageInfo<JobsLog>(logs);
        //System.err.println(map);
        return pageInfo;
    }

    /*查询 作业下有几个脚本执行 */
    @Override
    public List<JobScriptLog> findJobScript(String startTime, String endTime, String JobId) {
        List<ExecuteLog> executeLogs=executeLogMapper.jobLogScript(startTime,endTime,JobId);
        //System.err.println("-------"+executeLogs);
        List<JobScriptLog> list=new ArrayList<>();
        int orderNum=1;
        for (ExecuteLog executeLog:executeLogs) {
            //System.err.println( executeLog);
            JobScriptLog jobScriptLog=new JobScriptLog();
            jobScriptLog.setEndtime(executeLog.getEndtime());
            jobScriptLog.setOrdernum(executeLog.getOrdernum());
            jobScriptLog.setScriptId(executeLog.getScriptid());
            jobScriptLog.setJobLogId(executeLog.getJobid());
            //查询脚本内容
            Scripts contentOfScripts= scriptsMapper.selectByPrimaryKey(executeLog.getScriptid());
            if(contentOfScripts!=null){
                log.info(contentOfScripts.getContent());
                jobScriptLog.setScriptContent(contentOfScripts.getContent());
            }
            /*查询执行的服务器数量*/
            List<ExecuteLog> listservernub=executeLogMapper.jobLogScripResult(Date.date2String(executeLog.getStarttime()),
                    Date.date2String(executeLog.getEndtime()),executeLog.getMissionname());
            /*执行的服务器数量*/
            jobScriptLog.setServerNum(listservernub.size());
            jobScriptLog.setStarttime(executeLog.getStarttime());

            jobScriptLog.setOrdernum(orderNum);
            orderNum++;
            jobScriptLog.setTotaltime(executeLog.getTotaltime());
            if(executeLog.getIsjob().equals("yes")){

                jobScriptLog.setScriptName(
                        scriptsMapper.selectByPrimaryKey( executeLog.getScriptid())
                                .getName()  );
            }else{
                jobScriptLog.setScriptName(executeLog.getMissionname());
            }

//          此脚本下遍历每个机器日志是否执行成功
            ExecuteLogExample executeLogExample=new ExecuteLogExample();
            executeLogExample.or().andStarttimeEqualTo(Date.date2String(jobScriptLog.getStarttime())).andEndtimeEqualTo(Date.date2String(jobScriptLog.getEndtime()))
                    .andMissionnameEqualTo(jobScriptLog.getScriptName());
            List<ExecuteLog> scriptExecuteLog=executeLogMapper.selectByExample(executeLogExample);

            boolean scriptStatus=true;
            for (ExecuteLog executeLog1:
            scriptExecuteLog) {
                System.err.println(executeLog1);
                System.err.println(executeLog1.getStatus());

                if("执行失败".equals(executeLog1.getStatus())){
                    scriptStatus=false;
                }
            }
            // 根据scriptStatus 判断成功失败
            if (scriptStatus){
                jobScriptLog.setStatus("执行成功");
            }else {
                jobScriptLog.setStatus("执行失败");
            }
            list.add(jobScriptLog);
        }
        return list;
    }

    @Override
    public List<JobScriptLog> stepJobLogScript(String JobId) {
        return null;
    }

    /*查询脚本返回结果*/
    @Override
    public List<Map<String,String>> findJobScriptResult(String jobLogId, String scriptId) {

            /*根据时间 和脚本ID 查询执行的机器*/
        List<ExecuteLog> list=executeLogMapper.jobLogScripResult2(jobLogId,scriptId);

        List<Map<String,String>> listmap=new ArrayList<>();
        for (ExecuteLog executeLog:list
                 ) {
            System.err.println(executeLog);
                Map<String,String> map=new HashMap<>();
                 String value=executeLog.getOutput();
                 JSONObject jsonObject=JSONObject.parseObject(value);
                 //Map valueMap=jsonObject;
                 map.put("instanceName", executeLog.getServer());
                 map.put("stdout", jsonObject.get("stdout").toString());
                 map.put("stderr", jsonObject.get("stderr").toString());
                 map.put("retcode", jsonObject.get("retcode").toString());

                listmap.add(map);
            }
        return listmap;
    }

    @Override
    public TaotaoResult countAll() {
        String loginName=scriptsService.getLoginName();
         JobsLogExample jobsLogExample=new JobsLogExample();
         jobsLogExample.createCriteria().andAccountEqualTo("Tjob").andStatusEqualTo("执行成功").andUserEqualTo(loginName);
         Integer successTimeJob= jobsLogMapper.selectByExample(jobsLogExample).size();

         JobsLogExample jobsLogExample2=new JobsLogExample();
         jobsLogExample2.createCriteria().andAccountEqualTo("Tjob").andStatusEqualTo("执行失败").andUserEqualTo(loginName);
         Integer failTimeJob= jobsLogMapper.selectByExample(jobsLogExample2).size();

        JobsLogExample jobsLogExample3=new JobsLogExample();
        jobsLogExample3.createCriteria().andAccountEqualTo("job").andStatusEqualTo("执行成功").andUserEqualTo(loginName);
        Integer successJob= jobsLogMapper.selectByExample(jobsLogExample3).size();

        JobsLogExample jobsLogExample4=new JobsLogExample();
        jobsLogExample4.createCriteria().andAccountEqualTo("job").andStatusEqualTo("执行失败").andUserEqualTo(loginName);
        Integer failJob= jobsLogMapper.selectByExample(jobsLogExample4).size();

        JobsLogExample jobsLogExample5=new JobsLogExample();
        jobsLogExample5.createCriteria().andAccountEqualTo("fast").andStatusEqualTo("执行成功").andUserEqualTo(loginName);
        Integer successFast= jobsLogMapper.selectByExample(jobsLogExample5).size();

        JobsLogExample jobsLogExample6=new JobsLogExample();
        jobsLogExample6.createCriteria().andAccountEqualTo("fast").andStatusEqualTo("执行失败").andUserEqualTo(loginName);
        Integer failFast= jobsLogMapper.selectByExample(jobsLogExample6).size();

        TimeJobsExample timeJobsExample=new TimeJobsExample();
        timeJobsExample.createCriteria().andStatusEqualTo("running").andCreaterEqualTo(loginName);
        Integer runNum= timeJobsMapper.selectByExample(timeJobsExample).size();

        TimeJobsExample timeJobsExample2=new TimeJobsExample();
        timeJobsExample2.createCriteria().andStatusEqualTo("running").andCreaterEqualTo(loginName);
        Integer stopNum= timeJobsMapper.selectByExample(timeJobsExample2).size();


        Map<String,Integer> map=new HashMap<>();
         map.put("successFast",successFast);
         map.put("successJob",successJob);
         map.put("successTimeJob",successTimeJob);
         map.put("failFast",failFast);
         map.put("failJob",failJob);
         map.put("failTimeJob",failTimeJob);
         map.put("runNum",runNum);
         map.put("stopNum",stopNum);

        return TaotaoResult.ok(map);
    }


    /*查询所有的日志记录*/
    @Override
    public List<ExecuteLog> queryAllExecuteLog() {
        ExecuteLogExample example=new ExecuteLogExample();
        example.or();
        return executeLogMapper.selectByExample(example);
    }




}
