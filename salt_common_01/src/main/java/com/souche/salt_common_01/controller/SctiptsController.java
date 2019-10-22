package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.entity.ScriptServer;
import com.souche.salt_common_01.entity.Scripts;
import com.souche.salt_common_01.entity.ScriptsExample;
import com.souche.salt_common_01.mapper.ScriptsMapper;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.TaotaoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "脚本的CRUD",tags = {"脚本相关接口"})
@RestController
public class SctiptsController {



    @Autowired
    private ScriptsService scriptsService;
    @Autowired
    private ScriptsMapper scriptsMapper;

    @PostMapping("/lantu/salt/script/addScript")
    @ApiOperation(value = "新增一条脚本")
    public String add(
                @RequestParam String name,
                @RequestParam String content,
                @RequestParam String isCommon,
                @RequestParam(required = false)  List<String> vars
    ){

        String creater=scriptsService.getLoginName();
        Scripts scripts=new Scripts();
        scripts.setContent(content);
        scripts.setCreater(creater);
        scripts.setIsCommon(isCommon);
        scripts.setName(name);
        return    scriptsService.addScript(scripts,vars);

    }



    @PostMapping("/lantu/salt/script/addJobScript")
    @ApiOperation(value = "新增一条作业绑定的脚本后，绑定server和vars")
    public String addjobscript(
            String ScriptID,String account,String servers,String vars
    ){

        return    scriptsService.addJobScript(ScriptID,account,servers,vars);

    }

    @GetMapping("/lantu/salt/script/getobScript")
    @ApiOperation(value = "查询一条作业绑定的脚本")
    public Map<String,Object> getjobscript(
            String ScriptID
    ){

        return    scriptsService.getJobScript(ScriptID);

    }
    @ApiOperation(value = "删除一条脚本")
    @GetMapping(value = "/lantu/salt/script/deleteScript")
    public Boolean delete(@RequestParam String id
                         // @RequestParam String user
    ){
        String user=scriptsService.getLoginName();
        if(id==null||user==null){
            System.err.println("删除一条脚本，参数接受不全");
            return false;
        }
        return   scriptsService.deleteScript(id,user);

    }


    @DeleteMapping("/lantu/salt/script/deleteJobScript")
    @ApiOperation(value = "删除一条作业绑定的脚本")
    public String deletejobscript(
            String ScriptID
    ){
        String user=scriptsService.getLoginName();
        return    scriptsService.delJobScript(ScriptID,user);

    }


    /*更新一条脚本*/
    @PostMapping("/lantu/salt/script/updateScript")
    @ApiOperation(value = "更新一条脚本")
    public Boolean update(@RequestParam String id,
                          @RequestParam(required = false)String name,
                          @RequestParam(required = false)String content,
                         // @RequestParam(required = false) String lastmofityuser,
                          @RequestParam(required = false)  List<String> vars,
                          @RequestParam(required = false) String servers,
                          @RequestParam(required = false)  String account,
                                     String pause

                          ){
        System.err.println(pause);
        String lastmofityuser=scriptsService.getLoginName();
       // String lastmofityuser="admin";
        Scripts oldscripts=scriptsMapper.selectByPrimaryKey(id);
        if(oldscripts.getCreater().equals(lastmofityuser)){
            Scripts scripts = new Scripts();
            scripts.setId(id);
            scripts.setName(name);
            scripts.setContent(content);
            scripts.setLastMofityUser(lastmofityuser);
            scripts.setPause(pause);
            return scriptsService.updateScript(scripts, vars, account, servers);
        } else {
        return false;
    }
    }
    /*根据Id查询一条脚本*/
    @GetMapping("/lantu/salt/script/queryScript")
    @ApiOperation(value = "查询一条脚本")
    public  Scripts query(String id){
         return scriptsService.queryScript(id);

    }
    /*查询所有公共脚本*/
    @GetMapping("/lantu/salt/script/queryAllScript")
    @ApiOperation(value = "条件查询所有公共脚本")
    public List<Scripts> queryAll(String scriptName,
       String creater,String jobName,  String startTime,String endTime) {
        Map<String,String> param=new HashMap<>();
        param.put("scriptName",scriptName);
        param.put("creater",creater);
        param.put("jobName",jobName);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
       return    scriptsService.queryAllScript(param);

    }
    /*查询个人私有脚本*/
    @GetMapping("/lantu/salt/priScript")
    @ApiOperation(value = " 查询个人私人脚本")
    public List<Scripts> findPrivateScript(String scriptName,
                                           String creater
                                           ){
       if(creater==null||"".equals(creater)){
           creater=scriptsService.getLoginName();
       }
        return scriptsService.queryPriScript(creater,scriptName,creater);
    }





    /*判断执行日志密名字missionName是否存在*/
    @GetMapping("/lantu/salt/script/existName")
    @ApiOperation(value = "判断执行日志名字是否存在")
    public String existName(String name){
        return    scriptsService.isExistName(name);


    }
    @GetMapping("/lantu/salt/loginName")
    public String getloginName(){
        return    scriptsService.getLoginName();
    }

    @GetMapping("/lantu/salt/currentScripts")
    public TaotaoResult currentScripts(){
        return    scriptsService.getCurrentScripts();
    }

    @GetMapping("/lantu/salt/getBysaltScriptId")
    public TaotaoResult getBysaltScriptId(List<String> saltSids){
        System.err.println(saltSids.size()+"====");
        return    null;
    }
}




