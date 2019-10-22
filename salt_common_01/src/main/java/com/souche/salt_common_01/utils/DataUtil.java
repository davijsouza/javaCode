package com.souche.salt_common_01.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.controller.HongmaoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
public class DataUtil {
    //dev
//    private static String cmdbUrl="http://paas.dasouche-inc.net";
//    private static String appToken="\"7347ede6-8f84-454b-9660-0fb2146897f8\"";

    //生产
    private static String cmdbUrl="http://paas.souche-inc.com";
    private static String appToken="\"8f955d5d-8b6b-43bb-9f2c-9375675be5dd\"";




    public static   List<Map<String, String>> saltResult(String scriptResult,Map<String,String> headers) {

        List<Map<String, String>> listForResult = new ArrayList<>();
        JSONObject ob = JSON.parseObject(scriptResult);
        JSONArray jsonarray = JSONArray.parseArray(ob.get("return").toString());

        Map<String, Map<String, String>> resultmap = (Map<String, Map<String, String>>) JSON.parse(jsonarray.get(0).toString());

        if (resultmap.size()==0){
            Map<String,String> nores=new HashMap<>();
            nores.put("server","Minion did not return. [No response]");
            resultmap.put("server",nores);
        }
        for (Map.Entry<String, Map<String, String>> entry : resultmap.entrySet()
        ) {
            Map<String, String> instanceMap = new HashMap<>();
            //instanceMap.put("instanceId", entry.getKey());
            //查询instanceName
            Map<String,String> param=new HashMap<>();
            param.put("instanceId",entry.getKey());
            String resultByName= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/resource/select",param,headers);
            Object instanceName=null;
            try{
                instanceName=JSONObject.parseObject(resultByName).get("instanceName");
            }catch (Exception e){
                instanceName=entry.getKey();
                System.err.println("没有根据instanceID找到name,所以用ID代替name");
            }
           // param.put("instanceId",instanceName.toString());

            instanceMap.put("instanceId",instanceName.toString());
            String output= JSON.toJSONString(entry.getValue() );
           // System.err.println(output);
            if(output.contains("Minion")&&output.contains("did")&&output.contains("return")&&output.contains("response")){
              //  System.err.println("com in");
                instanceMap.put( "stdout","" );
                instanceMap.put( "stderr","Minion did not return. [No response] 机器不存在或者未安装salt" );
                instanceMap.put( "pid","*" );
                instanceMap.put( "retcode","1" );
            }else {
              //  System.err.println("not com in");
                for (Map.Entry<String, String> entry2 : entry.getValue().entrySet()
                ) {
                    instanceMap.put(entry2.getKey(), String.valueOf(entry2.getValue()));
                }
            }

            listForResult.add(instanceMap);

        }
        return  listForResult;
    }


