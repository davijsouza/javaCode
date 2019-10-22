package com.souche.salt_common_01.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.controller.SctiptsController;
import com.souche.salt_common_01.entity.*;
import com.souche.salt_common_01.mapper.JobMapper;
import com.souche.salt_common_01.mapper.ScriptServerMapper;
import com.souche.salt_common_01.mapper.ScriptVarMapper;
import com.souche.salt_common_01.mapper.ScriptsMapper;
import com.souche.salt_common_01.service.JobService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class JobServiceImpl implements JobService {


    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private ScriptsMapper scriptsMapper;
    @Autowired
    private ScriptServerMapper scriptServerMapper;
    @Autowired
    private ScriptVarMapper scriptVarMapper;
    @Autowired
    private ScriptsService scriptsService;
    @Autowired
    private SctiptsController sctiptsController;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    /*新增job
    接收参数 脚本id  和job创建者*/
    @Override
    public Integer addJob(AddJob addJob) {
        addJob.setUser(scriptsService.getLoginName());

        try{
            Job job = new Job();
        String jobId = "jobID_" + IDUtils.genImageName();
        job.setId(jobId);
        job.setCreatetime(new Date());
        job.setLastmodifytime(new Date());
        job.setLastmodifyuser(addJob.getUser());
        job.setCreater(addJob.getUser());
        job.setScriptid("exist");
        job.setIsCommon(addJob.getIsCommon());
        job.setName(addJob.getJobName());
        Integer jobRow=jobMapper.insert(job);//新建作业完成


        JSONArray jsonArray = JSONArray.parseArray(addJob.getScript());

        for (int i = 0; i < jsonArray.size(); i++) {

            /*作业的第i条脚本*/
            JSONObject jsonObjectScript = JSONObject.parseObject(jsonArray.get(i).toString());
            /*每条脚本绑定的服务器*/
            Object scriptServers = jsonObjectScript.get("servers");

            /*每条脚本的变量*/
            Object vars = jsonObjectScript.get("vars");
            //服务器转成数组
            JSONArray serversArr = JSONArray.parseArray(scriptServers.toString());
            /*设置脚本相关信息新建脚本*/
            String account = (String) jsonObjectScript.get("account");
            Scripts scripts = new Scripts();
            String scriptId = "script_job" + new Date().getTime();
            scripts.setId(scriptId);
            /*判断脚本名字是否存在如果存在的话---稍作修改---*/
            String scriptName = jsonObjectScript.get("name").toString();
            String pause=jsonObjectScript.get("pause").toString();
            scripts.setName(scriptName);
           /* ScriptsExample example = new ScriptsExample();
            example.or().andNameEqualTo(scriptName);
            List<Scripts> scriptsList = scriptsMapper.selectByExample(example);
            if (scriptsList == null || scriptsList.size() == 0) {
                scripts.setName(scriptName);
            } else {
                *//*重名*//*
                scripts.setName(scriptName + "_" + new Date().getTime());
            }*/
            scripts.setIdDeleted(jobId);
            scripts.setLastMofityUser(addJob.getUser());
            scripts.setIsCommon("job");
            scripts.setPause(pause);
            scripts.setCreater(addJob.getUser());
            scripts.setCreateTime(new Date());
            scripts.setLastModifyTime(new Date());
            scripts.setContent(jsonObjectScript.get("content").toString());
            scripts.setIdDeleted(jobId);
            scripts.setOrderNum(i+1);
            scriptsMapper.insert(scripts);//新建脚本完成

            /*插入脚本绑定的服务器*/
            ScriptServer scriptServer = new ScriptServer();
            scriptServer.setScriptId(scriptId);
            scriptServer.setAccount(account);
            for (int j = 0; j < serversArr.size(); j++) {

                Map map = (Map) JSONObject.parse(serversArr.get(j).toString());
                scriptServer.setInstanceId(map.get("instanceId").toString());
                scriptServer.setSzoneName(map.get("publicAddress").toString());
                scriptServer.setSdoMainName(map.get("privateAddress").toString());
                scriptServer.setProductLineName(map.get("innerAddress").toString());
                 scriptServer.setProductName("null");
                 scriptServer.setApplicationName("null");
                scriptServer.setInstanceName(map.get("instanceName").toString());
                scriptServerMapper.insert(scriptServer);

            }
            /*插入脚本绑定的变量*/
            if (vars != null && vars != "") {
                ScriptVar scriptVar = new ScriptVar();
                scriptVar.setScriptId(scriptId);
                String[] varArr = ((String) vars).split(",");
                for (int j = 0; j < varArr.length; j++) {
                    scriptVar.setVar(varArr[j]);
                    scriptVar.setSort(j + 1);
                    scriptVarMapper.insert(scriptVar);
                        }
                    }
                }
        return jobRow;

        }catch (Exception e){
            e.printStackTrace();
            return  0;
        }

}

    @Override
    public List<Job> queryAllJobs(Map<String, String> param) {

        List<Job> jobs=jobMapper.queryAlljobs(param);
        for (Job j:jobs
             ) {
            ScriptsExample scriptsExample=new ScriptsExample();
            scriptsExample.or().andIdDeletedEqualTo(j.getId());
            Integer num=scriptsMapper.selectByExample(scriptsExample).size();
            j.setSort(String.valueOf(num));

        }



        return jobs;
    }
    /*查询私有作业*/
    @Override
    public List<Job> findPrivateJob(String user, String name, String creater, String lastmodifyuser) {
        if(user==null||user.equals("")){
            user=scriptsService.getLoginName();
        }
        Map<String,String> param=new HashMap<>();
        param.put("user",user);
        param.put("name",name);
       // param.put("creater",creater);
        param.put("lastmodifyuser",lastmodifyuser);

        List<Job> jobs=jobMapper.queryPrijobs(param);
        for (Job j:jobs
        ) {
            ScriptsExample scriptsExample=new ScriptsExample();
            scriptsExample.or().andIdDeletedEqualTo(j.getId());
            Integer num=scriptsMapper.selectByExample(scriptsExample).size();
            j.setSort(String.valueOf(num));

        }
        return jobs;


    }

    @Override
    public List<ScriptSon> queryJobScript(String id) {
        List<ScriptSon> list= jobMapper.jobScripts(id);
        for (ScriptSon script: list
             ) {

            //每条脚本的变量
            ScriptVarExample scritVarexample=new ScriptVarExample();
            scritVarexample.or().andScriptIdEqualTo(script.getId());
            List<ScriptVar> scriptVars=scriptVarMapper.selectByExample(scritVarexample);
            List<String> varsLsit=new ArrayList<>();

            if (scriptVars!=null && scriptVars.size()>0){

                for (int i=0;i<scriptVars.size();i++){
                    varsLsit.add(scriptVars.get(i).getVar());
                }
            }
            script.setVars(varsLsit);

            //存放每条服务器重要的信息
             List<Map<String,String>> scriptServerInfo= new ArrayList<>();
             ScriptServerExample example=new ScriptServerExample();
             example.or().andScriptIdEqualTo(script.getId());
             /*查询脚本绑定的服务器*/
             List<ScriptServer> scriptServers= scriptServerMapper.selectByExample(example);
             //如果查到服务器则将账户 和服务器赋值给
            System.err.println();
             if (scriptServers!=null && scriptServers.size()>0){
                 /*赋值账户*/
                 script.setAccount(scriptServers.get(0).getAccount());
                 //赋值其他信息
             for (ScriptServer scriptServer:scriptServers
                 ) {
                // System.err.println(scriptServer.getInstanceName());
                 Map<String,String> mapserver=new HashMap<>();
                 mapserver.put("szoneName",scriptServer.getSzoneName());
                 mapserver.put("sdoMainName",scriptServer.getSdoMainName());
                 mapserver.put("productLineName",scriptServer.getProductLineName());
                 mapserver.put("productName",scriptServer.getProductName());
                 mapserver.put("applicationName",scriptServer.getApplicationName());
                 mapserver.put("instanceName",scriptServer.getInstanceName());
                 mapserver.put("instanceId",scriptServer.getInstanceId());

                 scriptServerInfo.add(mapserver);

            }}
             script.setServers(scriptServerInfo);

        }
        return list;
    }

    @Override
    public Integer delStep(String id, String user) {
        //删除job表中某条记录 根据scriptID
        user=scriptsService.getLoginName();
        ScriptsExample scriptsExample=new ScriptsExample();
        scriptsExample.or().andCreaterEqualTo(user).andIdEqualTo(id);
        Integer rows=scriptsMapper.deleteByExample(scriptsExample);
        if (rows>0){
            //步骤删除成功
        sctiptsController.deletejobscript(id);
        }else{
            System.err.println("创建人不是你");

        }

         return rows;
    }

    @Override
    public Integer updateJobName(String jobId, String jobName) {
       Job job=new Job();
       job.setId(jobId);
       job.setName(jobName);
       return jobMapper.updateByPrimaryKeySelective(job);
    }

    @Override
    public Integer deleteJob(String id, String user) {
        try{
            user=scriptsService.getLoginName();
         Job job=jobMapper.selectByPrimaryKey(id);
         if(job!=null&&job.getCreater().equals(user)){

          /*删除job时 同时删除job相关的脚本
          * 删除脚本相关的服务器
          * 删除脚本相关的变量
          * */

          /*  ScriptsExample scriptsExample=new ScriptsExample();
            scriptsExample.or().andIdDeletedEqualTo(id);
            List<Scripts> scriptsList=scriptsMapper.selectByExample(scriptsExample);
             for (Scripts s:scriptsList
                  ) {
                 sctiptsController.deletejobscript(s.getId());
             }*/
            job.setScriptid("deleted");
            return  jobMapper.updateByPrimaryKeySelective(job);
        }else {
            return 0;
        }

        }catch (Exception e){
      logger.info("捕获异常，事物会滚");
        return 0;
        }


    }
    /*
    *
    * */
    @Override
    public Integer updateJob(Job job, List<String> scriptIds) {

        JobExample example=new JobExample();
      example.or().andIdEqualTo(job.getId());
      List<Job> jobs=jobMapper.selectByExample(example);
      Job oldJob=jobs.get(0);

      job.setLastmodifytime(new Date());
      job.setCreatetime(oldJob.getCreatetime());
      job.setCreater(oldJob.getCreater());
      job.setName(oldJob.getName());
      job.setIsCommon(oldJob.getIsCommon());

      /*先删除再插入*/
      jobMapper.deleteByPrimaryKey(oldJob.getId());
      int i=1;
      try {
          for (String id:scriptIds ) {
              job.setSort(String.valueOf(i));
              i++;
              job.setScriptid(id);

              jobMapper.insert(job);
          }

          return 1;
      }catch (Exception e){
          System.err.println("更新job失败");
          e.printStackTrace();
          return 0;
      }

    }

    @Override
    public SonJob queryJob(String id) {

       SonJob sonJob= jobMapper.queryJob(id);
      if(sonJob==null){
          System.err.println("根据id没查到job");
          return null;
      }

      return  sonJob;
    }

    @Override
    public Boolean existName(String name) {
        JobExample example=new JobExample();
        example.or().andNameEqualTo(name).andCreaterEqualTo(scriptsService.getLoginName());
        List<Job> jobs=jobMapper.selectByExample(example);
        if (jobs==null||jobs.size()<=0){
            return true;
        }
        return false;
    }


}
