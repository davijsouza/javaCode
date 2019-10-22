package com.souche.salt_common_01.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RestUtil1 {

    public static  String load(String url,String query,String token)
    {
        try {


        URL restURL = new URL(url);
        /*
         * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类 的子类HttpURLConnection
         */
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        //请求方式
        conn.setRequestMethod("POST");
         //conn.setRequestProperty("Content-type","application/json");//
           // conn.setRequestProperty("Content-Type", "application/x-yaml;charset=utf-8");
             if(!url.contains("login")){
                //System.err.println("com in X0auth");
                conn.setRequestProperty("X-Auth-Token", token);
            }
        //设置是否从httpUrlConnection读入，默认情况下是true; httpUrlConnection.setDoInput(true);
        conn.setDoOutput(true);
        //allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
        conn.setAllowUserInteraction(false);

        PrintStream ps = new PrintStream(conn.getOutputStream());
        ps.print(query);

        ps.close();

        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

        String line,resultStr="";

        while(null != (line=bReader.readLine()))
        {
            resultStr +=line;
        }
        //System.out.println("3412412---"+resultStr);
        bReader.close();

        return resultStr;
        }catch (Exception e){
           // System.err.println("工具类中 设置请求头失败");
            e.printStackTrace();
        }
     return  null;
    }

    public static void main(String[] args) {

        //String token= RestUtil1.load("http://172.16.33.246:8000/login","username=salt-api&password=OgyT6aDGwQSB&eauth=pam","");
       // String token= RestUtil1.load("http://hongmao.souche-inc.com/aliyun/about/selectLike","instanceName=sc-prod-titan-00001","");
        String result=   RestUtil1.load("http://172.17.40.126:8888/login","username=salt&password=salt&eauth=pam","");

        System.err.println(result);
/*
       JSONObject ob= JSON.parseObject(token);
        System.err.println( ob.get("return").toString());
        JSONArray array = JSONArray.parseArray(ob.get("return").toString());
        Map<String, String> maps = (Map<String, String>) JSON.parse(array.get(0).toString());
        System.err.println(maps.get("token"));

*/

    }

}