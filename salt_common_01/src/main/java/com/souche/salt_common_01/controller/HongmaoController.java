package com.souche.salt_common_01.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.entity.SaltMche;
import com.souche.salt_common_01.entity.SaltMcheExample;
import com.souche.salt_common_01.mapper.SaltMcheMapper;
import com.souche.salt_common_01.service.SaltMcheService;
import com.souche.salt_common_01.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spi.service.contexts.SecurityContext;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
public class HongmaoController {
     @Autowired
     private SaltMcheMapper saltMcheMapper;

    @Value("${lantufe}")
    private String lantufe;
    private static String returntoken;
    private static int num=0;

  public static  String getReturntoken(){
      if(num%20==0){
          returntoken=null;
      }
      if (returntoken == null) {
          Map<String,String> params=new HashMap<>();
          params.put("client_id", "hongmao");
          params.put("username", "app_lantu");
          params.put("password", "N4i3wFgKJdSGYHT");
          params.put("grant_type", "refresh_token");
          params.put("scope", "offline_access");
          params.put("refresh_token", "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI5OTUxMWVlMS1hNjU2LTQ3OGEtOGM1ZS0zMGE4NGE3ZTczM2EifQ.eyJqdGkiOiJiMTc2MmQ0OS03NTI4LTQxMDgtOGQyMC1jMjIwMmRkNTEyZmMiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTUyNjMyMTk4LCJpc3MiOiJodHRwczovL2F1dGguc291Y2hlLWluYy5jb20vYXV0aC9yZWFsbXMvaG9uZ21hbyIsImF1ZCI6Imh0dHBzOi8vYXV0aC5zb3VjaGUtaW5jLmNvbS9hdXRoL3JlYWxtcy9ob25nbWFvIiwic3ViIjoiNmQxZDJjN2YtMmY0Ni00NzhkLTlhZDctMzE2M2NjNzRmZWEyIiwidHlwIjoiT2ZmbGluZSIsImF6cCI6ImhvbmdtYW8iLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJhOWY5ODQzMy0zZDZmLTRjNzYtOGFjMy02YzE1ZTg2ZjVlMzAiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9mZmxpbmVfYWNjZXNzIHByb2ZpbGUgZW1haWwifQ.BEWe2F97sER6CAUQxmbrROVbbFapoLnzBj3C6NE-WD0");
          params.put("client_secret", "29f40032-b025-4458-8021-03f0b29d9b1e");

          String result= HttpClientUtil.doPost("https://auth.souche-inc.com/auth/realms/hongmao/protocol/openid-connect/token",params);
          JSONObject jsonObject=JSONObject.parseObject(result);
          try{
              returntoken=jsonObject.get("access_token").toString();
          }catch (Exception e){
              System.err.println("得到access_token 失败");
              e.printStackTrace();
              getReturntoken();
          }


      }
      return  returntoken;
  }
    @GetMapping("/lantu/salt/hmStableToken")
    public String hmStableToken(){
        Map<String,String> params=new HashMap<>();
        params.put("client_id", "hongmao");
        params.put("username", "app_lantu");
        params.put("password", "N4i3wFgKJdSGYHT");
        params.put("grant_type", "refresh_token");
        params.put("scope", "offline_access");

        params.put("refresh_token", "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI5OTUxMWVlMS1hNjU2LTQ3OGEtOGM1ZS0zMGE4NGE3ZTczM2EifQ.eyJqdGkiOiJiMTc2MmQ0OS03NTI4LTQxMDgtOGQyMC1jMjIwMmRkNTEyZmMiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTUyNjMyMTk4LCJpc3MiOiJodHRwczovL2F1dGguc291Y2hlLWluYy5jb20vYXV0aC9yZWFsbXMvaG9uZ21hbyIsImF1ZCI6Imh0dHBzOi8vYXV0aC5zb3VjaGUtaW5jLmNvbS9hdXRoL3JlYWxtcy9ob25nbWFvIiwic3ViIjoiNmQxZDJjN2YtMmY0Ni00NzhkLTlhZDctMzE2M2NjNzRmZWEyIiwidHlwIjoiT2ZmbGluZSIsImF6cCI6ImhvbmdtYW8iLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJhOWY5ODQzMy0zZDZmLTRjNzYtOGFjMy02YzE1ZTg2ZjVlMzAiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9mZmxpbmVfYWNjZXNzIHByb2ZpbGUgZW1haWwifQ.BEWe2F97sER6CAUQxmbrROVbbFapoLnzBj3C6NE-WD0");
        params.put("client_secret", "29f40032-b025-4458-8021-03f0b29d9b1e");

       String result= HttpClientUtil.doPost("https://auth.souche-inc.com/auth/realms/hongmao/protocol/openid-connect/token",params);
        JSONObject jsonObject=JSONObject.parseObject(result);

       return jsonObject.get("access_token").toString();
    }
//获得调用蓝兔接口的token
    @GetMapping("/lantu/ltToken")
    public String ltToken(@RequestParam String username,
                          @RequestParam String password
    ){
        Map<String,String> params=new HashMap<>();
        params.put("client_id", "hongmao");
        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", "password");
        params.put("scope", "offline_access");
        params.put("client_secret", "29f40032-b025-4458-8021-03f0b29d9b1e");

       String result= HttpClientUtil.doPost("https://auth.souche-inc.com/auth/realms/hongmao/protocol/openid-connect/token",params);
       try{
           JSONObject jsonObject=JSONObject.parseObject(result);
           if (jsonObject==null){
               return "username or  password  错误";
           }else {
               Map<String,String> params2=new HashMap<>();
               params2.put("client_id", "hongmao");
               params2.put("grant_type", "refresh_token");
              // params.put("scope", "offline_access");
               //String a=jsonObject.get("refresh_token").toString();
               params2.put("refresh_token", jsonObject.get("refresh_token").toString());
               params2.put("client_secret", "29f40032-b025-4458-8021-03f0b29d9b1e");
               String result2= HttpClientUtil.doPost("https://auth.souche-inc.com/auth/realms/hongmao/protocol/openid-connect/token",params2);
               JSONObject jsonObject2=JSONObject.parseObject(result2);

               return "bearer "+jsonObject2.get("access_token").toString();
           }

       }catch (Exception e){
           return "username or  password  错误";
       }



    }

