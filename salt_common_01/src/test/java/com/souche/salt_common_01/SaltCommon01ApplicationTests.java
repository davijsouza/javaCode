package com.souche.salt_common_01;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.souche.salt_common_01.controller.ClientController;
import com.souche.salt_common_01.controller.HongmaoController;
import com.souche.salt_common_01.controller.TimeJobController;
import com.souche.salt_common_01.entity.Current;
import com.souche.salt_common_01.entity.Job;
import com.souche.salt_common_01.entity.JobExample;
import com.souche.salt_common_01.entity.Scripts;
import com.souche.salt_common_01.mapper.*;
import com.souche.salt_common_01.service.JobService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.DataUtil;
import com.souche.salt_common_01.utils.HttpClientUtil;
import com.souche.salt_common_01.utils.IDUtils;
import com.souche.salt_common_01.utils.MongoDBUtil;
import netscape.javascript.JSObject;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =SaltCommon01Application.class)
public class SaltCommon01ApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScriptsService scriptsService;

    @Autowired
    private ClientController clientController;
    @Autowired
    private JobsLogMapper jobsLogMapper;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private MySqlMapper mySqlMapper;
    @Autowired
    private JobService jobService;
    @Autowired
    private TimeJobController timeJobController;
    @Autowired
    private CurrentMapper currentMapper;
    @Autowired
    private ExecuteLogMapper executeLogMapper;
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private HongmaoController hongmaoController;

   // private  String cmdbUrl="http://cmdb2.souche-inc.com";
    //private  String cmdbUrl="http://paas.dasouche-inc.net";
    private  String cmdbUrl="http://paas.dasouche-inc.net";
    //private  String bktoken="bk_token=UC8OKDqlggfUMXWjSeUCvGfoRj4fbtkeb-r_-qChXTs";
     private  String bktoken="69bTlcDQ-mL2eIsxcpU9VbZl23QKNTr502_vS3ouFcY;";
//redis attr
    @Test
    public void contextLoads2() {
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bktoken);
        Map<String,Object>  param=new HashMap<>();
        param.put("bk_obj_id","redis");
        param.put("bk_supplier_account","0");
        param.put("bk_property_type","longchar");

        param.put("bk_property_id","id");
        param.put("bk_property_name","id");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instanceid");
        param.put("bk_property_name","instanceid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instancename");
        param.put("bk_property_name","instancename");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","connectiondomain");
        param.put("bk_property_name","connectiondomain");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","port");
        param.put("bk_property_name","port");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instanceclass");
        param.put("bk_property_name","instanceclass");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instanceclassname");
        param.put("bk_property_name","instanceClassName");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","engineversion");
        param.put("bk_property_name","engineVersion");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instancestatus");
        param.put("bk_property_name","instanceStatus");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","privateip");
        param.put("bk_property_name","privateip");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","instancetype");
        param.put("bk_property_name","instanceType");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","tag");
        param.put("bk_property_name","tag");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);
//date
        param.put("bk_property_id","createtime");
        param.put("bk_property_name","createTime");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","endtime");
        param.put("bk_property_name","endTime");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);


