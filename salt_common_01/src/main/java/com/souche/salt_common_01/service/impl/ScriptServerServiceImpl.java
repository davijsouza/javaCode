package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.souche.salt_common_01.controller.HongmaoController;
import com.souche.salt_common_01.entity.ScriptServer;
import com.souche.salt_common_01.entity.ScriptServerExample;
import com.souche.salt_common_01.mapper.ScriptServerMapper;
import com.souche.salt_common_01.service.ScriptServerService;
import com.souche.salt_common_01.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ScriptServerServiceImpl implements ScriptServerService {
    @Autowired
    private ScriptServerMapper scriptServerMapper;
    @Autowired
    private HongmaoController hongmaoController;
    @Override
    public Integer addServer(String ScriptID, String account, String servers) {
        if(servers==null){
            return 0;
        }
        JSONArray serverArr= JSONArray.parseArray(servers);
        ScriptServer scriptServer=new ScriptServer();
        scriptServer.setScriptId(ScriptID);
        scriptServer.setAccount(account);
        for (int i=0;i<serverArr.size();i++){
                Map map= (Map) JSONObject.parse(serverArr.get(i).toString());
            /*    scriptServer.setSzoneName(map.get("szoneName").toString());
                scriptServer.setSdoMainName(map.get("sdoMainName").toString());
                scriptServer.setProductLineName(map.get("productLineName").toString());
                scriptServer.setProductName(map.get("productName").toString());
                scriptServer.setApplicationName(map.get("applicationName").toString());
*/
            scriptServer.setInstanceId(map.get("instanceId").toString());
            scriptServer.setSzoneName(map.get("publicAddress").toString());
            scriptServer.setSdoMainName(map.get("privateAddress").toString());
            scriptServer.setProductLineName(map.get("innerAddress").toString());
            scriptServer.setProductName("null");
            scriptServer.setApplicationName("null");
            scriptServer.setInstanceName(map.get("instanceName").toString());





                scriptServerMapper.insert(scriptServer);



        }

        return null;
    }

    @Override
    public Map<String, Object> getServer(String scriptID) {
          //判断脚本属于 job  还是定时任务 分两种情况查找instanceId

        ScriptServerExample scriptServerExample=new ScriptServerExample();
        scriptServerExample.or().andScriptIdEqualTo(scriptID);
        List<ScriptServer> scriptServers=scriptServerMapper.selectByExample(scriptServerExample);
        Map<String, Object> map=new HashMap<>();
        List<String> instanceIds=new ArrayList<>();
        if(scriptServers!=null&&scriptServers.size()>0){
            map.put("account",scriptServers.get(0).getAccount());
            if(scriptServers.get(0).getInstanceId()==null||scriptServers.get(0).getInstanceId()==""){
                //定时任务查询服务器
                for (ScriptServer scriptServer:
                        scriptServers) {
                     /*String hmselectLik=hongmaoController.hmSelectLike("souche","default",
                             "支付平台","计费","charge-api");*/
                     String hmselectLik=hongmaoController.hmSelectLike(scriptServer.getSzoneName(),scriptServer.getSdoMainName(),
                             scriptServer.getProductLineName(),scriptServer.getProductName(),scriptServer.getApplicationName());
                     System.err.println(hmselectLik);
                     JSONArray servers= JSONArray.parseArray(hmselectLik);
                     for (int i=0;i<servers.size();i++){
                         JSONObject   oneServer=JSONObject.parseObject(servers.get(i).toString());
                         String instanceID= (String) oneServer.get("instanceId");
                         instanceIds.add(instanceID);

                     }


                }
                map.put("instanceIds",instanceIds);
                return map;

            }else {
                //普通job查询服务器
                for (ScriptServer scriptServer:
                scriptServers) {
                    instanceIds.add(scriptServer.getInstanceId());
                }
                map.put("instanceIds",instanceIds);
                return map;


            }
        }else{
            return null;
        }


    }

    @Override
    public void delServer(String scriptID) {
      ScriptServerExample scriptServerExample=new ScriptServerExample();
      scriptServerExample.or().andScriptIdEqualTo(scriptID);
      scriptServerMapper.deleteByExample(scriptServerExample);
    }
}