    /**
     * 当value为string 时加转义字符
     *
     * @param obj     对象模型
     * @param field1  字段1
     * @param value1  字段值
     * @param retKey   返回字段
     * @return    返回字段的集合
     */
    public static  List<Object> findInstance(String obj,String field1,Object value1,String retKey){

        return findInstance(obj,field1,value1,null,null,retKey);
    }
     public static  List<Object> findInstance(String obj,String field1,Object value1,String field2,Object value2,String retKey){
         obj="\""+obj+"\"";
         String param;
         if("开放平台".equals(value1)){

             if (field1 instanceof String) {
                 field1 = "\"" + field1 + "\"";
             }
             if (field2 instanceof String) {
                 field2 = "\"" + field2 + "\"";
             }


             if (value1 instanceof String) {
                 value1 = "\"" + value1 + "\"";
             }
             if (value2 instanceof String) {
                 value2 = "\"" + value2 + "\"";
             }
             param="{\"bk_obj_id\":"+obj+",\"condition\":{"+obj+":[{\"field\":"+field1+",\"operator\":\"$eq\",\"value\":\"开放平台\"},{\"field\":"+field2+",\"operator\":\"$eq\",\"value\":"+value2+"}]},\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":"+appToken+",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\"}";

         }else {

             if (field1 instanceof String) {
                 field1 = "\"" + field1 + "\"";
             }
             if (field2 instanceof String) {
                 field2 = "\"" + field2 + "\"";
             }


             if (value1 instanceof String) {
                 value1 = "\"" + value1 + "\"";
             }
             if (value2 instanceof String) {
                 value2 = "\"" + value2 + "\"";
             }
             param="{\"bk_obj_id\":"+obj+",\"condition\":{"+obj+":[{\"field\":"+field1+",\"operator\":\"$eq\",\"value\":"+value1+"},{\"field\":"+field2+",\"operator\":\"$eq\",\"value\":"+value2+"}]},\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":"+appToken+",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\"}";

         }
         List<Object> list=new ArrayList<>();
         String allDate=HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/search_inst/",param);
         JSONObject jsonObject;
         try{
             jsonObject=JSONObject.parseObject(allDate);
         }catch (Exception e){
             return null;
         }
         log.info(param);
         log.info(allDate);
         Object data=jsonObject.get("data");
         if(data==null)return  new ArrayList<>();
         JSONObject jsonObject1=JSONObject.parseObject(data.toString());
         String info=jsonObject1.get("info").toString();
         if(info!=null&&!info.equals("")){

             JSONArray jsonArray=JSONArray.parseArray(info);
             for (int i=0;i<jsonArray.size();i++){
                 Map map= (Map) jsonArray.get(i);
                 list.add(map.get(retKey));
             }
         }
        return list;
     }

//set
    public static  List<Object> findSet(String field1,Object value1,String retKey){

        return findSet(field1,value1,null,null,retKey);
    }
    public static  List<Object> findSet(String field1,Object value1,String field2,Object value2,String retKey){

        field1="\""+field1+"\"";
        field2="\""+field2+"\"";
        if(value1 instanceof String){
            value1 ="\""+value1+"\"";
        }
        if(value2 instanceof String){
            value2 ="\""+value2+"\"";
        }
        String param=null;
        if(field2==null) {
            param = " {\"condition\":{" + field1 + ":" + value1 + "},\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":"+appToken+",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\",\"bk_biz_id\":3}";
        }else {
            param = " {\"condition\":{" + field1 + ":" + value1 + "," + field2 + ":" + value2 + "},\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":"+appToken+",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\",\"bk_biz_id\":3}";
        }
        List<Object> list=new ArrayList<>();
        String allDate=HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/search_set/",param);
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
        return list;

    }
    //module
    public static  List<Object> findModules(Object bk_set_id,String field1,Object value1,String retKey){

        return findModules(bk_set_id,field1,value1,null,null,retKey);
    }
    public static  List<Object> findModules(Object bk_set_id,String field1,Object value1,String field2,Object value2,String retKey){

        field1="\""+field1+"\"";
        field2="\""+field2+"\"";
        if(value1 instanceof String){
            value1 ="\""+value1+"\"";
        }
        if(value2 instanceof String){
            value2 ="\""+value2+"\"";
        }
        String param=" {\"bk_set_id\":"+bk_set_id+",\"condition\":{"+field1+":"+value1+"},\"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":"+appToken+",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\",\"bk_biz_id\":3}";
        List<Object> list=new ArrayList<>();
        String allDate=HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/search_module/",param);
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
        return list;

    }

// ip find host
     public static  List<Object> findHostByIp(String ip,String retKey){

    ip="\""+ip+"\"";

    String param=" { \"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":"+appToken+",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\",\"ip\":{\n" +
            "\"data\": ["+ip+"],\"exact\": 1,\"flag\": \"bk_host_innerip|bk_host_outerip\"}}";
    List<Object> list=new ArrayList<>();
    String allDate=HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/search_host/",param);
    JSONObject jsonObject=JSONObject.parseObject(allDate);
    String data=jsonObject.get("data").toString();
    JSONObject jsonObject1=JSONObject.parseObject(data);
    String info=jsonObject1.get("info").toString();

    if(info!=null&&!info.equals("")){

        JSONArray jsonArray=JSONArray.parseArray(info);

        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject2=JSONObject.parseObject(jsonArray.get(i).toString());
           // String host=jsonObject2.get("host").toString();
            Map map= (Map) jsonObject2.get("host");
            list.add(map.get(retKey));
        }
    }
    return list;

}
//  find by 条件
     public static  List<Object> findHostByLike(String obj,String field1,Object value1,String retKey){

         obj = "\"" + obj + "\"";
         if(field1 instanceof String) {
             field1="\""+field1+"\"";
         }
         if(value1 instanceof String){
             value1 ="\""+value1+"\"";
         }


    String param=" { \"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":"+appToken+",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\",\n" +
            " \t  \"condition\": [{\"bk_obj_id\": "+obj+",\"condition\": [{\"field\": "+field1+",\"operator\": \"$eq\",\"value\": "+value1+"}]}]}";

    List<Object> list=new ArrayList<>();
    String allDate=HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/search_host/",param);
    JSONObject jsonObject=JSONObject.parseObject(allDate);
    Object data=jsonObject.get("data");
    if (data==null)return list;
    JSONObject jsonObject1=JSONObject.parseObject(data.toString());
    String info=jsonObject1.get("info").toString();

    if(info!=null&&!info.equals("")){

        JSONArray jsonArray=JSONArray.parseArray(info);

        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject2=JSONObject.parseObject(jsonArray.get(i).toString());
           // String host=jsonObject2.get("host").toString();
            Map map= (Map) jsonObject2.get("host");
            list.add(map.get(retKey));
        }
    }
    return list;

}

    /**
     *  创建实例
     * @param obj
     * @return
     */
     public static  String create_inst(String obj,String name,Object parentId){
         name="\""+name+"\"";
         obj="\""+obj+"\"";

    String param=" { \"bk_app_code\":\"bk_cmdb\",\"bk_app_secret\":"+appToken+",\"bk_supplier_account\":\"0\",\"bk_username\":\"admin\",\n" +
            " \"bk_obj_id\":"+obj+",\"bk_inst_name\":"+name+",\"bk_parent_id\":"+parentId+" }";

    List<Object> list=new ArrayList<>();
    String allDate=HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/create_inst/",param);
    log.info(allDate);
    JSONObject jsonObject=JSONObject.parseObject(allDate);
    Object data=jsonObject.get("data");
    if(data==null)return null;
    JSONObject jsonObject1=JSONObject.parseObject(data.toString());
    String bk_parent_id=jsonObject1.get("bk_inst_id").toString();
    return bk_parent_id;
}

    /**
     * 删除实例
     * @param obj
     * @return
     */
   public static  String delete_inst(String obj,Object bk_insert_id){
         obj="\""+obj+"\"";
    String param="{\"bk_app_code\": \"bk_cmdb\",\"bk_app_secret\": "+appToken+",\"bk_supplier_account\": \"0\",\"bk_username\": \"admin\",\n" +
            "\t\"bk_obj_id\":"+obj+",\"bk_inst_id\": "+bk_insert_id+"}";
    String allDate=HttpClientUtil.doPostJson(cmdbUrl+"/api/c/compapi/v2/cc/delete_inst/",param);
    return allDate;
}



}