//   int
        Map<String,String> option=new HashMap<String,String>();
        param.put("bk_property_type","int");
        param.put("option",option);
        param.put("bk_property_id","capacity");
        param.put("bk_property_name","capacity");
        System.err.println(JSONObject.toJSONString(param));
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","bandwidth");
        param.put("bk_property_name","bandwidth");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);


        param.put("bk_property_id","connections");
        param.put("bk_property_name","connections");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","qps");
        param.put("bk_property_name","qps");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);



    }
    @Test
    public void contextLoads3() {
       String allData=  hongmaoController.hmSelectLike("souche",null,null,null,null);
       JSONArray jsonArray= JSONArray.parseArray(allData);
       Set<String> set=new HashSet<>();
       for (int i=0;i<jsonArray.size();i++){
          Map map= (Map) jsonArray.get(i);
          set.add(map.get("instanceId").toString());
       }
        List<String> ips=new ArrayList<>();
        for (String instanceId:set
             ) {
           // System.err.println(instanceId);
            String serverInfo=hongmaoController.hmQuerySelectMeche(instanceId,null);
           // System.err.println(serverInfo);
            if("[]".equals(serverInfo)){
                continue;
            }
            JSONArray jsonArray1= JSONArray.parseArray(serverInfo);
            //System.err.println(jsonArray1);
            String  serverinfo1=jsonArray1.get(0).toString();
             JSONObject jsonObject=JSONObject.parseObject(serverinfo1);
             String  privateAddress= (String) jsonObject.get("privateAddress");
             String  innerAddress= (String) jsonObject.get("innerAddress");
              if ("[]".equals(privateAddress)){
                 String in= innerAddress.replaceAll("\\[|\\]","");
                  ips.add(in);
           }else {
                 String pr= privateAddress.replaceAll("\\[|\\]","");
                  ips.add(pr);
           }

        }
        System.err.println(ips);
     }
    @Test
    public void contextLoads4() {
      /*  Query query = Query.query(Criteria.where("bk_obj_id").is("relation").and("module_id").is(18340));
        List<Map> list2 = mongoTemplate.find(query,Map.class,"cc_ObjectBase");
        System.err.println(list2.get(0).get("bk_inst_id"));*/
        Map<String, String> header = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        Map<String,Object> szonemap =new HashMap<>();
        szonemap.put("bk_parent_id",3);
        szonemap.put("bk_supplier_account","0");
        szonemap.put("bk_inst_id",23333);
        szonemap.put("bk_biz_id",3);
        szonemap.put("bk_inst_name","szoneName");
        header.put("Cookie", "bk_token=IjlEXSsDx4p8vbtjgmmHO3yEq0qAXCxwTDC6gBU_KFI;cc3=MTU2NDU2NjY1OHxOd3dBTkRkSk1qVklXVkZHU0UxSE5VazFSVFZWUnpSRldsUklOMHBCV1RJelZsVllTRW8wUkVOUVVUTlRVbFZEVDFWVFIwZFFNMEU9fCymg7juBtKeDSMXmXc9jIq7HEDWra1dxHY7p6vnAM3X");
      //  HttpClientUtil.doDelete(cmdbUrl+"/api/v3/inst/0/relation/1834",header);
        //System.err.println(JSONObject.toJSONString(szonemap));

       HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/inst/0/szone", JSONObject.toJSONString(szonemap), header);

    }
/*新增db_base attr*/
    @Test
    public void contextLoads5() {
    Map<String,String> header=new HashMap<>();
    header.put("Cookie",bktoken);
    Map<String,Object>  param=new HashMap<>();
    param.put("bk_obj_id","db_base");
    param.put("bk_supplier_account","0");
    param.put("bk_property_type","longchar");

    param.put("bk_property_id","id");
    param.put("bk_property_name","id");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","db_clusterid");
    param.put("bk_property_name","db_clusterid");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","dbinstanceid");
    param.put("bk_property_name","dbinstanceid");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","connectionstring");
    param.put("bk_property_name","connectionstring");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","port");
    param.put("bk_property_name","port");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","masterinstanceid");
    param.put("bk_property_name","masterinstanceid");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","readonlydbinstanceid");
    param.put("bk_property_name","readonlydbinstanceid");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","discription");
    param.put("bk_property_name","discription");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);



    param.put("bk_property_id","creationtime");
    param.put("bk_property_name","creationtime");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","expiretime");
    param.put("bk_property_name","instancetype");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","cpu");
    param.put("bk_property_name","cpu");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","dbinstancetype");
    param.put("bk_property_name","dbinstancetype");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","dbinstancestatus");
    param.put("bk_property_name","dbinstancestatus");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","engine");
    param.put("bk_property_name","engine");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","engineversion");
    param.put("bk_property_name","engineversion");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);


    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","dbbaseid");
    param.put("bk_property_name","dbbaseid");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);


    param.put("bk_property_id","dbbasename");
    param.put("bk_property_name","dbbasename");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","szoneid");
    param.put("bk_property_name","szoneid");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","szonename");
    param.put("bk_property_name","szonename");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","applicationid");
    param.put("bk_property_name","applicationid");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","applicationname");
    param.put("bk_property_name","applicationname");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","dbdescription");
    param.put("bk_property_name","dbdescription");
       System.err.println(HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr", JSONObject.toJSONString(param), header));
//bool
       param.put("bk_property_type","bool");
       param.put("bk_property_id","cluster");
       param.put("bk_property_name","cluster");
       HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);
