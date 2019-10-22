package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.controller.HongmaoController;
import com.souche.salt_common_01.controller.RewriteController;
import com.souche.salt_common_01.entity.Rewrite;
import com.souche.salt_common_01.service.RewriteService;
import com.souche.salt_common_01.utils.HttpClientUtil;
import com.souche.salt_common_01.utils.RestUtil1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RewriterServiceImpl implements RewriteService {
    private static  String targetPath="/srv/salt/scripts/";
    /*线上环境*/
    private static final String SALT_API_URL = "http://172.16.33.246:8000";

    @Autowired
    private HongmaoController hongmaoController;
    /*测试环境*/
   // private static final String SALT_API_URL = "http://172.17.40.126:8888";

    @Override
    public String doWriter(String applicationName,
                           String path,
                           String content,
                           String szoneName) {

        /*取得要执行的主机*/
        Map<String,String> param=new HashMap<>();
         StringBuffer servers=new StringBuffer();
        param.put("applicationName",applicationName);
        if(szoneName==null||szoneName.equals("")){
            param.put("szoneName","souche");
        }else{
            param.put("szoneName",szoneName);
        }
        if(applicationName==null){
            System.err.println("应用名没接到值");
            return "应用名没接到值";
        }
       //获得token
        String hmtoken="bearer "+hongmaoController.hmStableToken();
        System.err.println(hmtoken);
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",hmtoken);





         String result= HttpClientUtil.doGet(
                 "https://hongmao.souche-inc.com/aliyun/about/selectLike",param,headers);
        System.err.println(result+"result");
        JSONArray jsonArray=JSONArray.parseArray(result);
        for (int i=0;i<jsonArray.size();i++){
            JSONObject object=JSONObject.parseObject(jsonArray.get(i).toString());
            if (i==jsonArray.size()-1){
                servers.append(object.get("instanceId"));
            }else{
                servers.append(object.get("instanceId")).append(",");
            }
        }
        System.err.println(servers.toString());

        /*线上登陆salt*/
        String loginResult= RestUtil1.load(SALT_API_URL+"/login","username=salt-api&password=OgyT6aDGwQSB&eauth=pam","aa");

        //测试环境登陆salt
       // String loginResult= RestUtil1.load(SALT_API_URL+"/login","username=salt&password=salt&eauth=pam","aa");
        JSONObject ob= JSON.parseObject(loginResult);
        JSONArray jsonarray = JSONArray.parseArray(ob.get("return").toString());
        Map<String, String> resultmap = (Map<String, String>) JSON.parse(jsonarray.get(0).toString());
        String token=resultmap.get("token");
        System.err.println("token---"+token);



        /*把脚本变成文件存在硬盘中*/

        String target = targetPath + "rewrite869524835" + ".sh";
        String script="echo \'"+content+"\' > "+path+" ";
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileOutputStream(new File(target)), true);
            printWriter.println(script);
        } catch (FileNotFoundException e) {
            System.err.println("新建脚本  写出到磁盘失败");
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        /*拼接调用接口需要的参数*/
         StringBuffer query = new StringBuffer();
        query.append("client=").append("local&");
        query.append("tgt=").append(servers).append("&");
        query.append("fun=cmd.script&");
        query.append("arg=").append("salt://scripts/rewrite869524835.sh&");
        query.append("tgt_type=list");

        System.err.println(query.toString());
        String scriptResults=null;
        try{
            scriptResults = RestUtil1.load(SALT_API_URL, query.toString(),token);
        }catch (Exception e){
            System.err.println("salt执行脚本发生错误");
            e.printStackTrace();
        }

        return scriptResults;
    }

    @Override
    public String doStableWriter(Rewrite rewrite) {

        List<String> instanceNames=rewrite.getInstanceNames();
        if(instanceNames==null||instanceNames.size()<=0){
            return  "";
        }
        StringBuffer servers=new StringBuffer();
        for (int i=0;i<instanceNames.size();i++){

            if (i==instanceNames.size()-1){
                servers.append(instanceNames.get(i));
            }else{
                servers.append(instanceNames.get(i)).append(",");
            }
        }
        String loginResult= RestUtil1.load("http://salt-api.k8s.dev.dasouche.net/login","username=salt-api&password=gLZe47ALWgXv0gFp&eauth=pam","aa");
        //测试环境登陆salt
        // String loginResult= RestUtil1.load(SALT_API_URL+"/login","username=salt&password=salt&eauth=pam","aa");
        JSONObject ob= JSON.parseObject(loginResult);
        JSONArray jsonarray = JSONArray.parseArray(ob.get("return").toString());
        Map<String, String> resultmap = (Map<String, String>) JSON.parse(jsonarray.get(0).toString());
        String token=resultmap.get("token");
        System.err.println("token---"+token);

        /*把脚本变成文件存在硬盘中*/

        String target = "/srv/k8s-salt/rewrite869524835.sh";
        String script="echo \'"+rewrite.getContent()+"\' > "+rewrite.getPath()+" ";
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileOutputStream(new File(target)), true);
            printWriter.println(script);
        } catch (FileNotFoundException e) {
            System.err.println("新建脚本  写出到磁盘失败");
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        /*拼接调用接口需要的参数*/
        StringBuffer query = new StringBuffer();
        query.append("client=").append("local&");
        query.append("tgt=").append(servers).append("&");
        query.append("fun=cmd.script&");
        query.append("arg=").append("salt://rewrite869524835.sh&");
        query.append("tgt_type=list");
        System.err.println(query.toString());
        String scriptResults=null;
        try{
            scriptResults = RestUtil1.load("http://salt-api.k8s.dev.dasouche.net", query.toString(),token);
        }catch (Exception e){
            System.err.println("salt执行脚本发生错误");
            e.printStackTrace();
        }
        return scriptResults;
    }
}
