package com.souche.salt_common_01.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.souche.salt_common_01.entity.*;
import com.souche.salt_common_01.mapper.ScriptServerMapper;
import com.souche.salt_common_01.mapper.ScriptVarMapper;
import com.souche.salt_common_01.mapper.ScriptsMapper;
import com.souche.salt_common_01.service.JobService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.IDUtils;
import com.souche.salt_common_01.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(description = "作业的CRUD",tags = {"job作业相关接口"})
public class JobController {
    @Autowired
    private JobService jobService;

     @Autowired
    private ScriptsService scriptsService;


    @PostMapping("/lantu/salt/job")
    @ApiOperation(value = "新增一个job")
    public String addJob(AddJob addJob){
        //System.err.println(addJob);
      //Boolean wheatherName=  jobService.existName(addJob.getJobName());
      Boolean wheatherName= true;
      if(wheatherName){
          Integer result=  jobService.addJob(addJob);
          return result==1?"success":"fail";
      }
        return "Job名重复";
    }



    @GetMapping("/lantu/salt/job")
    @ApiOperation(value = "查询所有公共job")
    public List<Job> queryJobs(String name,String creater,String lastmodifyuser,String startTime,String endTime){

        Map<String,String> param=new HashMap<>();
        if(name!=null && name !=""){
            param.put("name",name);
        }
        if(startTime!=null && startTime !=""){
            param.put("startTime",startTime);
        }
        if(creater!=null && creater !=""){
            param.put("creater",creater);
        }
        if(endTime!=null && endTime !=""){
            param.put("endTime",endTime);
        }
        if(lastmodifyuser!=null && lastmodifyuser !=""){
            param.put("lastmodifyuser",lastmodifyuser);
        }
        return jobService.queryAllJobs(param);
    }

    /*查询个人私有作业*/
    @GetMapping("/lantu/salt/priJob")
    @ApiOperation(value = "查询个人私有作业")
    public List<Job> findPrivateJob(String user,String name,String creater,String lastmodifyuser){
        System.err.println(user);
        return jobService.findPrivateJob(user,name,creater,lastmodifyuser);
    }



   /* @GetMapping("/lantu/salt/job/{id}")
    @ApiOperation(value = "根据jobid查询一个job")
    public SonJob queryJob(@PathVariable String id){

        return jobService.queryJob(id);
    }*/

    @GetMapping("/lantu/salt/jobAbout")
    @ApiOperation(value = "根据id查询一个job的脚本")
    public List<ScriptSon> jobAbout( @RequestParam String id){

        return jobService.queryJobScript(id);
    }



    @DeleteMapping("/lantu/salt/job/{id}")
    @ApiOperation(value = "删除一个job")
    public String deleteJobs(@PathVariable String id,
                             @RequestParam String user){

        Integer result= jobService.deleteJob(id,user);
        return result>0?"success":"fail";
    }




    @GetMapping("/lantu/salt/jobName")
    @ApiOperation(value = "判断job名是否存在")
    public Boolean existence(@RequestParam("name") String name){
        if(name==null ||name==""){
            return null;
        }
        return jobService.existName(name);
    }


    @DeleteMapping("/lantu/salt/delStep")
    @ApiOperation(value = "删除作业的某个步骤")
    public Boolean delStep( @RequestParam("id") String id


    ){
        String user=scriptsService.getLoginName();
        Integer step=jobService.delStep(id,user);

        return step>0?true:false;
    }

    @PutMapping("/lantu/salt/updateJobName")
    public Integer updateJobName(String jobId,String jobName){


        return jobService.updateJobName(jobId,jobName);
    }


/*


    @PutMapping("/lantu/salt/job")
    @ApiOperation(value = "更新一个job")
    public String updateJob(
                             @RequestParam("jobId") String jobId,
                             @RequestParam("lastModifyUser") String lastModifyUser,
                             @RequestParam("script")  String script){
        System.err.println(jobId);
        System.err.println(lastModifyUser);
        System.err.println(script);

        JSONArray jsonArray=JSONArray.parseArray(script);
        List<String> scriptIds=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObjectScript=JSONObject.parseObject(jsonArray.get(i).toString());
            System.err.println("jsonObjectScript=="+jsonObjectScript);
            //yes 表示是临时脚本 所以先添加脚本
            if("yes".equals(jsonObjectScript.get("hand").toString())){
                Scripts scripts=new Scripts();
                String scriptId="script_tmp"+new Date().getTime();
                scripts.setId(scriptId);
                */
    /*判断脚本名字是否存在如果存在的话稍作修改*//*

                String scriptName=jsonObjectScript.get("name").toString();
                scripts.setName(scriptName);
                scripts.setIdDeleted("tmp");
                scripts.setLastMofityUser(lastModifyUser);
                scripts.setIsCommon("yes");
                scripts.setCreater(lastModifyUser);
                scripts.setCreateTime(new Date());
                scripts.setLastModifyTime(new Date());
                scripts.setContent(jsonObjectScript.get("content").toString());
                scriptsMapper.insert(scripts);
                scriptIds.add(scriptId);
            }else{
                scriptIds.add(jsonObjectScript.get("id").toString());
            }
        }

        Job job=new Job();
        job.setId(jobId);
        job.setLastmodifyuser(lastModifyUser);

        Integer result= jobService.updateJob(job,scriptIds);
        return result==1?"success":"fail";

    }

*/

}