    @GetMapping("/lantu/salt/hmSelectAll")
    public String hmSelectAllData(){
         String token="bearer "+hmStableToken();
        //System.err.println(token);
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        String result= HttpClientUtil.doGet("http://hongmao.souche-inc.com/aliyun/szone/selectAllData",null,headers);

         return result;
    }

    @GetMapping("/lantu/salt/hmSelectAllRedis")
    public String hmSelectAllRedis(){
        String token="bearer "+hmStableToken();
        //System.err.println(token);
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        String result= HttpClientUtil.doGet("http://hongmao.souche-inc.com/aliyun/redis/selectAll",null,headers);
        return result;
    }
    /*db_base*/
    @GetMapping("/lantu/salt/hmSelectAlldb")
    public String hmSelectAlldb(){
        String token="bearer "+hmStableToken();
        //System.err.println(token);
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        String result= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/db_base/selectAll",null,headers);
        return result;
    }

    @GetMapping("/lantu/salt/hmSelectAlldbNode")
    public String hmSelectAlldbNode(){
        String token="bearer "+hmStableToken();
        //System.err.println(token);
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        String result= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/db_node/selectAll",null,headers);
        return result;
    }
    @GetMapping("/lantu/salt/hmSelectAllSlb")
    public String hmSelectAllSlb(){
        String token="bearer "+hmStableToken();
        //System.err.println(token);
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        String result= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/slb/selectAll",null,headers);
        return result;
    }
    @GetMapping("/lantu/salt/hmSelectAllUser")
    public String hmSelectAllUser(){
        String token="bearer "+hmStableToken();
        //System.err.println(token);
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        String result= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/user/user",null,headers);
        return result;
    }
    @GetMapping("/lantu/salt/selectAllResource")
    public String selectAllResource(String szoneName,String appName){
        String token="bearer "+hmStableToken();
        Map<String,String> headers=new HashMap<>();
        Map<String,String> param=new HashMap<>();
        headers.put("Authorization",token);
        param.put("sname",szoneName);
        param.put("appname",appName);
        String result= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/about/selectAllResource",param,headers);
        return result;
    }





    //查询所有机器
    @GetMapping("/lantu/salt/hmSelectAllMeche")
    public String hmSelectAllJiqi(){
         String token="bearer "+hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        String result= HttpClientUtil.doGet("http://hongmao.souche-inc.com/aliyun/resource/selectAll?instanceName=sc-prod-wj-saas-nginx-01",null,headers);
        return result;
    }
    //条件查询机器
    @GetMapping("/lantu/salt/hmQueryMeche")
    public String hmQuerySelectMeche(String instanceId,String instanceName){

        String token="bearer "+hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);