//   int
    Map<String,String> option=new HashMap<String,String>();
    param.put("bk_property_type","int");
    param.put("option",option);
    param.put("bk_property_id","count");
    param.put("bk_property_name","count");
   // System.err.println(JSONObject.toJSONString(param));
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","maxconnections");
    param.put("bk_property_name","maxconnections");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);


    param.put("bk_property_id","maxiops");
    param.put("bk_property_name","maxiops");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

    param.put("bk_property_id","dbinstancememory");
    param.put("bk_property_name","dbinstancememory");
    HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);



}
    /*新增relation attr*/
    @Test
    public void contextLoads51() {
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", bktoken);
        Map<String, Object> param = new HashMap<>();
        param.put("bk_obj_id", "relation");
        param.put("bk_supplier_account", "0");
        param.put("bk_property_type", "longchar");

        param.put("bk_property_id", "object_type");
        param.put("bk_property_name", "模型标识");
        System.err.println(HttpClientUtil.doPostJson("http://cmdb2.souche-inc.com/api/v3/object/attr", JSONObject.toJSONString(param), header));


        param.put("bk_property_id", "instance_id");
        param.put("bk_property_name", "redis-instanceId");
        HttpClientUtil.doPostJson("http://cmdb2.souche-inc.com/api/v3/object/attr", JSONObject.toJSONString(param), header);

        Map<String,String> option=new HashMap<String,String>();
        param.put("bk_property_type","int");
        param.put("option",option);
        param.put("bk_property_id", "module_id");
        param.put("bk_property_name", "模块id");
        HttpClientUtil.doPostJson("http://cmdb2.souche-inc.com/api/v3/object/attr", JSONObject.toJSONString(param), header);

    }

    /*同步db_base*/
    @Test
    public void contextLoads6() {
        String redisData=hongmaoController.hmSelectAlldb();
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bktoken);
        // System.err.println(HttpClientUtil.doGet(cmdbUrl+"/api/v3/userapi/data/2/bl0fdgo2a8on3bdop6fg/0/100", null, header));
        JSONArray jsonArray=JSONArray.parseArray(redisData);
        List<String> instaceidList=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            Map myselInfo=(Map)jsonArray.get(i);
            myselInfo.put("dbdescription",myselInfo.get("dbInstanceDescription"));
            myselInfo.remove("dbInstanceDescription");
            myselInfo.put("bk_inst_name",myselInfo.get("dbBaseId").toString());

            instaceidList.add(myselInfo.get("dbBaseId").toString());
//            Query query = Query.query(Criteria.where("dbbaseid").is(myselInfo.get("dbBaseId")));
//            mongoTemplate.remove(query,"cc_ObjectBase");
            //           System.err.println(JSONObject.toJSONString(myselInfo).toLowerCase());
            String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/inst/0/db_base", JSONObject.toJSONString(myselInfo).toLowerCase(),header);
            //   System.err.println(result);


        }
        Query query2 = Query.query(Criteria.where("bk_obj_id").is("db_base"));
        List<String> result=  mongoTemplate.find(query2,String.class,"cc_ObjectBase");
        for (String info:result
        ) {
            JSONObject jsonObject=JSONObject.parseObject(info);
            Object instanceid=jsonObject.get("dbbaseid");
            if(!instaceidList.contains(instanceid)){
                Query query3 = Query.query(Criteria.where("dbbaseid").is(instanceid).and("bk_obj_id").is("db_base"));
                mongoTemplate.remove(query3,"cc_ObjectBase");
            }

        }



    }

//    新增node  attr
    @Test
    public void contextLoads7() {
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bktoken);
        Map<String,Object>  param=new HashMap<>();
        param.put("bk_obj_id","db_node");
        param.put("bk_supplier_account","0");
        param.put("bk_property_type","longchar");

        param.put("bk_property_id","id");
        param.put("bk_property_name","id");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","db_clusterid");
        param.put("bk_property_name","db_clusterid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","dbinstanceid");
        param.put("bk_property_name","dbinstanceid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","connectionstring");
        param.put("bk_property_name","connectionstring");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","port");
        param.put("bk_property_name","port");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);


        param.put("bk_property_id","masterinstanceid");
        param.put("bk_property_name","masterinstanceid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","readonlydbinstanceid");
        param.put("bk_property_name","readonlydbinstanceid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","discription");
        param.put("bk_property_name","discription");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);



        param.put("bk_property_id","creationtime");
        param.put("bk_property_name","creationtime");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","expiretime");
        param.put("bk_property_name","instancetype");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","cpu");
        param.put("bk_property_name","cpu");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","dbinstancetype");
        param.put("bk_property_name","dbinstancetype");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","dbinstancestatus");
        param.put("bk_property_name","dbinstancestatus");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","engine");
        param.put("bk_property_name","engine");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","engineversion");
        param.put("bk_property_name","engineversion");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","dbdescription");
        param.put("bk_property_name","dbdescription");
        System.err.println(HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr", JSONObject.toJSONString(param), header));



//bool
        param.put("bk_property_type","bool");
        param.put("bk_property_id","cluster");
        param.put("bk_property_name","cluster");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);
