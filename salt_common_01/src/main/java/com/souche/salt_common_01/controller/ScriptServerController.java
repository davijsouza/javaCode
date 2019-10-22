package com.souche.salt_common_01.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.entity.ScriptServer;
import com.souche.salt_common_01.entity.ScriptVar;
import com.souche.salt_common_01.service.ScriptServerService;
import com.souche.salt_common_01.service.ScriptVarSevice;
import io.swagger.annotations.Api;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"脚本的绑定要执行的机器"})
public class ScriptServerController {


    @Autowired
    private ScriptServerService scriptServerService;
    @PostMapping("/lantu/salt/addServers")
    public Integer addServer(String ScriptID,String account,String servers){


        return scriptServerService.addServer(ScriptID,account,servers);
    }


    /*返回list*/
  @GetMapping("/lantu/salt/getServers")
    public Map<String, Object> getServer(String ScriptID){


        return scriptServerService.getServer(ScriptID);
    }

    @GetMapping("/lantu/salt/delServers")
    public void delServer(String ScriptID){


         scriptServerService.delServer(ScriptID);
    }


}
