package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.souche.salt_common_01.entity.*;
import com.souche.salt_common_01.mapper.CurrentMapper;
import com.souche.salt_common_01.mapper.ExecuteLogMapper;
import com.souche.salt_common_01.mapper.ScriptsMapper;
import com.souche.salt_common_01.utils.IDUtils;
import com.souche.salt_common_01.utils.TaotaoResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Component
@Slf4j
public class CurrnetFile {
    @Autowired
    private CurrentMapper currentMapper;
    @Autowired
    private ExecuteLogMapper executeLogMapper;
    @Autowired
    private ScriptsMapper scriptsMapper;
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
                    //获得变化部分的
                    randomFile.seek(lastTimeFileSize);
                    String tmp = "";
                    StringBuffer stringBuffer=new StringBuffer();
                    int k=0;
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
                            if (jobInfo.contains("new")){
                                String jobId=jobInfo.substring(jobInfo.indexOf("job")+4,jobInfo.indexOf("new")-1);
                                Map map= (Map) JSON.parse(paramInfo);
                                JSONArray jsonArray1=JSONArray.parseArray(map.get("arg").toString());
                                String loginName=null;
                                for(int p=0;p<jsonArray1.size();p++){
                                    if(jsonArray1.get(p).toString().contains("user")){
                                        loginName=jsonArray1.get(p).toString().substring(jsonArray1.get(p).toString().indexOf("=")+1);
                                    }
                                }
                                log.info(loginName);

                                JSONArray jsonArray2=JSONArray.parseArray(map.get("minions").toString());
                                //salt执行的脚本名称
                                  String saltscriptpath=jsonArray1.get(0).toString();
                                  String saltscriptId=saltscriptpath.substring(saltscriptpath.lastIndexOf("/")+1,saltscriptpath.lastIndexOf("."));
                                log.info(saltscriptId);
                                  //查询真正的脚本名称
                                ScriptsExample scriptsExample=new ScriptsExample();
                                scriptsExample.createCriteria().andSaltSnameEqualTo(saltscriptId);
                                log.info("准备查询真实脚本名");
                                List<Scripts>  scriptsList=scriptsMapper.selectByExampleWithBLOBs(scriptsExample);
                                System.err.println(scriptsList.size()+"=======scriptsList.size()");
                                if(scriptsList!=null&&scriptsList.size()>0){
                                Scripts script=scriptsList.get(0);
                                 Current current=new Current();
                                //current.setId(IDUtils.genImageName());
                                 current.setId(jobId);
                                 current.setNum(jsonArray2.size());
                                 current.setStartTime(new Date());
                                 current.setScript(script.getName());
                                 current.setContent(script.getContent());
                                 current.setUser(loginName);
                                try {
                                    log.info("即将 mapper  add");
                                    currentMapper.insert(current);
                                    log.info("  add  ok");
                                }catch (Exception e){
                                    System.err.println("id 重复");
                                    e.printStackTrace();
                                }
                                }else {
                                    System.err.println(scriptsList.size()+"=======不插入");
                                }
                                 //System.err.println("end....");
                            }else if(jobInfo.contains("ret")){
                                String jobId=jobInfo.substring(jobInfo.indexOf("job")+4,jobInfo.indexOf("ret")-1);
                                Current current=currentMapper.selectByPrimaryKey(jobId);
                                if(current!=null){
                                Integer num=current.getNum();
                                if (num<=1){
                                    currentMapper.deleteByPrimaryKey(jobId);
                                }else {
                                    current.setNum(current.getNum()-1);
                                    currentMapper.updateByPrimaryKey(current);
                                }
                               }

                            }
                            stringBuffer.setLength(0);

                        }


                    }
                    lastTimeFileSize = randomFile.length();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }




    }