//   int
        Map<String,String> option=new HashMap<String,String>();
        param.put("bk_property_type","int");
        param.put("option",option);
        param.put("bk_property_id","count");
        param.put("bk_property_name","count");
        // System.err.println(JSONObject.toJSONString(param));
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","maxconnections");
        param.put("bk_property_name","maxconnections");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);


        param.put("bk_property_id","maxiops");
        param.put("bk_property_name","maxiops");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","dbinstancememory");
        param.put("bk_property_name","dbinstancememory");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);



    }
  // 同步 node 实例
    @Test
    public void c8(){
        String redisData=hongmaoController.hmSelectAlldbNode();
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bktoken);
        // System.err.println(HttpClientUtil.doGet(cmdbUrl+"/api/v3/userapi/data/2/bl0fdgo2a8on3bdop6fg/0/100", null, header));
        JSONArray jsonArray=JSONArray.parseArray(redisData);
        List<String> instaceidList=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            Map myselInfo=(Map)jsonArray.get(i);
            myselInfo.put("dbdescription",myselInfo.get("dbInstanceDescription"));
            myselInfo.remove("dbInstanceDescription");
            myselInfo.put("bk_inst_name",myselInfo.get("id").toString());

            instaceidList.add(myselInfo.get("id").toString());
            String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/inst/0/db_node", JSONObject.toJSONString(myselInfo).toLowerCase(),header);
            //   System.err.println(result);
        }
        Query query2 = Query.query(Criteria.where("bk_obj_id").is("db_node"));
        List<String> result=  mongoTemplate.find(query2,String.class,"cc_ObjectBase");
        for (String info:result
        ) {
            JSONObject jsonObject=JSONObject.parseObject(info);
            Object instanceid=jsonObject.get("id");
            if(!instaceidList.contains(instanceid)){
                Query query3 = Query.query(Criteria.where("id").is(instanceid).and("bk_obj_id").is("db_node"));
                mongoTemplate.remove(query3,"cc_ObjectBase");
            }

        }
    }

    @Test
    public void c9(){

  /*    String allResouce=hongmaoController.selectAllResource("souche", "lantu");
        //System.err.println("="+allResouce+"=");
        if ( !"".equals(allResouce)&&allResouce!=null){
            JSONObject jsonObject=JSONObject.parseObject(allResouce);
            Object slbArr=jsonObject.get("Slb");
            if (slbArr!=""&&slbArr!=null){
                JSONArray jsonArray=JSONArray.parseArray(slbArr.toString());
                for (int i=0;i<jsonArray.size();i++){
                    Map map= (Map) jsonArray.get(i);
                    String id=map.get("id").toString();

                }
            }
        }
*/

        String redisData=hongmaoController.hmSelectAllUser();
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bktoken);
        // System.err.println(HttpClientUtil.doGet(cmdbUrl+"/api/v3/userapi/data/2/bl0fdgo2a8on3bdop6fg/0/100", null, header));
        JSONArray jsonArray=JSONArray.parseArray(redisData);
        List<String> instaceidList=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            Map myselInfo=(Map)jsonArray.get(i);
           // System.err.println(myselInfo);
            myselInfo.put("bk_inst_name",myselInfo.get("id").toString());
            instaceidList.add(myselInfo.get("id").toString());
            Query query = Query.query(Criteria.where("id").is(myselInfo.get("id")).and("bk_obj_id").is("user"));
            mongoTemplate.remove(query,"cc_ObjectBase");
            String result= HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/inst/0/user", JSONObject.toJSONString(myselInfo).toLowerCase(),header);
            System.err.println(result);
        }

        Query query2 = Query.query(Criteria.where("bk_obj_id").is("user"));
        List<String> result=  mongoTemplate.find(query2,String.class,"cc_ObjectBase");
        for (String info:result
        ) {
            JSONObject jsonObject=JSONObject.parseObject(info);
            Object instanceid=jsonObject.get("id");
            if(!instaceidList.contains(instanceid)){
                Query query3 = Query.query(Criteria.where("id").is(instanceid).and("bk_obj_id").is("user"));
                mongoTemplate.remove(query3,"cc_ObjectBase");
            }

        }

    }

