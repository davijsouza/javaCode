package com.souche.salt_common_01.service;

import com.souche.salt_common_01.entity.ExecuteLog;

import java.util.List;

public interface ClientService {
    /*快速执行脚本*/
    String doFastScripts(ExecuteLog executeLog, String script);
    String doFastScriptsAgent(String missionname, String account, List<String> ips, String script, String scriptId);
    /*执行作业*/
    List<String> doJob(String server, List<String> ids, String user, String name, String account, String variable);

    String getSaltToken();

    String  doClient(String content,String  servers,String account ,String vars);
    String  doClient2(String content,String  servers,String account ,String vars,String saltScriptId);


    String runJobStep(String runJobId, String mode);

    String allMche(String script);

    String cpsalt(String content, String servers, String account,String vars);
}
