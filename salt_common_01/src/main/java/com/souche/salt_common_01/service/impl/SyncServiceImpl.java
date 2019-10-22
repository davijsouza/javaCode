package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.controller.HongmaoController;
import com.souche.salt_common_01.service.SyncService;
import com.souche.salt_common_01.utils.DataUtil;
import com.souche.salt_common_01.utils.HttpClientUtil;
import com.souche.salt_common_01.utils.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authorization.client.util.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class SyncServiceImpl implements SyncService {
    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private HongmaoController hongmaoController;
    private  String bk_token="bk_token=ND20WsWQh1tw0YCqJwX_I1SsPyjF6mHPVyppwjDnEi4;cc3=MTU2NTMzMjU4MnxOd3dBTkVOTU5VWldWbEpQVkVsR1RscERTbEJEUmtwRlVFdERVbHBDVmtGTFRFeEpTa3MxVFV4WVdWa3pXVU5hVGxwUlQwVktRa0U9fK-eRwKiDy6kOa09LulxO08gBamqBHkTgXTlvYoAD-BL";
   //开发环境

   // private  String cmdbUrl="http://paas.dasouche-inc.net";

    //private  String appToken="7347ede6-8f84-454b-9660-0fb2146897f8";


    //生产环境
     private  String appToken="8f955d5d-8b6b-43bb-9f2c-9375675be5dd";
     private  String cmdbUrl="http://paas.souche-inc.com";

    /*
    *
    * 初始化层级关系 绑定机器和应用的关系
    * */
    @Override
    public String syncData() {
        List<Object> ljszones= DataUtil.findInstance("szone","bk_parent_id",3,"bk_inst_name");
        String allData=hongmaoController.hmSelectAllData();
        JSONArray szoneArr=JSONArray.parseArray(allData);
        //for(int i=0;i<0;i++){
        for(int i=0;i<szoneArr.size();i++){
            long szoneId=0;
            String szoneInfoString=szoneArr.get(i).toString();
            JSONObject szoneInfoObj=JSONObject.parseObject(szoneInfoString);
            String szoneName=szoneInfoObj.get("code").toString();
            if (ljszones.contains(szoneName)){
                List<Object> forszoneId=DataUtil.findInstance("szone","bk_inst_name",szoneName,"bk_inst_id");
                szoneId= Long.parseLong(forszoneId.get(0).toString());
                ljszones.remove(szoneName);
            }else{
                String insertId=  DataUtil.create_inst("szone",szoneName,3);
                if(insertId==null)continue;
                szoneId= Long.parseLong(insertId) ;
            }

            Object sdmainInfo=szoneInfoObj.get("children").toString();
            if (sdmainInfo==null)continue;
            List<Object> ljsdomains= DataUtil.findInstance("sdomain","bk_parent_id",szoneId,"bk_inst_name");;
            JSONArray sdomainArr=JSONArray.parseArray(sdmainInfo.toString());
            for(int k=0;k<sdomainArr.size();k++){
                long sdomainId=0;
                String sdomainInfoString=sdomainArr.get(k).toString();
                JSONObject sdomainInfoObj=JSONObject.parseObject(sdomainInfoString);
                String sdomainName=sdomainInfoObj.get("code").toString();
                if(ljsdomains.contains(sdomainName)){
                    List<Object> forsdomainId= DataUtil.findInstance("sdomain","bk_inst_name",sdomainName,"bk_parent_id",szoneId,"bk_inst_id");
                    sdomainId= Long.parseLong(forsdomainId.get(0).toString());
                    ljsdomains.remove(sdomainName);
                }else {
                    String insertId=DataUtil.create_inst("sdomain",sdomainName,szoneId);
                    if(insertId==null)continue;
                    sdomainId= Long.parseLong(insertId);
                }

                Object produceLineInfo=sdomainInfoObj.get("children") ;
                if (produceLineInfo==null){
                    continue;
                }
                 List<Object> ljproduceLines=DataUtil.findInstance("produceline","bk_parent_id",sdomainId,"bk_inst_name");
                JSONArray produceLineArr=JSONArray.parseArray(produceLineInfo.toString());
                for(int j=0;j<produceLineArr.size();j++){
                    long produceLineId=0;
                    String produceLineInfoString=produceLineArr.get(j).toString();
                    JSONObject produceLineInfoObj=JSONObject.parseObject(produceLineInfoString);
                    String produceLineName=produceLineInfoObj.get("code").toString();
                    if(ljproduceLines.contains(produceLineName)){
                        List<Object> forproduceLineId=DataUtil.findInstance("produceline","bk_inst_name",produceLineName,"bk_parent_id",sdomainId,"bk_inst_id");
                        if (forproduceLineId==null||forproduceLineId.size()==0)continue;
                        produceLineId= Long.parseLong(forproduceLineId.get(0).toString());
                        ljproduceLines.remove(produceLineName);
                    }else {
                       String insertId=DataUtil.create_inst("produceline",produceLineName,sdomainId);
                        if(insertId==null)continue;
                        produceLineId= Long.parseLong(insertId);

                    }

                    Object produceInfo=produceLineInfoObj.get("children");
                    if (produceInfo==null){
                        continue;
                    }
                    List<Object> sets=DataUtil.findSet("bk_parent_id",produceLineId,"bk_set_name");
                    List<Object> setsIds= DataUtil.findSet("bk_parent_id",produceLineId,"bk_set_id");
                    JSONArray produceArr=JSONArray.parseArray(produceInfo.toString());
                    for (int l=0;l<produceArr.size();l++){

                        Long produceId=IDUtils.genItemId();
                        String produceInfoString=produceArr.get(l).toString();
                        JSONObject produceInfoObj=JSONObject.parseObject(produceInfoString);
                        String produceName=produceInfoObj.get("code").toString();
                        //跳过那一堆空应用
                        if(szoneName.equals("souche")&&produceName.equals("default"))continue;
                        if(sets.contains(produceName)){
                            List<Object> forproduceId= DataUtil.findSet("bk_set_name",produceName,"bk_parent_id",produceLineId,"bk_set_id");
                            produceId= Long.parseLong(forproduceId.get(0).toString());
                            setsIds.remove(forproduceId.get(0));
                        }else {
                          String param="{\"bk_app_code\": \"bk_cmdb\",\"bk_app_secret\": \""+appToken+"\",\"bk_supplier_account\": \"0\",\"bk_username\": \"admin\",\n" +
                                    "\t\"bk_biz_id\":3,\"data\": {\"bk_parent_id\": "+produceLineId+",\"bk_set_name\": \""+produceName+"\"}}";

                            String addResult=  HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_set/", param);
                            log.info(addResult);
                            JSONObject addJsonObject=JSONObject.parseObject(addResult);
                            Map resultDate= (Map) addJsonObject.get("data");
                            produceId= Long.parseLong(resultDate.get("bk_set_id").toString()) ;

                        }


                        Object applaycationInfo=produceInfoObj.get("children");
                        if (applaycationInfo==null){
                            continue;
                        }
                        List<Object> modules=DataUtil.findModules(produceId,"bk_parent_id",produceId,"bk_module_name");
                        List<Object> modulesIds=DataUtil.findModules(produceId,"bk_parent_id",produceId,"bk_module_id");
                        JSONArray applaycationArr=JSONArray.parseArray(applaycationInfo.toString());
                        for (int m=0;m<applaycationArr.size();m++){
                            Long appId=IDUtils.genItemId();
                            String applaycationInfoString=applaycationArr.get(m).toString();
                            JSONObject applaycationobj=JSONObject.parseObject(applaycationInfoString);
                            String appName=applaycationobj.getString("code");
                            if(modules.contains(appName)){
                                 List<Object> modulesId= DataUtil.findModules(produceId,"bk_module_name",appName,"bk_module_id");;
                                 appId= Long.parseLong(modulesId.get(0).toString());
                                 modulesIds.remove(modulesId.get(0));
                            }else {
                             String param="{\"bk_app_code\": \"bk_cmdb\",\"bk_app_secret\": \""+appToken+"\",\"bk_supplier_account\": \"0\",\"bk_username\": \"admin\",\n" +
                                        "\t\"bk_biz_id\":3,\"bk_set_id\":"+produceId+",\"data\": {\"bk_parent_id\": "+produceId+",\"bk_module_name\": \""+appName+"\"}}";

                                String addResult=  HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_module/", param);
                                log.info(addResult);
                                JSONObject addJsonObject=JSONObject.parseObject(addResult);
                                Map resultDate= (Map) addJsonObject.get("data");
                                appId= Long.parseLong(resultDate.get("bk_module_id").toString()) ;
                            }




                            String mechelike= hongmaoController.hmSelectByAll(szoneName,appName,null);
                            System.out.println("hmSelectByAll的返回值（应用下主机）-"+szoneName+"-"+appName+":"+mechelike);
                            JSONArray mechelikearr=JSONArray.parseArray(mechelike);
                            if(mechelikearr.size()==0)continue;
                            List<Object> ljhostIds=DataUtil.findHostByLike("module","bk_module_id",appId,"bk_host_id");

                            for (int n=0;n<mechelikearr.size();n++){
                                JSONObject mecheObj=JSONObject.parseObject(mechelikearr.get(n).toString());
                                String instanceId=mecheObj.get("instanceId").toString();
                                List<Object> list2 = DataUtil.findHostByLike("host","instanceid",instanceId,"bk_host_id");
                                if(list2.size()==0){
                                    continue;
                                }
                               long hostId= Long.parseLong(list2.get(0).toString());
                                 ljhostIds.remove(list2.get(0));
//                              插入机器和业务的绑定关系
                                String a= " { \"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":\""+appToken+"\",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\",\n" +
                                            " \"bk_biz_id\":3,\"bk_host_id\": ["+hostId+"],\"bk_module_id\": ["+appId+"],\"is_increment\": true }";
                                    System.err.println(a);
                                    System.err.println(HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/transfer_host_module/", a));
                              //  }

                            }
                            //模块下删除多余主机
                            for (Object id:ljhostIds
                                 ) {
                                 Long hostid= Long.parseLong(id.toString());
                                 String param="{\"bk_biz_id\": 3,\"bk_host_id\": ["+hostid+"],\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":\""+appToken+"\",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\"}";
                                 HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/transfer_host_to_idlemodule/",param);
                            }
                        }
                        //删除多余模块
                        for (Object moduleid:modulesIds
                        ) {
                             String param="{\"bk_biz_id\":3,\"bk_module_id\":"+moduleid+",\"bk_set_id\":"+produceId+",\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":\""+appToken+"\",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\"}";
                             HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/transfer_sethost_to_idle_module/",param);
                             String result=HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/delete_module/",param);

                        }

                    }
                    //删除多余集群
                    for (Object setid:setsIds
                    ) {

                        String param="{\"bk_biz_id\":3,\"bk_set_id\":"+setid+",\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":\""+appToken+"\",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\"}";
                        HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/transfer_sethost_to_idle_module/",param);
                        String result=HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/delete_module/",param);


                    }
                }
                //删除多余产品线
                for (Object LineName:ljproduceLines
                ) {
//
                }

            }
            for (Object sdomainName:ljsdomains
            ) {

//                Query queryid = Query.query(Criteria.where("bk_inst_name").is(sdomainName));
//                mongoTemplate.remove(queryid,"cc_ObjectBase");
            }
        }
        for (Object delszoneName:ljszones
        ) {

//            Query queryid = Query.query(Criteria.where("bk_inst_name").is(delszoneName));
//            mongoTemplate.remove(queryid,"cc_ObjectBase");
        }

        return "success";
    }


    /*
    * 初始化主机信息
    *
    * */
    @Override
    public String syhosts() {
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bk_token);
        List<String> instanceIds=new ArrayList<>();
        String hostsInfo= hongmaoController.hmSelectAllJiqi();
        JSONArray hostarr=JSONArray.parseArray(hostsInfo);
        List<Object> allHostBase= DataUtil.findHostByLike("host",null,null,"instanceid");
      for (int i=0;i<hostarr.size();i++){
           JSONObject hostobj=JSONObject.parseObject(hostarr.get(i).toString());
           String instanceId=hostobj.get("instanceId").toString();
           instanceIds.add(instanceId);
           //查询主机表中是否有这个instanceId  如果已经有了就continue
//            if(allHostBase.contains(instanceId)){
//               continue;
//           }
           Map<String,Object> hostmap=new HashMap<>();
           if ("[]".equals(hostobj.get("privateAddress"))){
               hostmap.put("bk_host_innerip",hostobj.get("innerAddress").toString().replaceAll("\\[|\\]",""));
           }else {
               hostmap.put("bk_host_innerip",hostobj.get("privateAddress").toString().replaceAll("\\[|\\]",""));
           }
//           hostmap.put("bk_asset_id",hostobj.get("instanceId"));
           String publicAddress=hostobj.get("publicAddress").toString();
           hostmap.put("bk_host_outerip",hostobj.get("publicAddress").toString().replaceAll("\\[|\\]",""));
           hostmap.put("bk_host_name",hostobj.get("instanceName"));
           //hostmap.put("bk_host_id",Long.parseLong(hostobj.get("id").toString()));
           hostmap.put("instanceid",hostobj.get("instanceId"));
           hostmap.put("expiredtime",hostobj.get("expiredTime"));


          // mongoTemplate.save(hostmap,"cc_HostBase");

           Map<String,Object> HostSort=new HashMap<>();
           HostSort.put("0",hostmap);

           Map<String,Object> addHost=new HashMap<>();
           addHost.put("bk_supplier_id",0);
           addHost.put("bk_biz_id",3);
           addHost.put("bk_app_code","bk_cmdb");
           addHost.put("bk_app_secret",appToken);
           addHost.put("bk_username","admin");
           addHost.put("host_info",HostSort);
           log.info(addHost.toString()+"=="+cmdbUrl+"/api/c/compapi/v2/cc/add_host_to_resource/");
           String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/add_host_to_resource/", JSONObject.toJSONString(addHost));
           log.info(result);
       }

       for (int l=0;l<allHostBase.size();l++){
           if(allHostBase.get(l)==null)continue;
           if(!instanceIds.contains(allHostBase.get(l).toString())){
              List<Object> objectList=DataUtil.findHostByLike("host","instanceid",allHostBase.get(l).toString(),"bk_host_id");
              String param="{\"bk_app_code\": \"bk_cmdb\",\"bk_app_secret\": \""+appToken+"\",\"bk_supplier_account\": \"0\",\"bk_username\": \"admin\",\"bk_host_id\":\""+objectList.get(0).toString()+"\"}";
              // String a=allHostBase.get(l).toString();
              log.info(param);
              String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/delete_host/", param);
              log.info(result);
          }


       }


      return "success";
    }
    /*
     * 初始化空闲机
     *
     *  */

    public String syOther() {

        List<Map> list2 = mongoTemplate.findAll(Map.class,"cc_HostBase");
        for (Map map:list2){
            Long hostId= (Long) map.get("bk_host_id");
            Query query = Query.query(Criteria.where("bk_host_id").is(hostId));
            List<Map> listRe = mongoTemplate.find(query,Map.class,"cc_ModuleHostConfig");
            //System.err.println(listRe.size());
            if(listRe.size()==0){
                Map<String,Object> hostConfig=new HashMap<>();
                hostConfig.put("bk_host_id",hostId);
                hostConfig.put("bk_module_id",54);
                hostConfig.put("bk_set_id",10);
                hostConfig.put("bk_supplier_account","0");
                hostConfig.put("bk_biz_id",3);
                mongoTemplate.save(hostConfig,"cc_ModuleHostConfig");
            }else if(listRe.size()>1){
                //删除空闲池中的绑定
                Query query2 = Query.query(Criteria.where("bk_host_id").is(hostId).and("bk_module_id").is(54));
                mongoTemplate.remove(query2,"cc_ModuleHostConfig");

            }

        }

        return null;
    }

    /*
    * 增加redis实例 新增模型字段
    * */

    public String addRedisInstance(){
       String redisData=hongmaoController.hmSelectAllRedis();
       Map<String,String> header=new HashMap<>();
       header.put("Cookie",bk_token);
       // System.err.println(HttpClientUtil.doGet(cmdbUrl+"/api/v3/userapi/data/2/bl0fdgo2a8on3bdop6fg/0/100", null, header));
       JSONArray jsonArray=JSONArray.parseArray(redisData);
       List<String> instaceidList=new ArrayList<>();
        List<Object> listFind=  DataUtil.findInstance("redis",null,null,"id");
       for (int i=0;i<jsonArray.size();i++){
           Map redisInfo=(Map)jsonArray.get(i);
           instaceidList.add(redisInfo.get("id").toString());

          if(listFind.contains(redisInfo.get("id").toString())){

          }else {
              redisInfo.put("bk_app_code","bk_cmdb");
              redisInfo.put("bk_app_secret",appToken);
              redisInfo.put("bk_supplier_account","0");
              redisInfo.put("bk_username","admin");
              redisInfo.put("bk_obj_id","redis");
              redisInfo.put("bk_inst_name",redisInfo.get("id").toString());
              String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/", JSONObject.toJSONString(redisInfo).toLowerCase(),header);
              //System.err.println(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/");
              log.info(result);
          }

       }
        List<Object> result=DataUtil.findInstance("redis",null,null,"id");
       for (Object info:result
       ) {
           if(!instaceidList.contains(info)){
              List<Object> list= DataUtil.findInstance("redis","id",info,"bk_inst_id");
              DataUtil.delete_inst("redis",list.get(0));
           }
       }
       return "success";
   }


   /*db_base*/
    public String mysqlInstance(){
        String redisData=hongmaoController.hmSelectAlldb();
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bk_token);
        // System.err.println(HttpClientUtil.doGet(cmdbUrl+"/api/v3/userapi/data/2/bl0fdgo2a8on3bdop6fg/0/100", null, header));
        JSONArray jsonArray=JSONArray.parseArray(redisData);
        List<String> instaceidList=new ArrayList<>();
         List<Object> listDbbaseid=DataUtil.findInstance("db_base",null,null,"dbbaseid");

        for (int i=0;i<jsonArray.size();i++){
            Map myselInfo=(Map)jsonArray.get(i);
            Object dbBaseid=myselInfo.get("dbBaseId");
            if (listDbbaseid.contains(dbBaseid)){
                listDbbaseid.remove(dbBaseid);
            }else {
                myselInfo.put("dbdescription",myselInfo.get("dbInstanceDescription"));
                myselInfo.remove("dbInstanceDescription");
                myselInfo.put("bk_inst_name",myselInfo.get("dbBaseId").toString());
                myselInfo.put("bk_app_code","bk_cmdb");
                myselInfo.put("bk_app_secret",appToken);
                myselInfo.put("bk_supplier_account","0");
                myselInfo.put("bk_username","admin");
                myselInfo.put("bk_obj_id","db_base");
                String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/", JSONObject.toJSONString(myselInfo).toLowerCase(),header);
                log.info(result);
            }
        }
        for (Object info:listDbbaseid
        ) {
            List<Object> list= DataUtil.findInstance("db_base","dbbaseid",info,"bk_inst_id");
            DataUtil.delete_inst("db_base",list.get(0));
        }
        return "success";
    }
    /*db_node*/
    public String nodeInstance(){
        String redisData=hongmaoController.hmSelectAlldbNode();
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bk_token);
        // System.err.println(HttpClientUtil.doGet(cmdbUrl+"/api/v3/userapi/data/2/bl0fdgo2a8on3bdop6fg/0/100", null, header));
        JSONArray jsonArray=JSONArray.parseArray(redisData);
        List<String> instaceidList=new ArrayList<>();
        List<Object> listDbbaseid=DataUtil.findInstance("db_node",null,null,"id");
        for (int i=0;i<jsonArray.size();i++){
            Map myselInfo=(Map)jsonArray.get(i);
            Object dbnodeid=myselInfo.get("id");
            if (listDbbaseid.contains(dbnodeid)){
                listDbbaseid.remove(dbnodeid);
            }else {
                myselInfo.put("dbdescription",myselInfo.get("dbInstanceDescription"));
                myselInfo.remove("dbInstanceDescription");
                myselInfo.put("bk_inst_name",myselInfo.get("id").toString());
                myselInfo.put("bk_app_code","bk_cmdb");
                myselInfo.put("bk_app_secret",appToken);
                myselInfo.put("bk_supplier_account","0");
                myselInfo.put("bk_username","admin");
                myselInfo.put("bk_obj_id","db_node");
                String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/", JSONObject.toJSONString(myselInfo).toLowerCase(),header);
                log.info(result);
            }
        }
        for (Object info:listDbbaseid
        ) {
            List<Object> list= DataUtil.findInstance("db_node","id",info,"bk_inst_id");
            DataUtil.delete_inst("db_node",list.get(0));
        }
        return "success";
    }

    public String slbInstance(){
        String redisData=hongmaoController.hmSelectAllSlb();
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bk_token);
        JSONArray jsonArray=JSONArray.parseArray(redisData);
        List<Object> listDbbaseid=DataUtil.findInstance("slb",null,null,"id");
        List<String> instaceidList=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            Map myselInfo=(Map)jsonArray.get(i);
            Object slbid=myselInfo.get("id");
            if (listDbbaseid.contains(slbid)){
                listDbbaseid.remove(slbid);
            }else {
                myselInfo.put("bk_inst_name",myselInfo.get("id").toString());
                myselInfo.put("bk_app_code","bk_cmdb");
                myselInfo.put("bk_app_secret",appToken);
                myselInfo.put("bk_supplier_account","0");
                myselInfo.put("bk_username","admin");
                myselInfo.put("bk_obj_id","slb");
                String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/", JSONObject.toJSONString(myselInfo).toLowerCase(),header);
                log.info(result);
            }

        }

        for (Object info:listDbbaseid
        ) {
            List<Object> list= DataUtil.findInstance("slb","id",info,"bk_inst_id");
            DataUtil.delete_inst("slb",list.get(0));
        }
        return "success";
    }


    public String userInstance(){
        String redisData=hongmaoController.hmSelectAllUser();
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bk_token);
        // System.err.println(HttpClientUtil.doGet(cmdbUrl+"/api/v3/userapi/data/2/bl0fdgo2a8on3bdop6fg/0/100", null, header));
        JSONArray jsonArray=JSONArray.parseArray(redisData);
        List<String> instaceidList=new ArrayList<>();
        List<Object> listDbbaseid=DataUtil.findInstance("user",null,null,"id");
        for (int i=0;i<jsonArray.size();i++){
            Map myselInfo=(Map)jsonArray.get(i);
            Object userId=myselInfo.get("id");
            if (listDbbaseid.contains(userId)){
                listDbbaseid.remove(userId);
            }else {

                myselInfo.put("bk_inst_name",myselInfo.get("id").toString());
                myselInfo.put("bk_app_code","bk_cmdb");
                myselInfo.put("bk_app_secret",appToken);
                myselInfo.put("bk_supplier_account","0");
                myselInfo.put("bk_username","admin");
                myselInfo.put("bk_obj_id","user");
                String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/", JSONObject.toJSONString(myselInfo).toLowerCase(),header);
                log.info(result);
            }
        }
        for (Object info:listDbbaseid
        ) {
            List<Object> list= DataUtil.findInstance("user","id",info,"bk_inst_id");
            DataUtil.delete_inst("user",list.get(0));
        }
        return "success";
    }

    @Override
    public String syResource() {
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", bk_token);
        List<Object> ljszones= DataUtil.findInstance("szone","bk_parent_id",3,"bk_inst_name");
        String allData=hongmaoController.hmSelectAllData();
        JSONArray szoneArr=JSONArray.parseArray(allData);
        for(int i=0;i<szoneArr.size();i++){
            long szoneId=0;
            String szoneInfoString=szoneArr.get(i).toString();
            JSONObject szoneInfoObj=JSONObject.parseObject(szoneInfoString);
            String szoneName=szoneInfoObj.get("code").toString();
           // List<Object> forszoneId= QuerUtil("bk_inst_name",szoneName,"bk_parent_id",3,"cc_ObjectBase","bk_inst_id");
            List<Object> forszoneId= DataUtil.findInstance("szone","bk_inst_name",szoneName,"bk_parent_id",3,"bk_inst_id");
            if(forszoneId.size()==0)continue;
            szoneId= Long.parseLong(forszoneId.get(0).toString());

            Object sdmainInfo=szoneInfoObj.get("children").toString();
            if (sdmainInfo==null)continue;
            List<Object> ljsdomains= DataUtil.findInstance("sdomain","bk_parent_id",szoneId,"bk_inst_name");;
            JSONArray sdomainArr=JSONArray.parseArray(sdmainInfo.toString());
            for(int k=0;k<sdomainArr.size();k++){
                long sdomainId=0;
                String sdomainInfoString=sdomainArr.get(k).toString();
                JSONObject sdomainInfoObj=JSONObject.parseObject(sdomainInfoString);
                String sdomainName=sdomainInfoObj.get("code").toString();
               // List<Object> forsdomainId= QuerUtil("bk_inst_name",sdomainName,"bk_parent_id",szoneId,"cc_ObjectBase","bk_inst_id");
                List<Object> forsdomainId= DataUtil.findInstance("sdomain","bk_inst_name",sdomainName,"bk_parent_id",szoneId,"bk_inst_id");
                if(forsdomainId.size()==0)continue;
                sdomainId= Long.parseLong(forsdomainId.get(0).toString());

                Object produceLineInfo=sdomainInfoObj.get("children") ;
                if (produceLineInfo==null){
                    continue;
                }
                List<Object> ljproduceLines=DataUtil.findInstance("produceline","bk_parent_id",sdomainId,"bk_inst_name");
                JSONArray produceLineArr=JSONArray.parseArray(produceLineInfo.toString());
                for(int j=0;j<produceLineArr.size();j++){
                    long produceLineId=0;
                    String produceLineInfoString=produceLineArr.get(j).toString();
                    JSONObject produceLineInfoObj=JSONObject.parseObject(produceLineInfoString);
                    String produceLineName=produceLineInfoObj.get("code").toString();
                   // List<Object> forproduceLineId= QuerUtil("bk_inst_name",produceLineName,"bk_parent_id",sdomainId,"cc_ObjectBase","bk_inst_id");
                    List<Object> forproduceLineId= DataUtil.findInstance("produceline","bk_inst_name",produceLineName,"bk_parent_id",sdomainId,"bk_inst_id");
                    if (forproduceLineId.size()==0)continue;
                    produceLineId= Long.parseLong(forproduceLineId.get(0).toString());

                    Object produceInfo=produceLineInfoObj.get("children");
                    if (produceInfo==null){
                        continue;
                    }
                   List<Object> sets=DataUtil.findSet("bk_parent_id",produceLineId,"bk_set_name");
                   List<Object> setsIds= DataUtil.findSet("bk_parent_id",produceLineId,"bk_set_id");
                    JSONArray produceArr=JSONArray.parseArray(produceInfo.toString());
                    for (int l=0;l<produceArr.size();l++){
                        Long produceId=IDUtils.genItemId();
                        String produceInfoString=produceArr.get(l).toString();
                        JSONObject produceInfoObj=JSONObject.parseObject(produceInfoString);
                        String produceName=produceInfoObj.get("code").toString();
                       // List<Object> forproduceId= QuerUtil("bk_set_name",produceName,"bk_parent_id",produceLineId,"cc_SetBase","bk_set_id");
                        List<Object> forproduceId= DataUtil.findSet("bk_set_name",produceName,"bk_parent_id",produceLineId,"bk_set_id");
                        if(forproduceId.size()==0)continue;
                        produceId= Long.parseLong(forproduceId.get(0).toString());

                        Object applaycationInfo=produceInfoObj.get("children");
                        if (applaycationInfo==null){
                            continue;
                        }
                        List<Object> modules=DataUtil.findModules(produceId,"bk_parent_id",produceId,"bk_module_name");
                        List<Object> modulesIds=DataUtil.findModules(produceId,"bk_parent_id",produceId,"bk_module_id");
                        JSONArray applaycationArr=JSONArray.parseArray(applaycationInfo.toString());
                        for (int m=0;m<applaycationArr.size();m++){
                            Long appId=IDUtils.genItemId();
                            String applaycationInfoString=applaycationArr.get(m).toString();
                            JSONObject applaycationobj=JSONObject.parseObject(applaycationInfoString);
                            String appName=applaycationobj.getString("code");
                            //List<Object> modulesId= QuerUtil("bk_module_name",appName,"bk_parent_id",produceId,"cc_ModuleBase","bk_module_id");
                            List<Object> modulesId= DataUtil.findModules(produceId,"bk_module_name",appName,"bk_module_id");
                            if(modulesId.size()==0){
                                continue;
                            }
                            appId= Long.parseLong(modulesId.get(0).toString());

                            //更新redis 的appid
                            //1 先删除这个应用下绑定的资源
                           List<Object> appInsertIds= DataUtil.findInstance("relation","module_id",appId,"bk_inst_id");
                            for (Object obj:appInsertIds
                                 ) {
                                DataUtil.delete_inst("relation",obj);
                            }

                         /*   Query query7 = Query.query(Criteria.where("bk_obj_id").is("relation").and("module_id").is(appId));
                            List<Map> list7 = mongoTemplate.find(query7, Map.class, "cc_ObjectBase");
                            for (Map map : list7
                            ) {
                                String bk_inst_id = map.get("bk_inst_id").toString();
                                HttpClientUtil.doDelete(cmdbUrl+"/api/v3/inst/0/relation/" + bk_inst_id, header);
                            }*/

                            //2 再绑定 应用下资源
                            String redisInfo = hongmaoController.hmAboutRedis(szoneName,
                                    null, null, null, "charge-api");
                            JSONArray jsonArray = JSONArray.parseArray(redisInfo);
                            for (int p = 0; p < jsonArray.size(); p++) {
                                Map redisMap = (Map) jsonArray.get(p);
                                String redisId = redisMap.get("redisId").toString();
                                Map<String, Object> param = new HashMap<>();
                                //坑  1 必须有bk_inst_name  唯一性
                                param.put("bk_inst_name", IDUtils.genImageName());
                                param.put("object_type", "redis");
                                param.put("module_id", appId);
                                param.put("instance_id", redisMap.get("instanceId").toString());

                                param.put("bk_app_code","bk_cmdb");
                                param.put("bk_app_secret",appToken);
                                param.put("bk_supplier_account","0");
                                param.put("bk_username","admin");
                                param.put("bk_obj_id","relation");

                                String result = HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/", JSONObject.toJSONString(param), header);
                                log.info(result);
                            }
                            //3


                            String allResouce = hongmaoController.selectAllResource(szoneName, appName);
                            //System.err.println("="+allResouce+"=");
                            if (!"".equals(allResouce) && allResouce != null) {
                                JSONObject jsonObject = JSONObject.parseObject(allResouce);

                                //Rds
                                Object rdsArr = jsonObject.get("Rds");
                                if (rdsArr != "" && rdsArr != null) {
                                    JSONArray jsonArray1 = JSONArray.parseArray(rdsArr.toString());
                                    for (int p = 0; p < jsonArray1.size(); p++) {
                                        Map map = (Map) jsonArray1.get(p);
                                        String db_baseId = map.get("id").toString();
                                        Map<String, Object> param = new HashMap<>();
                                        //坑  1 必须有bk_inst_name  唯一性
                                        param.put("bk_inst_name", IDUtils.genImageName());
                                        param.put("object_type", "db_base");
                                        param.put("module_id", appId);
                                        param.put("instance_id",db_baseId );

                                        param.put("bk_app_code","bk_cmdb");
                                        param.put("bk_app_secret",appToken);
                                        param.put("bk_supplier_account","0");
                                        param.put("bk_username","admin");
                                        param.put("bk_obj_id","relation");
                                        String result = HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/", JSONObject.toJSONString(param), header);
                                        log.info(result);
                                    }
                                }
                                //Slb

                                Object slbArr=jsonObject.get("Slb");
                                if (slbArr!=""&&slbArr!=null){
                                    JSONArray jsonArray2=JSONArray.parseArray(slbArr.toString());
                                    for (int a=0;a<jsonArray.size();a++){
                                        Map map= (Map) jsonArray.get(a);
                                        String slb_id=map.get("id").toString();
                                        Map<String, Object> param = new HashMap<>();
                                        //坑  1 必须有bk_inst_name  唯一性
                                        param.put("bk_inst_name", IDUtils.genImageName());
                                        param.put("object_type", "slb");
                                        param.put("module_id", appId);
                                        param.put("instance_id",slb_id );

                                        param.put("bk_app_code","bk_cmdb");
                                        param.put("bk_app_secret",appToken);
                                        param.put("bk_supplier_account","0");
                                        param.put("bk_username","admin");
                                        param.put("bk_obj_id","relation");
                                        String result = HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/", JSONObject.toJSONString(param), header);
                                        log.info(result);

                                    }
                                }



                            }







                        }


                    }

                }


            }
        }

        return "success";
    }
   /*relation*/
