package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.controller.HongmaoController;
import com.souche.salt_common_01.controller.SctiptsController;
import com.souche.salt_common_01.entity.*;
import com.souche.salt_common_01.mapper.*;
import com.souche.salt_common_01.service.ClientService;
import com.souche.salt_common_01.service.NginxService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.DataUtil;
import com.souche.salt_common_01.utils.HttpClientUtil;
import com.souche.salt_common_01.utils.IDUtils;
import com.souche.salt_common_01.utils.RestUtil1;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.util.*;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ExecuteLogMapper executeLogMapper;
    @Autowired
    private JobsLogMapper jobsLogMapper;
    @Autowired
    private ScriptsService scriptsService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ScriptServerMapper scriptServerMapper;
   @Autowired
    private RunJobMapper runJobMapper;
  @Autowired
    private JobMapper jobMapper;
  @Autowired
  private SctiptsController sctiptsController;
  @Autowired
    private ScriptsMapper scriptsMapper;
    @Autowired
    private NginxService nginxService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static  String targetPath="/srv/salt/scripts/";
    @Autowired
    private HongmaoController hongmaoController;

    /*线上环境*/
    //  private static final String SALT_API_URL = "http://172.16.33.246:8000";
    /*测试环境*/
    //="http://172.17.40.126:8888";
    @Value("${salt.api.url}")
    private   String SALT_API_URL;
    @Value("${saltUserName}")
    private  String saltUserName;
    @Value("${saltPassword}")
    private String saltPassword;


    /*快速执行一个脚本
     *
     * 参数  日志
     *       服务器
     *        执行的脚本
     * */
    @Override
    public String doFastScripts(ExecuteLog executeLog,  String script) {

        /*线上登陆salt*/
        // String token= RestUtil1.load(SALT_API_URL+"/login","username=salt-api&password=OgyT6aDGwQSB&eauth=pam","aa");
       //测试环境登陆salt
         // String token= RestUtil1.load(SALT_API_URL+"/login","username=salt&password=salt&eauth=pam","aa");
        //获得token

        String hmtoken="bearer "+hongmaoController.hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",hmtoken);
        executeLog.setUser(scriptsService.getLoginName());
       /* String token=clientService.getSaltToken();
        //生成的脚本文件名
        String scriptFileName=""+new  Date().getTime();
        String target = targetPath + scriptFileName + ".sh";
        PrintWriter printWriter = null;
        *//*把脚本变成文件存在硬盘中*//*
        try {
            printWriter = new PrintWriter(new FileOutputStream(new File(target)), true);
            printWriter.println(script);
        } catch (FileNotFoundException e) {
            System.err.println("新建脚本  写出到磁盘失败");
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        *//*拼接调用接口需要的参数*//*
        StringBuffer query = new StringBuffer();
        query.append("client=").append("local&");
       // query.append("tgt=").append("172.17.40.162,172.17.40.194,172.17.40.129").append("&");
        query.append("tgt=").append(executeLog.getServer()).append("&");
        query.append("fun=cmd.script&");
        query.append("arg=").append("salt://" +scriptFileName+ ".sh&");
        query.append("tgt_type=list&");
        query.append("arg=runas=").append(executeLog.getAccount());

        System.err.println("query====="+query);

*/
        String saltScriptId=""+new  Date().getTime();
        Scripts scripts=new Scripts();
        String scriptID="scriptID"+IDUtils.genItemId();
        scripts.setId(scriptID);
        scripts.setName(executeLog.getMissionname());
        scripts.setContent(script);
        scripts.setCreater(scriptsService.getLoginName());
        scripts.setCreateTime(new Date());
        scripts.setLastMofityUser(scriptsService.getLoginName());
        scripts.setLastModifyTime(new Date());
        scripts.setIsCommon("no");
        scripts.setIdDeleted("yes");
        scripts.setOrderNum(1);
        scripts.setSaltSname(saltScriptId);
        scriptsMapper.insert(scripts);
        Date startTime =   new Date();
        String scriptResults=null;


        try{
            //scriptResults = RestUtil1.load(SALT_API_URL, query.toString(),token);
            scriptResults = doClient2(script,executeLog.getServer(),executeLog.getAccount(),null,saltScriptId);

        }catch (Exception e){
            System.err.println("salt执行脚本发生错误");
            e.printStackTrace();
        }
        Date endTime =   new Date();
        //*统计执行耗时*//*
        String totalTime=String.format("%.3f",(endTime.getTime()-startTime.getTime())*1.0/1000);
        executeLog.setStarttime(startTime);
        executeLog.setEndtime(endTime);
        executeLog.setTotaltime(totalTime);
        executeLog.setIsjob("no");
        executeLog.setOrdernum(1);
        executeLog.setScriptid(scriptID);
        String JobId= "joblogID_"+ IDUtils.genImageName();
        JSONObject jsonObject = JSONObject.parseObject(scriptResults);
        logger.info(scriptResults+"scriptResults-- ");
        String serversout = jsonObject.get("return").toString();
        JSONArray array = JSONArray.parseArray(serversout);

        Map<String, String> maps = (Map<String, String>) JSON.parse(array.get(0).toString());

        /*遍历返回结果   记录到executelog日志中*/
        //System.err.println(maps.size());
        if (maps.size()==0){
            maps.put("server","Minion did not return. [No response]");
        }
        Boolean jobStatus=true;
        for (Object map : maps.entrySet()) {
            String serverId=((Map.Entry) map).getKey().toString();
            String output=((Map.Entry) map).getValue().toString();
            /*判断状态吗看是否执行成功*/

            if ("Minion did not return. [No response]".equals(output)){
                System.err.println("没有返回值");
                output="{\"stdout\":\"\",\"pid\":\"*\",\"stderr\":\"Minion did not return. [No response] 机器不存在或者未安装salt\",\"retcode\":1}";
            }
            //System.err.println(output);
            JSONObject retCodeOb=JSONObject.parseObject(output);
            String retcode=String.valueOf(retCodeOb.get("retcode"));
            logger.info(retcode+"===retcode");
            if("0".equals(retcode)){
              executeLog.setStatus("执行成功");
            }else {
                jobStatus=false;
                executeLog.setStatus("执行失败");
            }
            Map<String,String> param=new HashMap<>();
            param.put("instanceId",serverId);
            String resultByName= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/resource/select",param,headers);
            Object instanceName=null;
            try{
                instanceName=JSONObject.parseObject(resultByName).get("instanceName");
            }catch (Exception e){
                System.err.println("没有根据instanceID找到name");
            }

            if (instanceName!=null){
                executeLog.setServer(instanceName.toString());
            }else{
                executeLog.setServer("*");
            }
            logger.info(instanceName+"---instanceName");

            executeLog.setServerId(serverId);
            executeLog.setOutput(output);
            executeLog.setJobid(JobId);
            executeLog.setIsjob(saltScriptId);
            executeLogMapper.insertSelective(executeLog);
        }

        JobsLog jobsLog=new JobsLog();
        jobsLog.setId(JobId);
        jobsLog.setMissionname(executeLog.getMissionname());
        jobsLog.setUser(executeLog.getUser());
        jobsLog.setAccount("fast");
        jobsLog.setServer("meicha");
        if (jobStatus) {
            jobsLog.setStatus("执行成功");
        }else {
            jobsLog.setStatus("执行失败");
        }
        jobsLog.setStarttime(startTime);
        jobsLog.setEndtime(endTime);
        jobsLog.setTotaltime(totalTime);
        jobsLogMapper.insert(jobsLog);
//    输出结果格式化
    /*    List<Map<String,String>> listForResult=new ArrayList<>();
        JSONObject ob= JSON.parseObject(scriptResults);
        JSONArray jsonarray = JSONArray.parseArray(ob.get("return").toString());
        Map<String, Map<String,String>> resultmap = (Map<String, Map<String,String>>) JSON.parse(jsonarray.get(0).toString());
        for (Map.Entry<String, Map<String,String>> entry:resultmap.entrySet()
             ) {
            Map<String,String> instanceMap=new HashMap<>();
            //System.err.println();
            instanceMap.put("instanceId",entry.getKey());
            for (Map.Entry<String, String> entry2:entry.getValue().entrySet()
            ) {
                instanceMap.put(entry2.getKey(),String.valueOf(entry2.getValue()));
            }
            listForResult.add(instanceMap);
        }
*/


         return JSON.toJSONString(DataUtil.saltResult(scriptResults,headers));

        //return  scriptResults;
    }
   @Override
    public String doFastScriptsAgent(String missionname, String account, List<String> ips, String script, String scriptID) {
        /* //虹猫token
        String hmtoken="bearer "+hongmaoController.hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",hmtoken);*/
        String user=scriptsService.getLoginName();
       //生成脚本

       Scripts scripts=new Scripts();

       scripts.setId(scriptID);
       scripts.setName(missionname);
       scripts.setContent(script);
       scripts.setCreater(user);
       scripts.setCreateTime(new Date());
       scripts.setLastMofityUser(user);
       scripts.setLastModifyTime(new Date());
       scripts.setIsCommon("no");
       scripts.setIdDeleted("yes");
       scripts.setOrderNum(1);
       scriptsMapper.insert(scripts);

       Date startTime =   new Date();
       //生成job日志
       String JobId= "joblogID_"+ IDUtils.genImageName();
       JobsLog jobsLog=new JobsLog();
       jobsLog.setId(JobId);
       jobsLog.setMissionname(missionname);
       jobsLog.setUser(user);
       jobsLog.setAccount("fast");
       jobsLog.setServer("meicha");
       jobsLog.setStatus("执行成功");
       jobsLog.setStarttime(startTime);
       //jobsLog.setEndtime(endTime);
       // jobsLog.setTotaltime(totalTime);
       jobsLogMapper.insert(jobsLog);

       //生成日志
        ExecuteLog executeLog=new ExecuteLog();
        executeLog.setMissionname(missionname);
        executeLog.setAccount(account);
        executeLog.setUser(user);
       executeLog.setStarttime(startTime);
       //executeLog.setEndtime(endTime);
       //executeLog.setTotaltime(totalTime);
       executeLog.setIsjob("no");
       executeLog.setStatus("执行成功");
       executeLog.setOrdernum(1);
       executeLog.setScriptid(scriptID);
       //executeLog.setServerId(serverId);
       executeLog.setJobid(JobId);


       Map<String,String> params=new HashMap<>();
       params.put("content",script);
       //变量需要处理成空格
       //
       ////
      List<String> resutlist=new ArrayList<>();
       for (String ip:ips
       ) {
           String  logId= IDUtils.genImageName();
           params.put("logId",logId);
           params.put("account",account);

           executeLog.setServer(logId);
           executeLog.setServerId(ip);
           executeLogMapper.insertSelective(executeLog);
           String result= HttpClientUtil.doPost("http://"+ip+":9999/agent/shell",params);
           resutlist.add(result);
       }
       // Date endTime =   new Date();
        //*统计执行耗时*//*
        //String totalTime=String.format("%.3f",(endTime.getTime()-startTime.getTime())*1.0/1000);


        //JSONObject jsonObject = JSONObject.parseObject(scriptResults);
        //logger.info(scriptResults+"scriptResults-- ");
        //String serversout = jsonObject.get("return").toString();
       // JSONArray array = JSONArray.parseArray(serversout);

        //Map<String, String> maps = (Map<String, String>) JSON.parse(array.get(0).toString());

        /*遍历返回结果   记录到executelog日志中*/
        //System.err.println(maps.size());
       /* if (maps.size()==0){
            maps.put("server","Minion did not return. [No response]");
        }
        Boolean jobStatus=true;
        for (Object map : maps.entrySet()) {
            String serverId=((Map.Entry) map).getKey().toString();
            String output=((Map.Entry) map).getValue().toString();
            *//*判断状态吗看是否执行成功*//*

            if ("Minion did not return. [No response]".equals(output)){
                System.err.println("没有返回值");
                output="{\"stdout\":\"\",\"pid\":\"*\",\"stderr\":\"Minion did not return. [No response] 机器不存在或者未安装salt\",\"retcode\":1}";
            }
            //System.err.println(output);
            JSONObject retCodeOb=JSONObject.parseObject(output);
            String retcode=String.valueOf(retCodeOb.get("retcode"));
            logger.info(retcode+"===retcode");
            if("0".equals(retcode)){
              executeLog.setStatus("执行成功");
            }else {
                jobStatus=false;
                executeLog.setStatus("执行失败");
            }
            Map<String,String> param=new HashMap<>();
            param.put("instanceId",serverId);
            String resultByName= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/resource/select",param,headers);
            Object instanceName=null;
            try{
                instanceName=JSONObject.parseObject(resultByName).get("instanceName");
            }catch (Exception e){
                System.err.println("没有根据instanceID找到name");
            }

            if (instanceName!=null){
                executeLog.setServer(instanceName.toString());
            }else{
                executeLog.setServer("*");
            }
            logger.info(instanceName+"---instanceName");

            executeLog.setServerId(serverId);
            executeLog.setOutput(output);
            executeLog.setJobid(JobId);
            executeLog.setIsjob(saltScriptId);
            executeLogMapper.insertSelective(executeLog);
        }*/


//    输出结果格式化
    /*    List<Map<String,String>> listForResult=new ArrayList<>();
        JSONObject ob= JSON.parseObject(scriptResults);
        JSONArray jsonarray = JSONArray.parseArray(ob.get("return").toString());
        Map<String, Map<String,String>> resultmap = (Map<String, Map<String,String>>) JSON.parse(jsonarray.get(0).toString());
        for (Map.Entry<String, Map<String,String>> entry:resultmap.entrySet()
             ) {
            Map<String,String> instanceMap=new HashMap<>();
            //System.err.println();
            instanceMap.put("instanceId",entry.getKey());
            for (Map.Entry<String, String> entry2:entry.getValue().entrySet()
            ) {
                instanceMap.put(entry2.getKey(),String.valueOf(entry2.getValue()));
            }
            listForResult.add(instanceMap);
        }
*/


         return JSON.toJSONString(resutlist);

        //return  scriptResults;
    }




    @Override
    public  String getSaltToken(){
        String token=RestUtil1.load(SALT_API_URL+"/login","username="+saltUserName+"&password="+saltPassword+"&eauth=pam","aa");
        JSONObject ob= JSON.parseObject(token);
        JSONArray jsonarray = JSONArray.parseArray(ob.get("return").toString());
        Map<String, String> resultmap = (Map<String, String>) JSON.parse(jsonarray.get(0).toString());

        return resultmap.get("token");
    }

    @Override
    public String doClient(String content, String servers, String account,String vars) {

        String token=clientService.getSaltToken();
        //生成的脚本文件名
        String scriptFileName=""+new  Date().getTime();
        String target = targetPath + scriptFileName + ".sh";
        PrintWriter printWriter = null;
        /*把脚本变成文件存在硬盘中*/
        try {
            printWriter = new PrintWriter(new FileOutputStream(new File(target)), true);
            printWriter.println(content);
        } catch (FileNotFoundException e) {
            System.err.println("新建脚本  写出到磁盘失败");
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        /*拼接调用接口需要的参数*/
        StringBuffer query = new StringBuffer();
         query.append("client=").append("local&");

        // query.append("tgt=").append("172.17.40.134").append("&");
        query.append("tgt=").append(servers).append("&");

        query.append("fun=cmd.script&");
        query.append("arg=").append("salt://scripts/" +scriptFileName+ ".sh&");
        if (vars!=null){
            query.append("arg=").append(vars).append("&");
        }
        query.append("tgt_type=list&");
        query.append("arg=runas=").append(account);
        log.info("query====="+query);
        String scriptResults = RestUtil1.load(SALT_API_URL, query.toString(),token);
        log.info("scriptResults===="+scriptResults);
        return scriptResults;
    }


    @Override
    public String doClient2(String content, String servers, String account,String vars,String saltScriptId) {



        String token=clientService.getSaltToken();
        //生成的脚本文件名
        String scriptFileName=saltScriptId;
        String target = targetPath + saltScriptId + ".sh";
        PrintWriter printWriter = null;
        /*把脚本变成文件存在硬盘中*/
        try {
            printWriter = new PrintWriter(new FileOutputStream(new File(target)), true);
            printWriter.println(content);
        } catch (FileNotFoundException e) {
            System.err.println("新建脚本  写出到磁盘失败");
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        /*拼接调用接口需要的参数*/
        StringBuffer query = new StringBuffer();
        query.append("client=").append("local&");

        // query.append("tgt=").append("172.17.40.134").append("&");
        query.append("tgt=").append(servers).append("&");

        query.append("fun=cmd.script&");
        query.append("arg=").append("salt://scripts/" +scriptFileName+ ".sh&");
        if (vars!=null){
            query.append("arg=").append(vars).append("&");
        }
        query.append("tgt_type=list&");
        query.append("arg=runas=").append(account);
        query.append("&arg=user=").append(scriptsService.getLoginName());
        log.info("query====="+query);
        String scriptResults = RestUtil1.load(SALT_API_URL, query.toString(),token);
        log.info("scriptResults===="+scriptResults);
        return scriptResults;
    }
//分步执行作业
    @Override
    public String runJobStep(String runJobId, String mode) {
        //System.err.println(runJobId+mode);
        String hmtoken="bearer "+hongmaoController.hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",hmtoken);
        RunJob runJob=runJobMapper.selectByPrimaryKey(runJobId);
        Job job=jobMapper.selectByPrimaryKey(runJob.getJobId());
       // System.err.println(jobsLogQuery);
                //如果第0步 则先新建jobLog
                if(runJob.getStep()==0){
                    JobsLog jobsLog=new JobsLog();
                    jobsLog.setId(runJob.getJobLogId());
                    jobsLog.setMissionname(job.getName());
                    jobsLog.setUser(scriptsService.getLoginName());
                    jobsLog.setAccount("job");
                    jobsLog.setServer("*");
                    jobsLog.setStatus("执行成功");
                    jobsLog.setStarttime(new Date());
                    jobsLog.setEndtime(new Date());
                    jobsLog.setTotaltime("0");
                    jobsLogMapper.insertSelective(jobsLog);
                }
                JobsLog jobsLogQuery=jobsLogMapper.selectById(runJob.getJobLogId());
                //更新runjob 执行中
                int step=0;
                String scriptID=null;
                if(!"disable".equals(runJob.getStep1())){
                      step=1;
                     scriptID=runJob.getStep1();
                     runJob.setStep1("disable");

                }else if(!"disable".equals(runJob.getStep2())){
                    step=2;
                    scriptID=runJob.getStep2();
                    runJob.setStep2("disable");
                }else if(!"disable".equals(runJob.getStep3())){
                    step=3;
                    scriptID=runJob.getStep3();
                    runJob.setStep3("disable");
                }else {
                    runJob.setMode("finish");
                    runJobMapper.updateByPrimaryKey(runJob);
                    return "disable";
                }
                runJob.setStep(step);
                runJob.setStepStatus("running");
                runJobMapper.updateByPrimaryKey(runJob);
                //根据jobId 和step 查询脚本
                try{
                //得到脚本 但是有可能查询为空
                Scripts scriptsForId=scriptsMapper.selectByPrimaryKey(scriptID);
                Map<String,Object> scriptInfo=sctiptsController.getjobscript(scriptID);
                //脚本变量
                List<String> vars= (List<String>) scriptInfo.get("vars");
                String varsStr="";
                if(vars!=null && vars.size()>0){
                    for (String var:vars
                    ) {
                        varsStr+=var+" ";
                    }
                }else{
                    varsStr=null;
                }
                Map servers= (Map) scriptInfo.get("servers");
                //脚本执行账户
                String account= (String) servers.get("account");
                //脚本执行机器
                List<String> instandIds= (List<String>) servers.get("instanceIds");
                //去重
                List<String> sigleids=new ArrayList<>();
                for (String str : instandIds) {
                    if (!sigleids.contains(str)) {
                        sigleids.add(str);
                    }
                }
                instandIds.clear();
                instandIds.addAll(sigleids);
                String instandidsStr="";
                if (instandIds!=null && instandIds.size()>0){
                    for (int k=0;k<instandIds.size();k++){
                        if(k==instandIds.size()-1){
                            instandidsStr+=instandIds.get(k);
                        }else{
                            instandidsStr+=instandIds.get(k)+",";
                        }
                    }
                }
                Date startTime =   new Date();
                 String saltScriptId=""+new  Date().getTime();
                 //脚本字段记录 salt生成的脚本名
                 scriptsForId.setSaltSname(saltScriptId);
                 scriptsMapper.updateByPrimaryKeySelective(scriptsForId);
                String scriptResults = clientService.doClient2(scriptsForId.getContent(),instandidsStr,account,varsStr,saltScriptId);
                Date endTime =   new Date();
                String JobtotalTime=String.format("%.3f",(endTime.getTime()-jobsLogQuery.getStarttime().getTime())*1.0/1000);
                String steptotalTime=String.format("%.3f",(endTime.getTime()-startTime.getTime())*1.0/1000);

                ExecuteLog executeLog=new ExecuteLog();
                //生成日志插入
                executeLog.setStarttime(startTime);
                executeLog.setEndtime(endTime);
                executeLog.setTotaltime(steptotalTime);
                executeLog.setIsjob(saltScriptId);
                executeLog.setOrdernum(step);
                executeLog.setMissionname(scriptsForId.getName());
                executeLog.setUser(scriptsService.getLoginName());
                executeLog.setAccount(account);
                executeLog.setJobid(runJob.getJobLogId());
                executeLog.setScriptid(scriptsForId.getId());

                JSONObject jsonObject = JSONObject.parseObject(scriptResults);
                logger.info(scriptResults+"scriptResults-- ");
                String serversout = jsonObject.get("return").toString();
                JSONArray array = JSONArray.parseArray(serversout);
                Map<String, String> maps = (Map<String, String>) JSON.parse(array.get(0).toString());
                    if (maps.size()==0){
                        maps.put("server","Minion did not return. [No response]");
                    }
              //  遍历返回结果   记录到executelog日志中
                Boolean jobStatus=true;
                for (Object map : maps.entrySet()) {
                    String serverId=((Map.Entry) map).getKey().toString();
                    String output=((Map.Entry) map).getValue().toString();
                    if ("Minion did not return. [No response]".equals(output)){
                        System.err.println("没有返回值");
                        output="{\"stdout\":\"\",\"pid\":\"*\",\"stderr\":\"Minion did not return. [No response] 机器不存在或者未安装salt\",\"retcode\":1}";
                    }
               //     判断状态吗看是否执行成功
                    JSONObject retCodeOb=JSONObject.parseObject(output);
                    String retcode=String.valueOf(retCodeOb.get("retcode"));
                    logger.info(retcode+"===retcode");
                    if("0".equals(retcode)){
                        executeLog.setStatus("执行成功");
                    }else {
                        jobStatus=false;
                        executeLog.setStatus("执行失败");
                    }

                    Map<String,String> param=new HashMap<>();
                    param.put("instanceId",serverId);
                    String resultByName= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/resource/select",param,headers);

                    Object instanceName=null;
                    try{
                        instanceName=JSONObject.parseObject(resultByName).get("instanceName");
                    }catch (Exception e){
                        System.err.println("没有根据instanceID找到name");
                    }

                    if (instanceName!=null){
                        executeLog.setServer(instanceName.toString());
                    }else{
                        executeLog.setServer(serverId);
                    }
                    //logger.info(instanceName+"---instanceName");

                    executeLog.setServerId(serverId);
                    executeLog.setOutput(output);
                    executeLogMapper.insertSelective(executeLog);
                }

                //判断执行成功和失败
                if (jobStatus) {
                    jobsLogQuery.setStatus("执行成功");
                }else {
                    jobsLogQuery.setStatus("执行失败");
                }
                //更新joblog
                jobsLogQuery.setTotaltime(JobtotalTime);
                jobsLogQuery.setEndtime(endTime);
                JobsLogExample jobsLogExample=new JobsLogExample();
                jobsLogExample.or().andIdEqualTo(jobsLogQuery.getId());
                jobsLogMapper.updateByExample(jobsLogQuery,jobsLogExample);
                //更新runjob
                runJob.setStepStatus("stop");
              /*  if(step==runJob.getTotalStep()){
                    runJob.setFinish("yes");
                    DDMesssage.TMessage("https://oapi.dingtalk.com/robot/send?access_token=180af836f7e0b4652eb2f4e50f32fc7c57e6b965d987efbf40748ca159b833c7",
                            "https://lantu.souche-inc.com/#/executeLog",job.getName()+ "执行完毕");

                }else {
                    DDMesssage.TMessage("https://oapi.dingtalk.com/robot/send?access_token=180af836f7e0b4652eb2f4e50f32fc7c57e6b965d987efbf40748ca159b833c7",
                            "https://lantu.souche-inc.com/#/executeLog",job.getName()+"的第"+step+"个步骤执行完毕");
                }*/

                 if("false".equals(scriptsForId.getPause())){
                     runJobMapper.updateByPrimaryKey(runJob);
                     runJobStep(runJobId, mode);
                 }else {
                     if (runJob.getStep1().equals("disable")&&runJob.getStep2().equals("disable")&&runJob.getStep3().equals("disable")){
                         runJob.setMode("finish");
                     }
                     runJobMapper.updateByPrimaryKey(runJob);
                 }
                return scriptResults;
                }catch (Exception e){
                    runJob.setStepStatus("stop");
                    runJobMapper.updateByPrimaryKey(runJob);
                    e.printStackTrace();
                    return "执行异常中断";
                }





    }

    @Override
    public String allMche(String script) {
        String allData= hongmaoController.hmSelectAllJiqi();
        JSONArray allDataArray=JSONArray.parseArray(allData);
        List<Map<String,String>> results=new ArrayList<>();
        // for (int i=0;i<10;i++) {
         for (int i=0;i<allDataArray.size();i++) {
            //从红猫获得一台机器的信息
            Map<String, String> result=new HashMap<>();
            Map<String, String> oneInfo = (Map<String, String>) allDataArray.get(i);
            String instanceId = oneInfo.get("instanceId");
            String saltResult = clientService.doClient(script, instanceId, "souche", null);
            String stdout = nginxService.getSingleClientResult(instanceId, saltResult);
            result.put(instanceId,stdout);
            results.add(result);
        }
        return JSON.toJSONString(results);
    }

    @Override
    public String cpsalt(String content, String servers, String account,String vars) {

          account="souche";
            String token=clientService.getSaltToken();
            //生成的脚本文件名
           /* String scriptFileName=""+new  Date().getTime();
            String target = targetPath + scriptFileName + ".sh";
            PrintWriter printWriter = null;
            *//*把脚本变成文件存在硬盘中*//*
            try {
                printWriter = new PrintWriter(new FileOutputStream(new File(target)), true);
                printWriter.println(content);
            } catch (FileNotFoundException e) {
                System.err.println("新建脚本  写出到磁盘失败");
                e.printStackTrace();
            } finally {
                printWriter.close();
            }*/
            /*拼接调用接口需要的参数*/
            StringBuffer query = new StringBuffer();
            query.append("client=").append("local&");

            query.append("tgt=").append("172.17.40.134").append("&");
            //query.append("tgt=").append(servers).append("&");

            query.append("fun=cp.push&");
            query.append("arg=").append("/tmp/cpsalt&");

            query.append("tgt_type=list&");
            query.append("arg=runas=").append(account);
            log.info("query====="+query);
            String scriptResults = RestUtil1.load(SALT_API_URL, query.toString(),token);
            log.info("scriptResults===="+scriptResults);
            return scriptResults;


    }


    /*执行作业  已经废弃*/

    @Override
    public List<String> doJob(String server, List<String> ids, String user, String name, String account, String variable) {


        /*线上环境*/
        //  String token= RestUtil1.load(SALT_API_URL+"/login","username=salt-api&password=OgyT6aDGwQSB&eauth=pam","aa");
        //测试环境登陆salt
        //String token= RestUtil1.load(SALT_API_URL+"/login","username=salt&password=salt&eauth=pam","aa");
        String token=clientService.getSaltToken();
        //获得token
        String hmtoken="bearer "+hongmaoController.hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",hmtoken);

        /*解析全局变量*/
        Map<String,String> globleVar=(Map<String,String>)JSON.parse(variable);
        /*存放每条脚本的返回结果*/
        List<String> results=new ArrayList<>();
        String JobId= "jobId_"+IDUtils.genImageName();
        int orederNum=1;
        JobsLog jobsLog=new JobsLog();
        Date jobStart=new Date();
        //jobStatus 判断作业执行状态
        Boolean jobStatus =true;
        for (String id:ids
        ) {
            /*把一条脚本生成 .sh 文件存在指定路径中*/
            Scripts scripts=scriptsService.queryScript(id);

            /*查询脚本的变量*/
            List<String> paramVar=scripts.getVars();
            StringBuffer stringBuffer=new StringBuffer();
            for (String var :paramVar
            ) {
                stringBuffer.append(var).append(" ");
            }
            //穿脚本要执行的instanceId
            StringBuffer instanceIds=new StringBuffer();
            ScriptServerExample scriptServerExample=new ScriptServerExample();
            scriptServerExample.or().andScriptIdEqualTo(id);
            List<ScriptServer> scriptServers=scriptServerMapper.selectByExample(scriptServerExample);
            for (int i=0;i<scriptServers.size();i++){
                if(i==scriptServers.size()-1){
                    instanceIds.append(scriptServers.get(i).getInstanceId());
                    //要执行的账户
                    account=scriptServers.get(i).getAccount();
                }else{
                    instanceIds.append(scriptServers.get(i).getInstanceId()).append(",");
                }
            }
            //脚本名
            String scriptFileName=""+new  Date().getTime();
            String target = targetPath + scriptFileName + ".sh";
            PrintWriter printWriter = null;
            /*把脚本变成文件存在硬盘中*/
            try {
                printWriter = new PrintWriter(new FileOutputStream(new File(target)), true);
                String scriptContent=scripts.getContent();

                /*替换全局变量*/
                if(globleVar!=null){
                    for (Map.Entry<String,String> entry:globleVar.entrySet()
                    ) {
                        scriptContent= scriptContent.replace("$"+entry.getKey(),entry.getValue());
                    }
                }
                printWriter.println(scriptContent);
            } catch (FileNotFoundException e) {
                System.err.println("新建脚本  写出到磁盘失败");
                e.printStackTrace();
            } finally {
                printWriter.close();
            }

            /*拼接调用接口需要的参数*/
            StringBuffer query = new StringBuffer();
            query.append("client=").append("local&");
             query.append("tgt=").append(instanceIds.toString()).append("&");
            //query.append("tgt=").append(instanceIds.toString()).append("&");
            query.append("fun=cmd.script&");
            query.append("arg=").append("salt://" + scriptFileName + ".sh&");
            query.append("arg=").append(stringBuffer.toString()).append("&");
            query.append("tgt_type=list&");
            query.append("arg=runas=").append(account);



            System.err.println("query========"+query.toString());

            //执行脚本记录时间
            Date startTime =   new Date();
            String scriptResults = RestUtil1.load(SALT_API_URL, query.toString(),token);

            String value= scriptResults;
            String regexp = "\"";
            value = value.replaceAll(regexp, "");
            System.err.println("scriptResults==="+value);
            Date endTime =   new Date();
            results.add(value);
            //*统计执行耗时*//*
            String totalTime=String.format("%.3f",(endTime.getTime()-startTime.getTime())*1.0/1000);

            JSONObject jsonObject = JSONObject.parseObject(scriptResults);
            String serversout = jsonObject.get("return").toString();
            JSONArray array = JSONArray.parseArray(serversout);
            Map<String, String> maps = (Map<String, String>) JSON.parse(array.get(0).toString());
            /*遍历返回结果   记录到executelog日志中*/
            /*记录executeLog*/
            ExecuteLog executeLog=new ExecuteLog();
            executeLog.setMissionname(scripts.getName());
            executeLog.setUser(user);
            executeLog.setAccount(account);
            executeLog.setIsjob("yes");
            executeLog.setJobid(JobId);
            executeLog.setOrdernum(orederNum);
            executeLog.setScriptid(scripts.getId());
            executeLog.setStarttime(startTime);
            executeLog.setEndtime(endTime);
            executeLog.setTotaltime(totalTime);

            for (Object map : maps.entrySet()) {

                String output=((Map.Entry) map).getValue().toString();
                String serverId=((Map.Entry) map).getKey().toString();


                /*判断状态吗看是否执行成功*/
                JSONObject retCodeOb=JSONObject.parseObject(output);
                String retcode=String.valueOf(retCodeOb.get("retcode"));
                logger.info(retcode+"===retcode");
                if("0".equals(retcode)){
                    executeLog.setStatus("执行成功");
                }else {
                    jobStatus=false;
                    executeLog.setStatus("执行失败");
                }
                //根据机器instanceName 查询istanceId
                Map<String,String> param=new HashMap<>();
                param.put("instanceId",serverId);
                String resultByName= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/resource/select",param,headers);
                JSONObject instanceName=JSONObject.parseObject(resultByName);
                if (instanceName!=null){
                    executeLog.setServer(instanceName.get("instanceName").toString());
                }else{
                    executeLog.setServer("*");
                }
                logger.info(instanceName+"---instanceName");
                executeLog.setServerId(serverId);
                executeLog.setOutput(output);

                executeLogMapper.insertSelective(executeLog);
            }
            orederNum++;
        }
        Date jobEnd=new Date();
        String JobtotalTime=String.format("%.3f",(jobEnd.getTime()-jobStart.getTime())*1.0/1000);

        /*生成jobLog*/
        jobsLog.setId(JobId);
        jobsLog.setMissionname(name);
        jobsLog.setUser(user);
        jobsLog.setAccount("fast");
        jobsLog.setServer("*");
        if (jobStatus) {
            jobsLog.setStatus("执行成功");
        }else {
            jobsLog.setStatus("执行失败");
        }
        jobsLog.setStarttime(jobStart);
        jobsLog.setEndtime(jobEnd);
        jobsLog.setTotaltime(JobtotalTime);
        try{
            jobsLogMapper.insert(jobsLog);
        }catch (Exception e){
            System.err.println("执行作业新建job日志失败");
            e.printStackTrace();
        }
        return results;
    }

}







