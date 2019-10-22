package com.souche.salt_common_01.service;

import com.souche.salt_common_01.entity.Scripts;
import com.souche.salt_common_01.utils.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ScriptsService {
    /*新增一条脚本*/
    String addScript(Scripts scripts, List<String> vars);
    /*删除一条脚本*/
    Boolean deleteScript(String id, String user);
    /*修改更新一条脚本*/
    Boolean updateScript(Scripts scripts, List<String> vars, String account, String servers);
    /*查询一条脚本*/
     Scripts queryScript(String id);
    /*查询所有的scripts展示在页面*/
    List<Scripts> queryAllScript(Map<String, String> param);

    /*判断执行日志是否重名 即作业名 或者快速执行名*/
    String isExistName(String name);

    List<Scripts> queryPriScript(String user, String scriptName, String creater);

    String addJobScript(String scriptID, String account, String servers, String vars);

    Map<String, Object> getJobScript(String scriptID);

    String delJobScript(String scriptID,String user);

    String getLoginName();

    TaotaoResult getCurrentScripts();
}