        Map<String,String> params=new HashMap<>();
        params.put("instanceId",instanceId);
        if(instanceName!=null){
            params.put("instanceName",instanceName);
        }
        String result= HttpClientUtil.doGet("http://hongmao.souche-inc.com/aliyun/resource/selectLikeQuery",params,headers);


        return result;
    }

    public Map<String,String> getHongMaoHeader(){
        String token="bearer "+hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        return headers;
    }

    //根据多个ip查询机器

    @GetMapping("/lantu/salt/hmResourceArrIP")
    public List<Object> hmResourceArrIP(String[] listIp){
        List<Object> list=new ArrayList<>();
       for (int i=0;i<listIp.length;i++){
          String result= hmResourceIP(listIp[i].trim());
          if(result==null||result.equals("[]")||result.equals(" ")){
              log.info("返回结果null");
              continue;
          }
           log.info("result===="+result);
           JSONArray jsonArray=  JSONArray.parseArray(result);
           Map<String,String> oneMcheInfo= (Map<String, String>) jsonArray.get(0);

           String privateIp=oneMcheInfo.get("privateAddress").replaceAll("\\[|\\]","");
           String innerIp=oneMcheInfo.get("innerAddress").replaceAll("\\[|\\]","");
           SaltMcheExample saltMcheExample=new SaltMcheExample();
           saltMcheExample.or().andIpEqualTo(privateIp);
           List<SaltMche> listMches=saltMcheMapper.selectByExample(saltMcheExample);
           if (listMches!=null&&listMches.size()>0){

               oneMcheInfo.put("online","yes");
           }else {
               SaltMcheExample saltMcheExample2=new SaltMcheExample();
               saltMcheExample2.or().andIpEqualTo(innerIp);
               List<SaltMche> listMches2=saltMcheMapper.selectByExample(saltMcheExample2);

               if(listMches2!=null&&listMches2.size()>0){

                   oneMcheInfo.put("online","yes");
               }else {

                   oneMcheInfo.put("online","no");
               }

           }

           list.add(oneMcheInfo);
       }
        return list;
    }
    //条件查询机器
    @GetMapping("/lantu/salt/hmArrayMeche")
    public List<Object> hmArrayMeche(String[] instanceIds){

        List<Object> list=new ArrayList<>();
        for (String id:instanceIds
             ) {
           //  System.err.println(id);
          String result=  hmQuerySelectMeche(id,null);
             log.info("result===="+result);
           JSONArray jsonArray=  JSONArray.parseArray(result);
            Map<String,String> oneMcheInfo= (Map<String, String>) jsonArray.get(0);
            String privateIp=oneMcheInfo.get("privateAddress").replaceAll("\\[|\\]","");
            String innerIp=oneMcheInfo.get("innerAddress").replaceAll("\\[|\\]","");
            //System.err.println(result);
            SaltMcheExample saltMcheExample=new SaltMcheExample();
            saltMcheExample.or().andIpEqualTo(privateIp);
            List<SaltMche> listMches=saltMcheMapper.selectByExample(saltMcheExample);
          if (listMches!=null&&listMches.size()>0){
              oneMcheInfo.put("online","yes");
          }else {
              SaltMcheExample saltMcheExample2=new SaltMcheExample();
              saltMcheExample2.or().andIpEqualTo(innerIp);
              List<SaltMche> listMches2=saltMcheMapper.selectByExample(saltMcheExample2);
              if(listMches2!=null&&listMches2.size()>0){
                  oneMcheInfo.put("online","yes");
              }else {
                  oneMcheInfo.put("online","no");
              }

          }

          list.add(oneMcheInfo);
        }
         System.err.println(list.size());
          return list;
    }








    @GetMapping("/lantu/salt/hmSelectByAll")
    public String hmSelectByAll(
                                @RequestParam(required = false) String szoneName,
                                @RequestParam(required = false) String applicationName,
                                @RequestParam(required = false) String instanceName

                                ){
         //String token="bearer "+hmStableToken();
         String token="bearer "+getReturntoken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        Map<String,String> params=new HashMap<>();
      if(instanceName!=null&&instanceName!=""){
          params.put("instanceName",instanceName);
      }
      if(szoneName!=null&&szoneName!=""){
          params.put("szoneName",szoneName);
      }
      if(applicationName!=null&&applicationName!=""){
          params.put("applicationName",applicationName);
      }
        String result= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/about/selectByAll",params,headers);
      //System.err.println(result+"result");
         return result;
    }
