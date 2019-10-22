package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.souche.salt_common_01.controller.ScriptServerController;
import com.souche.salt_common_01.controller.ScriptVarController;
import com.souche.salt_common_01.entity.*;
import com.souche.salt_common_01.mapper.*;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.IDUtils;
import com.souche.salt_common_01.utils.TaotaoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class ScriptServiceimpl implements ScriptsService {



    @Autowired
    private ScriptsMapper scriptsMapper;
    @Autowired
    private CurrentMapper currentMapper;
    @Autowired
    private ExecuteLogMapper executeLogMapper;
    @Autowired
    private ScriptVarMapper scriptVarMapper;
    @Autowired
    private ScriptServerMapper scriptServerMapper;
    @Autowired
    private ScriptVarController scriptVarController;
    @Autowired
    private ScriptServerController scriptServerController;


    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private  ScriptsService scriptsService;

    @Autowired
    private  HttpServletRequest request;


    /*
    *  新增一个小脚本
    * 并给加入创建时间，
    * 修改时间，
    * id （UUID）
    *
    * */
    @Override
    public String addScript(Scripts scripts, List<String> vars) {
        try{

        /*判断脚本名是否已经存在*/
        ScriptsExample example=new ScriptsExample();
        example.or().andNameEqualTo(scripts.getName());
        List<Scripts> scriptsList=scriptsMapper.selectByExample(example);
        /*如果已经存在直接返回false*/
        if (scriptsList==null||scriptsList.size()==0){

            scripts.setCreateTime(new Date());
            scripts.setLastModifyTime(new Date());
            scripts.setLastMofityUser(scripts.getCreater());
            String scriptId="scriptID_"+ IDUtils.genItemId();
            System.err.println("scriptId==="+scriptId);
            scripts.setId(scriptId);
            scripts.setIdDeleted("no");
            int result=scriptsMapper.insert(scripts);
            /*插入脚本变量*/
            if(vars!=null){
                ScriptVar scriptVar=new ScriptVar();
                scriptVar.setScriptId(scriptId);
                int i=1;
                for (String var:vars
                ) {
                    scriptVar.setVar(var);
                    scriptVar.setSort(i);
                    i++;
                    scriptVarMapper.insert(scriptVar);
                }

            }

            return result==1?"true":"false";


        }else{
             return "repeat";
        }

        }catch (Exception e){
            logger.info("捕获异常 事物回滚 添加失败");
         return  "false";
        }


    }

    /*
    *
    *  根据ID删除一条脚本
    * */
    @Override
    public Boolean deleteScript(String id, String user) {
        try{
        Scripts oldScripts=scriptsMapper.selectByPrimaryKey(id);
        if(oldScripts != null &&
                oldScripts.getCreater().equals(user) &&
                        oldScripts.getIdDeleted().equals("no")  ){

            Scripts scripts=new Scripts();
            scripts.setId(id);
            scripts.setIdDeleted("yes");
            scripts.setName(
                    scriptsService.queryScript(id).getName()+"_del_"+new Date().getTime());
            Boolean result= scriptsService.updateScript(scripts, null, null, null);
           //只是状态改变为del  所以绑定的变量不需要删除
            // scriptVarController.delVar(id);

            return result;
        }
       return false;
        }catch (Exception e){
            System.err.println("捕获异常，事物回滚，删除脚本失败");
            return  false;
        }
    }

    /*
    * 修改一条脚本
    * 修改最后更新时间
    * */

    @Override
    public Boolean updateScript(Scripts scripts, List<String> vars, String account, String servers) {
     try {
         scripts.setLastModifyTime(new Date());

         int result = scriptsMapper.updateByPrimaryKeySelective(scripts);

         if (vars != null) {
             /*删除原来的脚本变量*/
             ScriptVarExample example = new ScriptVarExample();
             example.or().andScriptIdEqualTo(scripts.getId());
             scriptVarMapper.deleteByExample(example);

             /*重新插入脚本变量*/
             ScriptVar scriptVar = new ScriptVar();
             scriptVar.setScriptId(scripts.getId());
             int i = 1;
             for (String var : vars
             ) {
                 scriptVar.setVar(var);
                 scriptVar.setSort(i);
                 i++;
                 scriptVarMapper.insert(scriptVar);
             }

         }

         /*更新绑定的服务器*/
         if (servers != null) {
             //删除原脚本ID 相关的服务器
             ScriptServerExample example = new ScriptServerExample();
             example.or().andScriptIdEqualTo(scripts.getId());
             scriptServerMapper.deleteByExample(example);
             //重新插入

             JSONArray jsonArray = JSONArray.parseArray(servers);
             ScriptServer scriptServer = new ScriptServer();
             scriptServer.setScriptId(scripts.getId());
             scriptServer.setAccount(account);
             for (int i = 0; i < jsonArray.size(); i++) {
                 Map<String, String> map = (Map<String, String>) jsonArray.get(i);
                 scriptServer.setInstanceId(map.get("instanceId"));
                 scriptServer.setSzoneName(map.get("szoneName"));
                 scriptServer.setSdoMainName(map.get("sdoMainName"));
                 scriptServer.setProductLineName(map.get("productLineName"));
                 scriptServer.setProductName(map.get("productName"));
                 scriptServer.setApplicationName(map.get("applicationName"));
                 scriptServer.setInstanceName(map.get("instanceName"));
                 scriptServer.setInstanceId(map.get("instanceId"));
                 scriptServerMapper.insert(scriptServer);
             }


         }

         return result == 1 ? true : false;
     }catch (Exception e){
         System.err.println("捕获异常 事物回滚 更新失败");
         return false;
     }
    }


    /*
    *
    * 根据ID查询一条脚本
    * */
    @Override
    public  Scripts queryScript(String id) {
      /*查询脚本*/
        Scripts scripts=scriptsMapper.selectByPrimaryKey(id);
        if(scripts==null){
            System.err.println("根据脚本id没找到var");
            return  null;
        }
        /*查询脚本绑定的变量*/
        List<String> vars=new ArrayList<>();
        ScriptVarExample  example=new ScriptVarExample();
        example.or().andScriptIdEqualTo(id);
        List<ScriptVar> scriptVars=scriptVarMapper.selectByExample(example);
        for (ScriptVar scriptVar:scriptVars
             ) {
          vars.add(scriptVar.getVar());
        }
         scripts.setVars(vars);
      return scripts;
    }
    /*
     *
     * 查询所有脚本
     * */
    @Override
    public List<Scripts> queryAllScript(Map<String, String> param) {
        System.err.println("param脚本查询参数=="+param);
        List<Scripts> scripts=scriptsMapper.findAllScripts(param);
        for (Scripts script:scripts
             ) {
             List<String> vars=new ArrayList<>();
            ScriptVarExample  example=new ScriptVarExample();
            example.or().andScriptIdEqualTo(script.getId());
            List<ScriptVar> scriptVars=scriptVarMapper.selectByExample(example);
            for (ScriptVar scriptVar:scriptVars
            ) {
                vars.add(scriptVar.getVar());
            }
            script.setVars(vars);
        }
        return scripts;
    }

    @Override
    public String isExistName(String name) {
        ExecuteLogExample example=new ExecuteLogExample();
        example.or().andMissionnameEqualTo(name);

        List<ExecuteLog> list=executeLogMapper.selectByExample(example);

        if(list.size()==0||list==null){
            return "true";
        }else{
            return "false";
        }

    }

    @Override
    public List<Scripts> queryPriScript(String user, String scriptName, String creater) {

        List<Scripts> scripts=scriptsMapper.queryPriScript(user,scriptName);
        for (Scripts script:scripts
        ) {
            List<String> vars=new ArrayList<>();
            ScriptVarExample  example=new ScriptVarExample();
            example.or().andScriptIdEqualTo(script.getId());
            List<ScriptVar> scriptVars=scriptVarMapper.selectByExample(example);
            for (ScriptVar scriptVar:scriptVars
            ) {
                vars.add(scriptVar.getVar());
            }
            script.setVars(vars);
        }
        return scripts;

    }

    @Override
    public String addJobScript(String scriptID, String account, String servers, String vars) {
        try{
            scriptServerController.addServer(scriptID,account,servers);
            scriptVarController.addVar(scriptID,vars);
            return "true";
        }catch (Exception e){

            e.printStackTrace();
            return "false";
        }

    }

    @Override
    public Map<String, Object> getJobScript(String scriptID) {
        Map<String, Object> map=new HashMap<>();
        List<String> vars=scriptVarController.getVar(scriptID);
        Map<String, Object> servers=scriptServerController.getServer(scriptID);
        if(vars!=null){
            map.put("vars",vars);
        }
        if(servers!=null){
            map.put("servers",servers);
        }


        return map;
    }

    @Override
    public String delJobScript(String scriptID,String user) {


        try{
            Scripts scripts=scriptsMapper.selectByPrimaryKey(scriptID);
            if(scripts!=null&&scripts.getCreater().equals(user)){
                scriptServerController.delServer(scriptID);
                scriptVarController.delVar(scriptID);
                scriptsMapper.deleteByPrimaryKey(scriptID);
            }

        return null;

        }catch (Exception e){
            System.err.println("捕获异常 回滚  删除作业的脚本失败");
        return null;
        }
    }
//获得loginName

    @Override
    public String getLoginName() {

        String username=null;
        try {
            username=request.getSession().getAttribute("username").toString();
        }catch (Exception e){
            username="error";
         //e.printStackTrace();
        }

        return username;
    }

    @Override
    public TaotaoResult getCurrentScripts() {
        CurrentExample example=new CurrentExample();
        List<Current> currents=currentMapper.selectByExample(example);
        return TaotaoResult.ok(currents);
    }
}