/*

    public String syResource1() {
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", bk_token);
        List<Object> ljszones=QuerUtil("bk_obj_id","szone","bk_parent_id",3,"cc_ObjectBase","bk_inst_name");
        String allData=hongmaoController.hmSelectAllData();
        JSONArray szoneArr=JSONArray.parseArray(allData);
        for(int i=0;i<szoneArr.size();i++){
            long szoneId=0;
            String szoneInfoString=szoneArr.get(i).toString();
            JSONObject szoneInfoObj=JSONObject.parseObject(szoneInfoString);
            String szoneName=szoneInfoObj.get("code").toString();
            List<Object> forszoneId= QuerUtil("bk_inst_name",szoneName,"bk_parent_id",3,"cc_ObjectBase","bk_inst_id");
            szoneId= (Long) forszoneId.get(0);
//
            Object sdmainInfo=szoneInfoObj.get("children").toString();
            if (sdmainInfo==null){
                continue;
            }
            JSONArray sdomainArr=JSONArray.parseArray(sdmainInfo.toString());
            for(int k=0;k<sdomainArr.size();k++){
                long sdomainId=0;
                String sdomainInfoString=sdomainArr.get(k).toString();
                JSONObject sdomainInfoObj=JSONObject.parseObject(sdomainInfoString);
                String sdomainName=sdomainInfoObj.get("code").toString();
                List<Object> forsdomainId= QuerUtil("bk_inst_name",sdomainName,"bk_parent_id",szoneId,"cc_ObjectBase","bk_inst_id");
                if(forsdomainId.size()==0){
                    continue;
                }
                sdomainId= (long) forsdomainId.get(0);
//
                Object produceLineInfo=sdomainInfoObj.get("children") ;
                if (produceLineInfo==null){
                    continue;
                }
                JSONArray produceLineArr=JSONArray.parseArray(produceLineInfo.toString());
                for(int j=0;j<produceLineArr.size();j++){
                    long produceLineId=0;
                    String produceLineInfoString=produceLineArr.get(j).toString();
                    JSONObject produceLineInfoObj=JSONObject.parseObject(produceLineInfoString);
                    String produceLineName=produceLineInfoObj.get("code").toString();
                    List<Object> forproduceLineId= QuerUtil("bk_inst_name",produceLineName,"bk_parent_id",sdomainId,"cc_ObjectBase","bk_inst_id");
                    produceLineId= (long) forproduceLineId.get(0);
//
                    Object produceInfo=produceLineInfoObj.get("children");
                    if (produceInfo==null){
                        continue;
                    }
                    JSONArray produceArr=JSONArray.parseArray(produceInfo.toString());
                    for (int l=0;l<produceArr.size();l++){
                        Long produceId=IDUtils.genItemId();
                        String produceInfoString=produceArr.get(l).toString();
                        JSONObject produceInfoObj=JSONObject.parseObject(produceInfoString);
                        String produceName=produceInfoObj.get("code").toString();
                        List<Object> forproduceId= QuerUtil("bk_set_name",produceName,"bk_parent_id",produceLineId,"cc_SetBase","bk_set_id");
                        if(forproduceId.size()==0){
                            continue;
                        }
                        produceId= (Long) forproduceId.get(0);

//
                        Object applaycationInfo=produceInfoObj.get("children");
                        if (applaycationInfo==null){
                            continue;
                        }
                        List<Object> modules=QuerUtil("bk_parent_id",produceId,"cc_ModuleBase","bk_module_name");
                        List<Object> modulesIds=QuerUtil("bk_parent_id",produceId,"cc_ModuleBase","bk_module_id");
                        JSONArray applaycationArr=JSONArray.parseArray(applaycationInfo.toString());
                        for (int m=0;m<applaycationArr.size();m++){
                            Long appId=IDUtils.genItemId();
                            String applaycationInfoString=applaycationArr.get(m).toString();
                            JSONObject applaycationobj=JSONObject.parseObject(applaycationInfoString);
                            String appName=applaycationobj.getString("code");
                            List<Object> modulesId= QuerUtil("bk_module_name",appName,"bk_parent_id",produceId,"cc_ModuleBase","bk_module_id");
                            //System.err.println(appName+":="+modulesId);
                            if(modulesId.size()==0){
                                continue;
                            }
                            appId= (Long) modulesId.get(0);


                            //更新redis 的appid
                            //1 先删除这个应用下绑定的资源

                                Query query7 = Query.query(Criteria.where("bk_obj_id").is("relation").and("module_id").is(appId));
                                List<Map> list7 = mongoTemplate.find(query7, Map.class, "cc_ObjectBase");
                                for (Map map : list7
                                ) {
                                    String bk_inst_id = map.get("bk_inst_id").toString();
                                    HttpClientUtil.doDelete(cmdbUrl+"/api/v3/inst/0/relation/" + bk_inst_id, header);
                                }

                                //2 再绑定 应用下资源
                                String redisInfo = hongmaoController.hmAboutRedis(szoneName,
                                        null, null, null, appName);
                                JSONArray jsonArray = JSONArray.parseArray(redisInfo);
                                for (int p = 0; p < jsonArray.size(); p++) {
                                    Map redisMap = (Map) jsonArray.get(p);
                                    String redisId = redisMap.get("redisId").toString();
                                    Map<String, Object> param = new HashMap<>();
                                    //坑  1 必须有bk_inst_name  唯一性
                                    param.put("bk_inst_name", IDUtils.genImageName());
                                    param.put("object_type", "redis");
                                    param.put("module_id", appId);
                                    param.put("instance_id", redisMap.get("instanceId").toString());
                                    String result = HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/inst/0/relation", JSONObject.toJSONString(param), header);
                                    log.info(result);
                                    System.err.println(result);
                                }
                                //3


                                String allResouce = hongmaoController.selectAllResource(szoneName, appName);
                                //System.err.println("="+allResouce+"=");
                                if (!"".equals(allResouce) && allResouce != null) {
                                    JSONObject jsonObject = JSONObject.parseObject(allResouce);

                                   //Rds
                                    Object rdsArr = jsonObject.get("Rds");
                                    if (rdsArr != "" && rdsArr != null) {
                                        JSONArray jsonArray1 = JSONArray.parseArray(rdsArr.toString());
                                        for (int p = 0; p < jsonArray1.size(); p++) {
                                            Map map = (Map) jsonArray1.get(p);
                                            String db_baseId = map.get("id").toString();
                                            Map<String, Object> param = new HashMap<>();
                                            //坑  1 必须有bk_inst_name  唯一性
                                            param.put("bk_inst_name", IDUtils.genImageName());
                                            param.put("object_type", "db_base");
                                            param.put("module_id", appId);
                                            param.put("instance_id",db_baseId );
                                            String result = HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/inst/0/relation", JSONObject.toJSONString(param), header);
                                            log.info(result);
                                        }
                                    }
                                   //Slb

                                    Object slbArr=jsonObject.get("Slb");
                                    if (slbArr!=""&&slbArr!=null){
                                        JSONArray jsonArray2=JSONArray.parseArray(slbArr.toString());
                                        for (int a=0;a<jsonArray.size();a++){
                                            Map map= (Map) jsonArray.get(a);
                                            String slb_id=map.get("id").toString();
                                            Map<String, Object> param = new HashMap<>();
                                            //坑  1 必须有bk_inst_name  唯一性
                                            param.put("bk_inst_name", IDUtils.genImageName());
                                            param.put("object_type", "slb");
                                            param.put("module_id", appId);
                                            param.put("instance_id",slb_id );
                                            String result = HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/inst/0/relation", JSONObject.toJSONString(param), header);
                                            log.info(result);

                                        }
                                    }



                                }


                        }


                    }

                }


            }

        }


        return "success";

    }
*/


}

                          /*  //更新redis 的appid
                            //1 先删除这个应用下绑定的redis
                            Query query7 = Query.query(Criteria.where("bk_obj_id").is("relation").and("module_id").is(appId));
                            List<Map> list7 = mongoTemplate.find(query7,Map.class,"cc_ObjectBase");
                            for (Map map:list7
                                 ) {
                                String bk_inst_id=map.get("bk_inst_id").toString();
                                HttpClientUtil.doDelete("http://cmdb2.souche-inc.com/api/v3/inst/0/relation/"+bk_inst_id,header);
                            }

                            //2 再绑定
                            String redisInfo=hongmaoController.hmAboutRedis(szoneName,
                                    null, null, null, appName);
                            JSONArray jsonArray=JSONArray.parseArray(redisInfo);
                            for(int p=0;p<jsonArray.size();p++){
                                Map redisMap= (Map) jsonArray.get(p);
                                String redisId=redisMap.get("redisId").toString();
                                Map<String,Object> param=new HashMap<>();
                                //坑  1 必须有bk_inst_name  唯一性
                                param.put("bk_inst_name",redisMap.get("redisId").toString());
                                param.put("object_type","redis");
                                param.put("module_id",appId);
                                param.put("instance_id",redisMap.get("instanceId").toString());

                                // System.err.println(JSONObject.toJSONString(param));
                               HttpClientUtil.doPostJson("http://cmdb2.souche-inc.com/api/v3/inst/0/relation", JSONObject.toJSONString(param),header);
                            }*/
