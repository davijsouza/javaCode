package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.entity.Scripts;
import com.souche.salt_common_01.service.AgentService;
import com.souche.salt_common_01.utils.HttpClientUtil;
import com.souche.salt_common_01.utils.IDUtils;
import com.souche.salt_common_01.utils.TaotaoResult;
import io.swagger.annotations.Api;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@Api(description = "Agent",tags = "Agent执行接口")
public class AgentController {
    @Autowired
    private AgentService agentService;
    @PostMapping("/lantu/salt/doShell")
    public TaotaoResult agentShell(String content, String vars, String account, List<String> ips) {


        return agentService.doshell( content,  vars,  account,  ips);
    }


    @PostMapping("/lantu/recivelog")
    public String recivelog(String stdout,String logId) {
         agentService.updateLog(stdout,logId);
        return null;
    }

    @PostMapping("/lantu/reciveCode")
    public String recivelog(String pid,String logId,String retcode) {
//        System.err.println(pid);
//        System.err.println(logId);
//        System.err.println(retcode);
        agentService.updateRetcode(pid,logId,retcode);
        return null;
    }


}
