package com.souche.salt_common_01.swagger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.controller.HongmaoController;
import com.souche.salt_common_01.utils.*;
import com.souche.salt_common_01.utils.Date;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Jsontest {
    private long lastTimeFileSize = 0;
    public void realtimeShowLog(File logFile) throws IOException{
        lastTimeFileSize=logFile.length();
        //指定文件可读可写
        final RandomAccessFile randomFile = new RandomAccessFile(logFile,"rw");
        //启动一个线程每10秒钟读取新增的日志信息
        ScheduledExecutorService exec =
                Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(new Runnable(){
            public void run() {
                try {
                    System.err.println(lastTimeFileSize+"====size");
                    //获得变化部分的
                    randomFile.seek(lastTimeFileSize);
                    String tmp = "";
                    StringBuffer stringBuffer=new StringBuffer();
                    while( (tmp = randomFile.readLine())!= null) {

                        String tepString=new String(tmp.getBytes("ISO8859-1"));
                        stringBuffer.append(tepString).append("\n");
                        if(tepString.equals("}")){
                            Integer index=stringBuffer.toString().indexOf("{");
                            if(index==-1){
                                continue;
                            }
                            String jobInfo=stringBuffer.substring(0,index);
                            String paramInfo=stringBuffer.substring(index);
                           // System.out.println(jobInfo);
                            //System.out.println(paramInfo);
                            if (jobInfo.contains("new")){
                                String jobId=jobInfo.substring(jobInfo.indexOf("job")+4,jobInfo.indexOf("new")-1);
                                System.err.println(jobId);
                                Map map= (Map) JSON.parse(paramInfo);
                                System.err.println(map.get("minions"));
                                JSONArray jsonArray1=JSONArray.parseArray(map.get("arg").toString());
                               // System.err.println(jsonArray1.get(0));
                                JSONArray jsonArray2=JSONArray.parseArray(map.get("minions").toString());
                                //System.err.println(jsonArray2.size());
                            }else if(jobInfo.contains("ret")){
                                String jobId=jobInfo.substring(jobInfo.indexOf("job")+4,jobInfo.indexOf("ret")-1);

                            }


                            stringBuffer.setLength(0);

                        }

                    }
                    lastTimeFileSize = randomFile.length();
                    // lastTimeFileSize = randomFile.getFilePointer();
                } catch (Exception e) {
                    //throw new RuntimeException(e);
                    try {
                        lastTimeFileSize = randomFile.getFilePointer();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
/*
* Jsontest view = new Jsontest();
        final File tmpLogFile = new File("/tmp/saltlog.txt");
        System.err.println(tmpLogFile.length());
        view.realtimeShowLog(tmpLogFile);
* */
    public static void main(String[] args) throws Exception {
        //int logId= Integer.parseInt(String.valueOf(IDUtils.genImageName()));
        long ll = System.currentTimeMillis();
        System.err.println(ll);
        //int ii = (int)ll;
        int ii= new Long(ll).intValue();
        System.err.println(ii);

    }

    public static String doPost(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
               // logger.error("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public TaotaoResult getInfo(){


        return TaotaoResult.ok();
    }

    }