// slb 新增attr
    @Test
    public void c10(){
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bktoken);
        Map<String,Object>  param=new HashMap<>();
        param.put("bk_obj_id","slb");
        param.put("bk_supplier_account","0");
        param.put("bk_property_type","longchar");

        param.put("bk_property_id","id");
        param.put("bk_property_name","id");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","loadbalancerid");
        param.put("bk_property_name","loadbalancerid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","loadbalancername");
        param.put("bk_property_name","loadbalancername");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","loadbalancerstatus");
        param.put("bk_property_name","loadbalanstatus");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","address");
        param.put("bk_property_name","address");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","addresstype");
        param.put("bk_property_name","addresstype");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","regionid");
        param.put("bk_property_name","regionid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","vpcid");
        param.put("bk_property_name","vpcid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);


        param.put("bk_property_id","networktype");
        param.put("bk_property_name","networktype");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","masterzoneid");
        param.put("bk_property_name","masterzoneid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","slavezoneid");
        param.put("bk_property_name","slavezoneid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);


        param.put("bk_property_id","internetchargetype");
        param.put("bk_property_name","internetchargetype");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","createtime");
        param.put("bk_property_name","createtime");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","endtime");
        param.put("bk_property_name","endtime");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","paytype");
        param.put("bk_property_name","paytype");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","resourcegroupid");
        param.put("bk_property_name","resourcegroupid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

       param.put("bk_property_id","listenerport");
        param.put("bk_property_name","listenerport");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

       param.put("bk_property_id","backendserverids");
        param.put("bk_property_name","backendserverids");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

       param.put("bk_property_id","vswitchid");
        param.put("bk_property_name","vswitchid");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);



    }
    // user add attr
    @Test
    public void c11(){
        Map<String,String> header=new HashMap<>();
        header.put("Cookie",bktoken);
        Map<String,Object>  param=new HashMap<>();
        param.put("bk_obj_id","user");
        param.put("bk_supplier_account","0");
        param.put("bk_property_type","longchar");

        param.put("bk_property_id","id");
        param.put("bk_property_name","id");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","name");
        param.put("bk_property_name","name");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","email");
        param.put("bk_property_name","email");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","cnname");
        param.put("bk_property_name","cnName");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","tag2");
        param.put("bk_property_name","tag2");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","tag3");
        param.put("bk_property_name","tag3");
        HttpClientUtil.doPostJson(cmdbUrl+"/api/v3/object/attr",JSONObject.toJSONString(param),header);

        param.put("bk_property_id","kid");
        param.put("bk_property_name","kid");
        System.err.println(HttpClientUtil.doPostJson(cmdbUrl + "/api/v3/object/attr", JSONObject.toJSONString(param), header));


    }
    @Test
    public void c12(){
        long hostid=261;
        long moduleid=3039;
        //String param="{\"bk_biz_id\":3,\"bk_module_id\":"+moduleid+",\"bk_set_id\":"+hostid+",\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":\"c433c4ef-4edf-408e-bdd8-e77a8c81c541\",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\"}";
       // System.err.println(HttpClientUtil.doPostJson(cmdbUrl + "/api/c/compapi/v2/cc/delete_module/", param));
        System.err.println(DataUtil.findModules(542,"bk_module_name","flume_to_es","bk_module_id"));

    }
       /* String obj="\"redis\"";
        String field1="\"bk_inst_name\"";
        Object value1="\"redis-008f1383451642be\"";
        String field2="\"capacity\"";
        Object value2=1024;
        String retKey="bk_inst_name";


        // String param="{\"bk_obj_id\":"+obj+",\"condition\":{"+obj+":[{\"field\":"+field1+",\"operator\":\"$eq\",\"value\":"+value1+"}]},\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":\"c433c4ef-4edf-408e-bdd8-e77a8c81c541\",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\"}";
        String param="{\"bk_obj_id\":"+obj+",\"condition\":{"+obj+":[{\"field\":"+field1+",\"operator\":\"$eq\",\"value\":"+value1+"},{\"field\":"+field2+",\"operator\":\"$eq\",\"value\":"+value2+"}]},\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":\"c433c4ef-4edf-408e-bdd8-e77a8c81c541\",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\"}";
        List<Object> list=new ArrayList<>();
        String allDate=HttpClientUtil.doPostJson("http://paas.dasouche-inc.net/api/c/compapi/v2/cc/search_inst/",param);
        JSONObject jsonObject=JSONObject.parseObject(allDate);
        String data=jsonObject.get("data").toString();
        JSONObject jsonObject1=JSONObject.parseObject(data);
        String info=jsonObject1.get("info").toString();
        if(info!=null&&!info.equals("")){

            JSONArray jsonArray=JSONArray.parseArray(info);
            for (int i=0;i<jsonArray.size();i++){
            Map map= (Map) jsonArray.get(i);
            list.add(map.get(retKey));
            }
        }
        System.err.println(list);
    }*/

    }




