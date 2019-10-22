package com.souche.salt_common_01.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.service.SyncService;
import com.souche.salt_common_01.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SyncController {
    @Autowired
    private SyncService syncService;


    @GetMapping("/lantu/syhosts")
    public String syhosts(){
        return syncService.syhosts();
    }

    @GetMapping("/lantu/syRelation")
    public String sy(){
        String result=  syncService.syncData();
        return result;
    }

    @GetMapping("/lantu/syResource")
    public String resource(){
      String result=  syncService.syResource();
        return result;
    }




    @GetMapping("/lantu/syRedisInst")
    public String addRedisInst(){

        return  syncService.addRedisInstance();
    }
    @GetMapping("/lantu/syBaseInst")
    public String addBaseInst(){

        return  syncService.mysqlInstance();
    }
    @GetMapping("/lantu/syNodeInst")
    public String nodeInstance(){

        return  syncService.nodeInstance();
    }
    @GetMapping("/lantu/sySlbInst")
    public String addSlbInst(){

        return  syncService.slbInstance();
    }
    @GetMapping("/lantu/syUserInst")
    public String addUserInst(){

        return  syncService.userInstance();
    }






    //
    @GetMapping("/lantu/addRedisAttr")
    public void addRedisAttr(){
        Map<String,String> header=new HashMap<>();
        header.put("Cookie","bk_token=BYnijethSzV-eFysGLcCrWKU-p0bRwVs3BfOj0ahL5o");
        Map<String,Object>  param=new HashMap<>();
        param.put("bk_obj_id","redis");
        param.put("bk_supplier_account","0");
        param.put("bk_property_type","singlechar");

        param.put("bk_property_id","id");
        param.put("bk_property_name","id");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instanceid");
        param.put("bk_property_name","instanceid");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instancename");
        param.put("bk_property_name","instancename");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","connectiondomain");
        param.put("bk_property_name","connectiondomain");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","port");
        param.put("bk_property_name","port");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instanceclass");
        param.put("bk_property_name","instanceclass");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instanceclassname");
        param.put("bk_property_name","instanceClassName");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","engineversion");
        param.put("bk_property_name","engineVersion");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instancestatus");
        param.put("bk_property_name","instanceStatus");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","privateip");
        param.put("bk_property_name","privateip");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instancetype");
        param.put("bk_property_name","instanceType");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","tag");
        param.put("bk_property_name","tag");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);
//date
        param.put("bk_property_id","createtime");
        param.put("bk_property_name","createTime");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","endtime");
        param.put("bk_property_name","endTime");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);


//   int
        Map<String,String> option=new HashMap<String,String>();
        param.put("bk_property_type","int");
        param.put("option",option);
        param.put("bk_property_id","capacity");
        param.put("bk_property_name","capacity");
        System.err.println(JSONObject.toJSONString(param));
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","bandwidth");
        param.put("bk_property_name","bandwidth");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);


        param.put("bk_property_id","connections");
        param.put("bk_property_name","connections");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","qps");
        param.put("bk_property_name","qps");
        HttpClientUtil.doPostJson("http://cmdb.bk2.com/api/v3/object/attr",JSONObject.toJSONString(param),header);




    }







}
