package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.entity.ExecuteLog;
import com.souche.salt_common_01.entity.Job;
import com.souche.salt_common_01.entity.SonExecuteLog;
import com.souche.salt_common_01.entity.SonJob;
import com.souche.salt_common_01.service.ClientService;
import com.souche.salt_common_01.service.JobService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.service.TimeJobService;
import com.souche.salt_common_01.utils.HttpClientUtil;
import com.souche.salt_common_01.utils.IDUtils;
import com.souche.salt_common_01.utils.RestUtil1;
import com.suse.salt.netapi.results.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@Api(description = "快速执行，作业执行",tags = "salt执行接口")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ScriptsService scriptsService;
    @Autowired
    private TimeJobService timeJobService;

    /*
  *
  *快速执行一个脚本
  *
  * 接收参数是实体SonexcuteLog 其中包含excuteLog   servers  script
  *返回值是 map封装返回结果
  *
  */
    @ApiOperation(value = "快速执行脚本")
    @PostMapping("/lantu/salt/client/script/fast")
    public  String fastScript( @RequestParam("missionname") String missionname,
                               //@RequestParam("user") String user,
                               @RequestParam("account") String account,
                               @RequestParam("server") String server,
                               @RequestParam("script")   String script

             ){
        String user=scriptsService.getLoginName();
        ExecuteLog executeLog=new ExecuteLog();
        executeLog.setServer(server);
        executeLog.setMissionname(missionname);
        executeLog.setAccount(account);
        executeLog.setUser(user);

        String  result=clientService.doFastScripts(
                executeLog,script);
        return result;

    }

    @PostMapping("/lantu/salt/fastAgent")
    public  String fastScriptAgent(
                              @RequestParam("missionname") String missionname,
                               //@RequestParam("user") String user,
                               @RequestParam("account") String account,
                               @RequestParam("ips") List<String> ips,
                               @RequestParam("script")   String script,
                               @RequestParam("scriptId")   String scriptId

    ){


        String  result=clientService.doFastScriptsAgent(missionname,account,ips,script,scriptId);
        return result;

    }


    /*执行作业
    *
    * 参数：
    * jobId
    *传入一个id 则整个作业全部执行完毕
    *
    * */
    @PostMapping("/lantu/salt/client")
    @ApiOperation(value = "执行作业")
    public  void jobScript(
              @RequestParam("id")String id

    ){
       timeJobService.doJob(id,"job");

    }


    @PostMapping("/lantu/salt/jobStep")
    @ApiOperation(value = "分步执行作业")
    public  String jobStep(
            @RequestParam("runJobId")String runJobId,
            @RequestParam("mode")String mode

    ){

    return clientService.runJobStep(runJobId,mode);
    }

    @PostMapping("/lantu/salt/allMche")
    @ApiOperation(value = "所有机器")
    public  String allMche(
            @RequestParam("script")String script


    ){
      return clientService.allMche(script);
    }

    @GetMapping("/cpsalt")
    public void cpsalt(){
        clientService.cpsalt("","","","");
    }

}
