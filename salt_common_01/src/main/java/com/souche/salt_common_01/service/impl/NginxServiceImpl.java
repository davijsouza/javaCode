package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.controller.HongmaoController;
import com.souche.salt_common_01.entity.Config;
import com.souche.salt_common_01.entity.ConfigExample;
import com.souche.salt_common_01.entity.ConfigWithBLOBs;
import com.souche.salt_common_01.mapper.ConfigMapper;
import com.souche.salt_common_01.service.ClientService;
import com.souche.salt_common_01.service.NginxService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.DataUtil;
import com.souche.salt_common_01.utils.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class NginxServiceImpl implements NginxService {
    @Autowired
    private HongmaoController hongmaoController;
   @Autowired
    private ConfigMapper configMapper;
   @Autowired
   private ScriptsService scriptsService;

    @Autowired
    private ClientService clientService;
    @Override
    public void initialize() {
        log.info("come 初始化");
        String allData= hongmaoController.hmSelectAllJiqi();
        JSONArray allDataArray=JSONArray.parseArray(allData);
        //for (int i=0;i<1;i++){
               for (int i=0;i<allDataArray.size();i++){
            //从红猫获得一台机器的信息
            Map<String,String> oneInfo= (Map<String,String>)allDataArray.get(i);
            String instanceId=oneInfo.get("instanceId");
             //String instanceId="i-bp171edtwst96k23qc3f";
            //去数据库查询此机器下的配置文件
            List<String> dbConfPath=new ArrayList<>();
            ConfigExample configExample=new ConfigExample();
            configExample.or().andInstanceIdEqualTo(instanceId);
            List<ConfigWithBLOBs> dbConfigs= configMapper.selectByExampleWithBLOBs(configExample);


            //从salt获取一台机器下的配置信息
             String etcConfigs=clientService.doClient( "ls /home/souche | grep nginx",instanceId,"souche",null);
             String etcStdout=getSingleClientResult(instanceId,etcConfigs);
             String saltConfigs=null;
             try{
                 if(etcStdout.contains("nginx")){
                     saltConfigs=clientService.doClient("find /home/souche/nginx/conf/ -maxdepth 1 -regex '.*conf$' -or -regex '.*list$' -or -regex '.*host$' && find /home/souche/nginx/conf/apps -maxdepth 1 -regex '.*conf$' -or -regex '.*list$' -or -regex '.*host$'",instanceId,"souche",null);
                 }else{
                     saltConfigs=clientService.doClient("find /etc/nginx -regex '.*conf$' ",instanceId,"souche",null);
                 }
             }catch (Exception e){
                 log.info("etcStdout==null");
                 continue;
             }
            log.info("salt输出  "+saltConfigs);
            String stdout=getSingleClientResult(instanceId,saltConfigs);
            if(stdout==null||stdout==""){
                log.info("salt返回为空");
                continue;
            }
            log.info("配置文件名 未分割  "+saltConfigs);
            //远程机器下文配置文件名
            String[]  saltConfigNamesArr=stdout.split("\\r?\\n");
            List<String> saltConfigNames= Arrays.asList(saltConfigNamesArr);
            //遍历dbConfigs
            for (int j=0;j<dbConfigs.size();j++){
                log.info(dbConfigs.get(j).getConfigContent());
                //收集此机器下的所有文件
                dbConfPath.add(dbConfigs.get(j).getConfigPath());
                //判断这个文件是否存在于salt机器里
                if(saltConfigNames.contains(dbConfigs.get(j).getConfigPath())){
                    //远端存在这个文件 则对比内容
                    String configContentResult=clientService.doClient("cat "+dbConfigs.get(j).getConfigPath(),instanceId,"souche",null);
                    String saltConfigContent=getSingleClientResult(instanceId,configContentResult);
                    //判断本地和远程 同一个文件是否相同
                    if(!dbConfigs.get(j).getConfigContent().equals(saltConfigContent.replaceAll("[\\n]","<br/>"))){
                        //不相同 则更新
                        log.info("远程变动更新文件");
                        log.info("更新文件");

                       // System.err.println ("更新文件");
                        //未记录历史版本
                         String text=saltConfigContent.replaceAll("[\\n]","<br/>");
                         dbConfigs.get(j).setConfigContent(text);
                        try{
                            //configMapper.updateByPrimaryKey(dbConfigs.get(j));
                            updateDBContent(dbConfigs.get(j).getId(),dbConfigs.get(j).getConfigContent());
                        }catch (Exception e){
                            log.info("更新文件失败 机器"+instanceId+"文件"+dbConfigs.get(j).getConfigName());
                            e.printStackTrace();
                        }
                    }


                }else{
                    //远端不存在此文件 则数据库也删除
                    log.info("delete");
                    configMapper.deleteByPrimaryKey(dbConfigs.get(j).getId());
                }
            }

            //saltConfigNames
            for (int k=0;k<saltConfigNames.size();k++){
                String filePath= saltConfigNames.get(k);
                if(!dbConfPath.contains(filePath)){
                    //同步此文件到本地
                    ConfigWithBLOBs configWithBLOBs=new ConfigWithBLOBs();
                    configWithBLOBs.setInstanceId(instanceId);
                    configWithBLOBs.setConfigPath(filePath);

                    try{
                        configWithBLOBs.setConfigName(filePath.substring(filePath.indexOf("nginx")));
                    }catch (Exception e){
                        if(filePath.length()>100){
                            configWithBLOBs.setConfigName(filePath.substring(0,99));
                        }else {
                            configWithBLOBs.setConfigName(filePath.substring(0));
                        }
                    }
                    String configContentResult=clientService.doClient("cat "+filePath,instanceId,"souche",null);
                    String saltConfigContent=getSingleClientResult(instanceId,configContentResult);


                    String text=saltConfigContent.replaceAll("[\\n]","<br/>");
                   // System.err.println(text);
                    configWithBLOBs.setConfigContent(text);
                   // System.err.println ("add文件");
                    try{
                        configMapper.insertSelective(configWithBLOBs);
                    }catch (Exception e){
                        log.info("新增文件失败 机器"+instanceId+"文件"+configWithBLOBs.getConfigName() );
                        e.printStackTrace();
                    }

                }
            }
    }

}


   public void  updateDBContent(Integer id,String content ){
       ConfigWithBLOBs configWithBLOBs=configMapper.selectByPrimaryKey(id);

           configWithBLOBs.setVersion5(configWithBLOBs.getVersion4());
           configWithBLOBs.setVersion4(configWithBLOBs.getVersion3());
           configWithBLOBs.setVersion3(configWithBLOBs.getVersion2());
           configWithBLOBs.setVersion2(configWithBLOBs.getVersion1());
           configWithBLOBs.setVersion1(configWithBLOBs.getConfigContent());
           configWithBLOBs.setConfigContent(content);
           configWithBLOBs.setVerTime5(configWithBLOBs.getVerTime4());
           configWithBLOBs.setVerTime4(configWithBLOBs.getVerTime3());
           configWithBLOBs.setVerTime3(configWithBLOBs.getVerTime2());
           configWithBLOBs.setVerTime2(configWithBLOBs.getVerTime1());
           configWithBLOBs.setVerTime1(Date.date2String(new java.util.Date()));
           configWithBLOBs.setModify5(configWithBLOBs.getModify4());
           configWithBLOBs.setModify4(configWithBLOBs.getModify3());
           configWithBLOBs.setModify3(configWithBLOBs.getModify2());
           configWithBLOBs.setModify2(configWithBLOBs.getModify1());
           configWithBLOBs.setModify1(scriptsService.getLoginName());
           configMapper.updateByPrimaryKeySelective(configWithBLOBs);

    }
    @Override
    public List<Map<String, Object>> getInstanceConf(String tableData) {
        List<Map<String, Object>> list=new ArrayList<>();
        JSONArray jsonArray=JSONArray.parseArray(tableData);
        for (int i=0;i<jsonArray.size();i++){
            Map<String,Object> map=(Map<String,Object>)jsonArray.get(i);
            String instanceId=map.get("instanceId").toString();
            //System.err.println(instanceId);
            List<Map<String, String>> list2=new ArrayList<>();
            ConfigExample configExample=new ConfigExample();
            configExample.or().andInstanceIdEqualTo(instanceId);
            List<ConfigWithBLOBs> configs=configMapper.selectByExampleWithBLOBs(configExample);
            for (int j=0;j<configs.size();j++){
                System.err.println(configs.get(j));
                Map<String,String> map2=new HashMap<>();
                map2.put("configPath",configs.get(j).getConfigPath());
                map2.put("configName",configs.get(j).getConfigName());
                map2.put("id",configs.get(j).getId().toString());
               if(configs.get(j).getVersion1()!=null){
                   map2.put("version1",configs.get(j).getVersion1().replaceAll("<br/>","\r\n"));
               }else {
                   map2.put("version1",configs.get(j).getVersion1());
               }
                if(configs.get(j).getVersion2()!=null){
                    map2.put("version2",configs.get(j).getVersion2().replaceAll("<br/>","\r\n"));
                }else {
                    map2.put("version2",configs.get(j).getVersion2());
                }
                if(configs.get(j).getVersion3()!=null){
                    map2.put("version3",configs.get(j).getVersion3().replaceAll("<br/>","\r\n"));
                }else {
                    map2.put("version3",configs.get(j).getVersion3());
                }
                if(configs.get(j).getVersion4()!=null){
                    map2.put("version4",configs.get(j).getVersion4().replaceAll("<br/>","\r\n"));
                }else {
                    map2.put("version4",configs.get(j).getVersion4());
                }
                if(configs.get(j).getVersion5()!=null){
                    map2.put("version5",configs.get(j).getVersion5().replaceAll("<br/>","\r\n"));
                }else {
                    map2.put("version5",configs.get(j).getVersion5());
                }



                map2.put("verTime1",configs.get(j).getVerTime1());
                map2.put("verTime2",configs.get(j).getVerTime2());
                map2.put("verTime3",configs.get(j).getVerTime3());
                map2.put("verTime4",configs.get(j).getVerTime4());
                map2.put("verTime5",configs.get(j).getVerTime5());



                String mytext=configs.get(j).getConfigContent().replaceAll("<br/>","\r\n");
                map2.put("configContent",mytext);
                list2.add(map2);
            }
           // System.err.println(list2);
            map.put("config",list2);
            //map表示一台机器信息  现在去查询机器Ip
            String mecheInfo= hongmaoController.hmQuerySelectMeche(instanceId,null);
            //System.err.println(mecheInfo);
            Map mecheInfoMap=(Map) JSONObject.parseObject(JSONArray.parseArray(mecheInfo).get(0).toString());
            map.put("publicAddress",mecheInfoMap.get("publicAddress"));
            map.put("innerAddress",mecheInfoMap.get("innerAddress"));
            map.put("privateAddress",mecheInfoMap.get("privateAddress"));
            list.add(map);
        }

        return list;
    }

    @Override
    public String getSingleClientResult(String instanceId, String result) {
             // instanceId="172.17.240.134";
        log.info(result);
        try {
            String returnResult = JSONObject.parseObject(result).get("return").toString();
            JSONArray jsonArray = JSONArray.parseArray(returnResult);
            Map machine1 = (Map) jsonArray.get(0);

            Map machine1Map = (Map) machine1.get(instanceId);
            return machine1Map.get("stdout").toString();
        }catch (Exception e){
            log.info("方法NginxServiceImpl.getSingleClientResult发生异常");
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String updateContent(Integer id, String content, String operate) {
        Config config=configMapper.selectByPrimaryKey(id);
      /*  System.err.println((ConfigWithBLOBs) config);
        System.err.println(((ConfigWithBLOBs) config).getConfigContent());
        if(true){
            return  null;
        }*/
        String result=null;
        result=clientService.doClient("echo \'"+content+"\' > "+config.getConfigPath()+";./nginx/bin/nginxctl configtest ",config.getInstanceId(),"souche",null);
        List<Map<String, String>> listMap= DataUtil.saltResult(result,hongmaoController.getHongMaoHeader());
        if("0".equals(listMap.get(0).get("retcode"))){
                if ("save".equals(operate)){
            result=clientService.doClient("echo \'"+content+"\' > "+config.getConfigPath()+" ",config.getInstanceId(),"souche",null);
           // System.err.println(result);

          }else if ("restart".equals(operate)){
             result=clientService.doClient("echo \'"+content+"\' > "+config.getConfigPath()+";./nginx/bin/nginxctl reload ",config.getInstanceId(),"souche",null);
           // System.err.println(result);

          }
                ConfigWithBLOBs configWithBLOBs=configMapper.selectByPrimaryKey(id);
                if(!content.equals(configWithBLOBs.getConfigContent())){

                configWithBLOBs.setVersion5(configWithBLOBs.getVersion4());
                configWithBLOBs.setVersion4(configWithBLOBs.getVersion3());
                configWithBLOBs.setVersion3(configWithBLOBs.getVersion2());
                configWithBLOBs.setVersion2(configWithBLOBs.getVersion1());
                configWithBLOBs.setVersion1(configWithBLOBs.getConfigContent());
                configWithBLOBs.setConfigContent(content);

                configWithBLOBs.setVerTime5(configWithBLOBs.getVerTime4());
                configWithBLOBs.setVerTime4(configWithBLOBs.getVerTime3());
                configWithBLOBs.setVerTime3(configWithBLOBs.getVerTime2());
                configWithBLOBs.setVerTime2(configWithBLOBs.getVerTime1());
                configWithBLOBs.setVerTime1(Date.date2String(new java.util.Date()));

                configWithBLOBs.setModify5(configWithBLOBs.getModify4());
                configWithBLOBs.setModify4(configWithBLOBs.getModify3());
                configWithBLOBs.setModify3(configWithBLOBs.getModify2());
                configWithBLOBs.setModify2(configWithBLOBs.getModify1());
                configWithBLOBs.setModify1(scriptsService.getLoginName());

                configMapper.updateByPrimaryKeyWithBLOBs(configWithBLOBs);
                }
                return JSON.toJSONString(DataUtil.saltResult(result,hongmaoController.getHongMaoHeader()));
        }else {
            String repaceContent=((ConfigWithBLOBs) config).getConfigContent().replaceAll("<br/>","\r\n");
             clientService.doClient("echo \'"+repaceContent+"\' > "+config.getConfigPath()+" ",config.getInstanceId(),"souche",null);
            return JSON.toJSONString(DataUtil.saltResult(result,hongmaoController.getHongMaoHeader()));
        }

    }

    @Override
    public String addNginx(String instanceId, String name, String content, String path) {
        String result=clientService.doClient("cd "+path+"",instanceId,"souche",null);
        String returnResult = JSONObject.parseObject(result).get("return").toString();
        JSONArray jsonArray = JSONArray.parseArray(returnResult);
        Map machine1 = (Map) jsonArray.get(0);

        Map machine1Map = (Map) machine1.get(instanceId);
        // Map machine1Map = (Map) machine1.get("172.17.40.134");
        if(!"0".equals(machine1Map.get("retcode").toString())){
            return "[{\"retcode\": \"1\",\"instanceId\": \"error\",\"stdout\": \"文件路径不存在\"}]";
        }


        //判断路径是否包含'/'
        String filePath=null;
        if(path.endsWith("/")){
            filePath=path+name;
        }else{
             filePath=path+"/"+name;
        }
        ConfigExample configExample=new ConfigExample();
        configExample.or().andInstanceIdEqualTo(instanceId).andConfigPathEqualTo(filePath);
        List<Config> listConfig=configMapper.selectByExample(configExample);
        if(listConfig.size()>0){
            return "[{\"retcode\": \"1\",\"instanceId\": \"error\",\"stdout\": \"文件名重复,新建失败\"}]";
        }else {
            ConfigWithBLOBs config=new ConfigWithBLOBs();
            //截取路径名
            try{
                config.setConfigName(filePath.substring(filePath.indexOf("nginx")));
            }catch (Exception e){
                if(filePath.length()>100){
                    config.setConfigName(filePath.substring(0,99));
                }else {
                    config.setConfigName(filePath.substring(0));
                }
            }
            config.setConfigPath(filePath);
            config.setInstanceId(instanceId);
            config.setConfigContent(content);
            configMapper.insert(config);
            clientService.doClient("echo \'"+content+"\' > "+filePath+" ",instanceId,"souche",null);
            String  scriptResults=clientService.doClient("./nginx/bin/nginxctl reload",instanceId,"souche",null);
            return JSON.toJSONString(DataUtil.saltResult(scriptResults,hongmaoController.getHongMaoHeader()));
        }

    }

    @Override
    public List<ConfigWithBLOBs> queryConf(String content) {
        List<ConfigWithBLOBs> list =configMapper.queryLike(content);
        List<String> listContent=new ArrayList<>();
        for (ConfigWithBLOBs conf:list
             ) {
         //  System.err.println(conf.getConfigName());
           // String Confcontent=conf.getConfigContent().replaceAll("<br/>","\r\n");
          //  System.err.println(Confcontent);
           // listContent.add(Confcontent);
            conf.setConfigContent(conf.getConfigContent().replaceAll("<br/>","\r\n"));
        }
        return list;
    }

    @Override
    public Integer disableFile(Integer id, String ifreload) {
        ConfigWithBLOBs config=configMapper.selectByPrimaryKey(id);
        String filePath=config.getConfigPath();
        String newName=config.getConfigName() + ".bak";//"."+Date.date2String(new java.util.Date()) +
        String newFilePath=filePath.substring(0,filePath.indexOf("nginx"))+newName;
        if("disreload".equals(ifreload)){
            clientService.doClient("mv "+filePath+" "+newFilePath,config.getInstanceId(),"souche",null);
        }else if("reload".equals(ifreload)){
          String saltresult=  clientService.doClient("mv "+filePath+" "+newFilePath+";./nginx/bin/nginxctl reload",config.getInstanceId(),"souche",null);
            System.err.println(saltresult);
        }

        config.setConfigName(newName);
        config.setConfigPath(newFilePath);
        return configMapper.updateByPrimaryKeySelective(config);
    }

    @Override
    public Integer effect(Integer id) {
        ConfigWithBLOBs config=configMapper.selectByPrimaryKey(id);
        String filePath=config.getConfigPath();
        String newFilePath=filePath.substring(0,filePath.indexOf(".bak"));
        String newName=newFilePath.substring(newFilePath.indexOf("nginx"));//"."+Date.date2String(new java.util.Date()) +
        clientService.doClient("mv "+filePath+" "+newFilePath+";./nginx/bin/nginxctl reload",config.getInstanceId(),"souche",null);
        config.setConfigName(newName);
        config.setConfigPath(newFilePath);
        return configMapper.updateByPrimaryKeySelective(config);

    }


}