/*
*
* 此处上下两个方法查询的结果返回值一样  只是查询参数不同
* */
    @GetMapping("/lantu/salt/hmSelectLike")
    public String hmSelectLike(
             @RequestParam(required = false) String szoneName, @RequestParam(required = false) String sdomainName, @RequestParam(required = false) String productLine, @RequestParam(required = false) String productName, @RequestParam(required = false) String applicationName
    ){
        Map<String,String> params=new HashMap<>();
        String token="bearer "+hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);

        if(szoneName!=null){
            params.put("szoneName",szoneName);
        }
        if(sdomainName!=null){
            params.put("sdomainName",sdomainName);
        }
        if(productLine!=null){
            params.put("productLine",productLine);
        }
        if(productName!=null){
            params.put("productName",productName);
        }
        if(applicationName!=null){
            params.put("applicationName",applicationName);
        }

        // params.put("szoneName","souche");

        String result= HttpClientUtil.doGet("http://hongmao.souche-inc.com/aliyun/about/selectLike",params,headers);

        return result;
    }

    //https://hongmao.souche-inc.com/aliyun/aboutRedis/selectByAll?applicationName=guanghui-menu&szoneName=guanghui
    @GetMapping("/lantu/salt/hmAboutRedis")
    public String hmAboutRedis(
            @RequestParam(required = false) String szoneName, @RequestParam(required = false) String sdomainName, @RequestParam(required = false) String productLine, @RequestParam(required = false) String productName, @RequestParam(required = false) String applicationName
    ){
        Map<String,String> params=new HashMap<>();
        String token="bearer "+hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);

        if(szoneName!=null){
            params.put("szoneName",szoneName);
        }
        if(sdomainName!=null){
            params.put("sdomainName",sdomainName);
        }
        if(productLine!=null){
            params.put("productLine",productLine);
        }
        if(productName!=null){
            params.put("productName",productName);
        }
        if(applicationName!=null){
            params.put("applicationName",applicationName);
        }

        // params.put("szoneName","souche");

        String result= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/aboutRedis/selectByAll",params,headers);

        return result;
    }

//登陆后 把姓名放在跳session中，转前台
    @RequestMapping("/lantu/salt/login")
    public  void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         HttpSession session=request.getSession();
         //通过用户id去keyclock获得用户姓名  存放在session中
         String userId=request.getRemoteUser();
         String url="https://auth.souche-inc.com/auth/admin/realms/hongmao/users/"+userId;
         String token=getKeyClockToken();
         Map<String,String> headers=new HashMap<>();
         headers.put("Authorization",token);
         String result=HttpClientUtil.doGet(url,null,headers);
         JSONObject jsonObject=JSONObject.parseObject(result);
      //  System.err.println(jsonObject);
         try{
            // String username=jsonObject.get("lastName").toString()+jsonObject.get("firstName");
             String username=jsonObject.get("username").toString();
             session.setAttribute("username",username);
         }catch (Exception e){
            log.info("此用户没用姓名");
             e.printStackTrace();
         }
         response.sendRedirect(lantufe);
    }

//退出 系统
    @GetMapping("/lantu/logout")
    public  void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       // System.err.println(request.getSession().getAttribute("username"));
        request.logout();


    }

    @GetMapping("/lantu/salt/hmResourceIP")
    public  String hmResourceIP(String ip) {
        log.info(ip);
        Map<String,String> params=new HashMap<>();
        String token="bearer "+hmStableToken();
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);
        params.put("ip",ip);
        String result= HttpClientUtil.doGet("https://hongmao.souche-inc.com/aliyun/resource/selectResourceIP",params,headers);
        log.info(result);
        return result;
    }

    @PostMapping("/lantu/salt/json")
    public  void logout(  String name)  {
        System.err.println("123"+name);


    }

    //获得keyclock的管理员token
    @GetMapping("/lantu/salt/keyClockToken")
    public  String getKeyClockToken()  {
        Map<String,String> params=new HashMap<>();
        params.put("client_id", "admin-cli");
        params.put("username", "admin");
        params.put("password", "peppapig");
        params.put("grant_type", "password");
        String result= HttpClientUtil.doPost("https://auth.souche-inc.com/auth/realms/master/protocol/openid-connect/token",params);
        JSONObject jsonObject=JSONObject.parseObject(result);
        return "bearer "+jsonObject.get("access_token").toString();

    }






    public static void main(String[] args) {
        HongmaoController hongmaoController=new HongmaoController();
       // String res=hongmaoController.hmSelectLike("souche","",null,null,null);

        System.err.println(hongmaoController.ltToken("test","123"));






    }

}
