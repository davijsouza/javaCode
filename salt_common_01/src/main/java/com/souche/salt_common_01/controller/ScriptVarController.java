package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.entity.Rewrite;
import com.souche.salt_common_01.entity.ScriptVar;
import com.souche.salt_common_01.entity.ScriptVarExample;
import com.souche.salt_common_01.service.RewriteService;
import com.souche.salt_common_01.service.ScriptVarSevice;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"脚本的绑定变量"})
public class ScriptVarController {

    @Autowired
    private ScriptVarSevice scriptVarSevice;
    @PostMapping("/lantu/salt/addVar")
    public Integer addVar(String ScriptID,String vars){
        if(vars!=null||vars!=""){
            String[] varArr= ((String) vars).split(",");
            return scriptVarSevice.addVar(ScriptID,varArr);
        }else{
            return  0;
        }


    }

    @GetMapping("/lantu/salt/getVar")
    public List<String> getVar(String ScriptID) {



      return  scriptVarSevice.getScriptVars(ScriptID);
    }

    @GetMapping("/lantu/salt/delVar")
    public void delVar(String ScriptID) {



          scriptVarSevice.delScriptVars(ScriptID);
    }



}
