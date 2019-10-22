package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.entity.Config;
import com.souche.salt_common_01.entity.ConfigWithBLOBs;
import com.souche.salt_common_01.service.NginxService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags ={"nginx相关接口"})
public class NginxController {

    @Autowired
    private NginxService nginxService;

    @GetMapping("/lantu/salt/config")
    public void initialize( ){
        nginxService.initialize( );
    }

  /*   @GetMapping("/lantu/salt/configQuery")
    public List<Map<String,Object>> queryConfig(
                                  String szoneName,
                                    String applicationName,
                                    String instanceName
    ){

         List<Map<String,Object>>  configs= nginxService.queryConfig(szoneName,applicationName,instanceName);
       return configs;
    }*/

   @PostMapping("/lantu/salt/instanceConf")
   public List<Map<String,Object>> getInstanceConf(String tableData){

       return   nginxService.getInstanceConf(tableData);
   }

   @PutMapping("/lantu/salt/config")
    public String updateConf(@RequestParam Integer id,
                              @RequestParam String  content,
                              @RequestParam String  operate

   ){


     return  nginxService.updateContent(id,content,operate);
   }

@PostMapping("/lantu/salt/config")
    public String addNginx(@RequestParam String instanceId,
                              @RequestParam String  content,
                              @RequestParam String  path,
                              @RequestParam String  name

   ){
  /*  System.err.println(instanceId);
    System.err.println(content);
    System.err.println(path);
    System.err.println(name);*/

     return  nginxService.addNginx(instanceId,name,content,path);
   }

   @GetMapping("/lantu/contentLike")
    public List<ConfigWithBLOBs> queryConfig(String content){


       return  nginxService.queryConf(content);
   }

   @PutMapping("/lantu/salt/disableFile")
    public Integer disableFile(Integer id,String ifreload){

    return   nginxService.disableFile(id,ifreload);

   }

    @PutMapping("/lantu/salt/effect")
    public Integer effect(Integer id){

        return   nginxService.effect(id);

    }



}
