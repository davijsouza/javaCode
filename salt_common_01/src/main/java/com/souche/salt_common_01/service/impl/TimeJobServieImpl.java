package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.controller.*;
import com.souche.salt_common_01.entity.*;
import com.souche.salt_common_01.mapper.*;
import com.souche.salt_common_01.service.ClientService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.service.TimeJobService;
import com.souche.salt_common_01.utils.DDMesssage;
import com.souche.salt_common_01.utils.HttpClientUtil;
import com.souche.salt_common_01.utils.IDUtils;
import com.souche.salt_common_01.utils.TaotaoResult;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TimeJobServieImpl  implements TimeJobService {
    @Value("${salt.api.url}")
    private   String SALT_API_URL;
    @Value("${saltUserName}")
    private  String saltUserName;
    @Value("${saltPassword}")
    private String saltPassword;



    @Autowired
    private ScriptsMapper scriptsMapper;


    @Autowired
    private TimeJobsMapper timeJobsMapper;
    @Autowired
    private ExecuteLogMapper executeLogMapper;
    @Autowired
    private JobsLogMapper jobsLogMapper;
    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private SctiptsController sctiptsController;
    @Autowired
    private ClientService clientService;

    @Autowired
    private ScriptsService scriptsService;
    @Autowired
    private HongmaoController hongmaoController;
    @Autowired
    private QuartzApiController quartzApiController;


  /*  @Autowired
    private ScriptsMapper scriptsMapper;
    @Autowired
    private TimeJobsMapper timeJobsMapper;
    @Autowired
    private ScriptVarMapper scriptVarMapper;*/
    @Autowired
    private ScriptVarController scriptVarController;
   @Autowired
    private ScriptServerController scriptServerController;
    @Autowired
    private MySqlMapper mySqlMapper;



    @Override
    public String addTimeJob(String jobName, String user, String script, String doTime, Integer totalNum, String cron) {
        TimeJobs timeJobs=new TimeJobs();
        String timeJob_ID="timeJob_ID"+ IDUtils.genItemId();

        timeJobs.setId(timeJob_ID);
        timeJobs.setName(jobName);
        timeJobs.setCreater(user);
        timeJobs.setCreateTime(new Date());
        timeJobs.setLastModifyTime(new Date());
        timeJobs.setDoTime(doTime);
        timeJobs.setCron(cron);
        timeJobs.setStay1("exist");
        timeJobs.setTotalNum(totalNum);
        timeJobs.setDoneNum(0);
        timeJobs.setStatus("running");
        //System.err.println(timeJobs);
        //新建job
        timeJobsMapper.insert(timeJobs);



            //新建job下的3个脚本
                System.err.println(script);
                JSONArray jsonArray =JSONArray.parseArray(script);
                for (int i= 0 ;i<jsonArray.size();i++){
                    Scripts scripts=new Scripts();
                    // 一条脚本的信息
                    Map scriptMap= (Map) jsonArray.get(i);

                    String script_ID="script_tJob"+IDUtils.genItemId();
                    String content  =scriptMap.get("content").toString();
                    String account  =scriptMap.get("account").toString();
                    String scriptName=scriptMap.get("name").toString();
                    String servers =scriptMap.get("servers").toString();
                    String vars=scriptMap.get("vars").toString();
                    //判断脚本名字是否重复
                    scripts.setName(scriptName);
                  /*  ScriptsExample example=new ScriptsExample();
                    example.or().andNameEqualTo(scriptName);
                    List<Scripts> scriptsList=scriptsMapper.selectByExample(example);
                    if (scriptsList==null||scriptsList.size()==0){
                        scripts.setName(scriptName);
                    }else{
                        *//*重名*//*
                        scripts.setName(scriptName+"_"+new Date().getTime());
                    }*/

                    scripts.setId(script_ID);
                    scripts.setContent(content);
                    scripts.setCreater(user);
                    scripts.setCreateTime(new Date());
                    scripts.setLastMofityUser(user);
                    scripts.setLastModifyTime(new Date());
                    scripts.setIsCommon("no");
                    scripts.setIdDeleted(timeJob_ID);
                    scripts.setOrderNum(i+1);
                    scriptsMapper.insert(scripts);//新建脚本完成


                    /*插入脚本绑定的变量*/
                    scriptVarController.addVar(script_ID,vars);
                    //插入脚本的执行的机器
                    scriptServerController.addServer(script_ID,account,servers);


        }

         return timeJob_ID;


    }

    @Override
    public void doJob(String jobID, String jobType) {
        String user=null;
        // jobtype : job   Tjob
        //user :使用者(job  的user 必须填写)
        String missionName = null;
        if("Tjob".equals(jobType)){
            TimeJobs timeJobs=timeJobsMapper.selectByPrimaryKey(jobID);
            missionName=timeJobs.getName();
            user=timeJobs.getCreater();
        }else if("job".equals(jobType)){
            Job job=jobMapper.selectByPrimaryKey(jobID);
            missionName=job.getName();
            user= scriptsService.getLoginName();
        }



        String SaltToken=clientService.getSaltToken();
        String JobLogId= "jobLogId_"+ IDUtils.genImageName();
        int orederNum=1;
        JobsLog jobsLog=new JobsLog();
        Date jobStart=new Date();
        //jobStatus 判断作业执行状态
        Boolean jobStatus =true;
        //查询出作业的脚本
        ScriptsExample scriptsExample=new ScriptsExample();
        scriptsExample.or().andIdDeletedEqualTo(jobID);
        scriptsExample.setOrderByClause("orderNum asc");
        List<Scripts> scriptsList=scriptsMapper.selectByExample(scriptsExample);
        for (int i=0;i<scriptsList.size();i++){
            //获得红猫接口的token
            String hmToken=hongmaoController.hmStableToken();
            String hongmaotoken="bearer "+hmToken;
            Map<String,String> headers=new HashMap<>();
            headers.put("Authorization",hongmaotoken);
            //获得脚本内容
            Scripts scripts=scriptsService.queryScript(scriptsList.get(i).getId());
            String content=scripts.getContent();
            //获得脚本相关机器呵变量
            Map<String, Object> scriptInfo=sctiptsController.getjobscript(scriptsList.get(i).getId());
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


           /* //脚本名
            String scriptFileName=""+new  Date().getTime();
            String target = "/srv/salt/" + scriptFileName + ".sh";
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
            }


            *//*拼接调用接口需要的参数*//*
            System.err.println("-------------");
            System.err.println(varsStr);
           // varsStr=null;
            System.err.println(instandidsStr);
           // instandidsStr="172.17.40.162";
            StringBuffer query = new StringBuffer();
            query.append("client=").append("local&");
            query.append("tgt=").append(instandidsStr).append("&");
            query.append("fun=cmd.script&");
            query.append("arg=").append("salt://" + scriptFileName + ".sh&");
            if (varsStr!=null){
                query.append("arg=").append(varsStr).append("&");
            }
            query.append("tgt_type=list&");
            query.append("arg=runas=").append(account);
            System.err.println("query========"+query.toString());
*/


            //执行脚本记录时间
            Date startTime =   new Date();
           // String scriptResults = RestUtil1.load(SALT_API_URL, query.toString(),SaltToken);
            String saltScriptId=""+new  Date().getTime();
            scripts.setSaltSname(saltScriptId);
            scriptsMapper.updateByPrimaryKeySelective(scripts);
            String scriptResults = clientService.doClient2(content,instandidsStr,account,varsStr,saltScriptId);
            Date endTime =   new Date();
            String totalTime=String.format("%.3f",(endTime.getTime()-startTime.getTime())*1.0/1000);

            JSONObject jsonObject = JSONObject.parseObject(scriptResults);
            String serversout = jsonObject.get("return").toString();
            JSONArray array = JSONArray.parseArray(serversout);
            Map<String, String> maps = (Map<String, String>) JSON.parse(array.get(0).toString());
            if (maps.size()==0){
                maps.put("server","Minion did not return. [No response]");
            }
            /*遍历返回结果   记录到executelog日志中*/
            /*记录executeLog*/
            ExecuteLog executeLog=new ExecuteLog();
            executeLog.setMissionname(scripts.getName());
            executeLog.setUser(user);
            executeLog.setAccount(account);
            executeLog.setIsjob(saltScriptId);
            executeLog.setJobid(JobLogId);
            executeLog.setOrdernum(orederNum);
            executeLog.setScriptid(scripts.getId());
            executeLog.setStarttime(startTime);
            executeLog.setEndtime(endTime);
            executeLog.setTotaltime(totalTime);

            for (Object map : maps.entrySet()) {

                String output=((Map.Entry) map).getValue().toString();
                String serverId=((Map.Entry) map).getKey().toString();
                if ("Minion did not return. [No response]".equals(output)){
                    System.err.println("没有返回值");
                    output="{\"stdout\":\"\",\"pid\":\"*\",\"stderr\":\"Minion did not return. [No response] 机器不存在或者未安装salt\",\"retcode\":1}";
                }

                /*判断状态吗看是否执行成功*/
                JSONObject retCodeOb=JSONObject.parseObject(output);
                String retcode=String.valueOf(retCodeOb.get("retcode"));
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
                    executeLog.setServer(serverId);
                }

                executeLog.setServerId(serverId);
                executeLog.setOutput(output);

                executeLogMapper.insertSelective(executeLog);
            }
            orederNum++;
        }
        Date jobEnd=new Date();
        String JobtotalTime=String.format("%.3f",(jobEnd.getTime()-jobStart.getTime())*1.0/1000);

        /*生成jobLog*/
        jobsLog.setId(JobLogId);
        jobsLog.setMissionname(missionName);
        jobsLog.setUser(user);
        jobsLog.setAccount(jobType);
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


    }

    @Override
    public Boolean ifTimeJobName(String timeJobName) {
        TimeJobsExample timeJobsExample=new TimeJobsExample();
        timeJobsExample.or().andNameEqualTo(timeJobName).andCreaterEqualTo(scriptsService.getLoginName());
        List<TimeJobs> timeJobs=timeJobsMapper.selectByExample(timeJobsExample);
        if(timeJobs!=null&& timeJobs.size()>0){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public List<TimeJobs> queryTimeJob() {
        TimeJobsExample timeJobsExample=new TimeJobsExample();
        timeJobsExample.setOrderByClause("createTime desc");
        timeJobsExample.or();
        return timeJobsMapper.selectByExample(timeJobsExample);
    }

    @Override
    public void startJob(String id, Integer totalNum) {

        TimeJobs timeJobs=timeJobsMapper.selectByPrimaryKey(id);
        if ("running".equals(timeJobs.getStatus())){
            return;
        }
        timeJobs.setTotalNum(totalNum);
        //timeJobs.setDoneNum(0);
        timeJobs.setStatus("running");

        timeJobsMapper.updateByPrimaryKeySelective(timeJobs);
        quartzApiController.startQuartzJob(id,timeJobs.getCron());

    }

    @Override
    public void stopJob(String id) {
        TimeJobs timeJobs=timeJobsMapper.selectByPrimaryKey(id);
        timeJobs.setStatus("stop");
        timeJobsMapper.updateByPrimaryKeySelective(timeJobs);
        quartzApiController.deleteJob(id,"group1");
    }

    @Override
    public String deleteJob(String id) {
       /* ScriptsExample scriptsExample=new ScriptsExample();
        scriptsExample.or().andIdDeletedEqualTo(id);
        List<Scripts> scriptsList=scriptsMapper.selectByExample(scriptsExample);
        for (Scripts scripts:scriptsList
             ) {
            sctiptsController.deletejobscript(scripts.getId());
        }*/
        quartzApiController.deleteJob(id,"group1");
        TimeJobs timeJobs=new TimeJobs();
        timeJobs.setId(id);
        timeJobs.setStay1("deleted");
        timeJobs.setStatus("stop");
        return  timeJobsMapper.updateByPrimaryKeySelective(timeJobs)>0?"success":"fail";

    }

    @Override
    public List<TimeJobs> timeJobBy(String name, String status, String jobId) {

        return timeJobsMapper.timeJobBy(name,status,scriptsService.getLoginName(),jobId);
    }

    @Override
    public Integer updateCron(String id, String doTime, String cron) {

        //quartzApiController.deleteJob(id,"group1");

         Integer result=   quartzApiController.startQuartzJob(id,cron);
        if(result>0){
            quartzApiController.deleteJob(id,"group1");
            TimeJobs timeJobs=new TimeJobs();
            timeJobs.setId(id);
            timeJobs.setDoTime(doTime);
            timeJobs.setCron(cron);
            return timeJobsMapper.updateByPrimaryKeySelective(timeJobs);
        }else {
            return 0;
        }





    }

    @Override
    public TimeJobs getTimeJobById(String jobId) {

        return timeJobsMapper.selectByPrimaryKey(jobId);
    }

    @Override
    public void dayFailNum() {

        List<JobsLog> logs=mySqlMapper.findFailJob();

        for (JobsLog jobsLog:logs
             ) {
           // System.err.println(jobsLog);
             try {
                //jobsLog.getMissionname()+"执行失败，执行人"+jobsLog.getUser()
                DDMesssage.TMessage("https://oapi.dingtalk.com/robot/send?access_token=579ae43300d58867b1025167a0be29fe1ae75ee9ac776f96ab8b9f698b2b80ba",
                        "https://lantu.souche-inc.com/#/executeLog/"+jobsLog.getMissionname(), "执行失败-----"+jobsLog.getUser(),jobsLog.getMissionname());

            } catch (ApiException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public TaotaoResult failJobs() {
        String loginName=scriptsService.getLoginName();
        List<JobsLog> logs=mySqlMapper.failJobs(loginName);

        return TaotaoResult.ok(logs);
    }


}
