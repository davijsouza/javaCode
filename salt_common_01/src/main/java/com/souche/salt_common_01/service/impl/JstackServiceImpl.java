package com.souche.salt_common_01.service.impl;

import com.alibaba.fastjson.JSON;
import com.souche.salt_common_01.service.ClientService;
import com.souche.salt_common_01.service.JstackService;
import com.souche.salt_common_01.service.NginxService;
import com.souche.salt_common_01.service.ScriptsService;
import com.souche.salt_common_01.utils.RestUtil1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class JstackServiceImpl implements JstackService {
    @Value("${salt.api.url}")
    private   String SALT_API_URL;
    @Autowired
    private ClientService clientService;
    @Autowired
    private NginxService nginxService;
    @Autowired
    private  ScriptsService scriptsService;
    @Override
    public String getInfo(String instanceId) {
      String saltResult=  clientService.doClient("ps aux | grep java | grep -v grep",instanceId,"souche",null);
      String stdout=nginxService.getSingleClientResult(instanceId,saltResult);
        List<Map<String,String>> list=new ArrayList<>();
        String[]  jstackArr=stdout.split("\\n");
        for (String jstack:jstackArr
             ) {
            Map<String,String> map=new HashMap<>();
            if(jstack.startsWith("souche")){
                String[] arr=jstack.split("\\s+");
                for (int i=0;i<arr.length;i++){
                    if(arr[i].contains("-DAPP_NAME")){
                        map.put("head",arr[i]);
                    }
                }
                if(map.get("head")==null||"".equals(map.get("head"))){
                    StringBuffer sb=new StringBuffer();
                    for (int i=0;i<arr.length;i++){
                        if(i>6&&i<15){
                             sb.append(arr[i]).append(" ");
                        }
                    }
                    map.put("head",jstack);
                }
                log.info(arr[1]);
                String script="sudo -u souche /opt/souche/java/bin/jstack "+arr[1];
                log.info(script);
                String jstackResult=  clientService.doClient(script,instanceId,"scops",null);
                String jstackstdout=nginxService.getSingleClientResult(instanceId,jstackResult);
                map.put("body",jstackstdout);
                map.put("pid",arr[1]);
                map.put("instanceId",instanceId);
                list.add(map);
            }
        }
        return JSON.toJSONString(list );
    }

    String transmitClient(String instanceId,String path){
        String token=clientService.getSaltToken();
        StringBuffer query = new StringBuffer();
        query.append("client=").append("local&");
        // query.append("tgt=").append("172.17.40.134").append("&");
        query.append("tgt=").append(instanceId).append("&");

        query.append("fun=cp.push&");
        query.append("arg=").append(path+"&");
        query.append("arg='runas=scops'");
        log.info("query====="+query);
        String scriptResults = RestUtil1.load(SALT_API_URL, query.toString(),token);
        log.info("querscriptResultsy====="+scriptResults);
        return  scriptResults;
    }

    void download(String instanceId,String path,HttpServletResponse response){
        String fileName = "jstack.txt";
        if (fileName != null) {
            //设置文件路径
            String realPath = "/var/cache/salt/master/minions/"+instanceId+"/files"+path;
            log.info("realPath"+realPath);
            //File file = new File(realPath , tarfile);
            File file = new File(realPath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else {
                System.out.println("error");
            }
        }
    }

    @Override
    public void downloadJstack(HttpServletRequest request, HttpServletResponse response, String instanceId, String pid) {
        String token=clientService.getSaltToken();
        //客户端机器生成文件
        String loginName= scriptsService.getLoginName();
        String tarfile="/tmp/"+loginName+"_jstack";
        String script="sudo -u souche /opt/souche/java/bin/jstack "+pid+" >"+tarfile;
        log.info(script);
        clientService.doClient(script,instanceId,"scops",null);
        //传输文件
        StringBuffer query = new StringBuffer();
         query.append("client=").append("local&");
        // query.append("tgt=").append("172.17.40.134").append("&");
         query.append("tgt=").append(instanceId).append("&");

        query.append("fun=cp.push&");
        query.append("arg=").append(tarfile+"&");
        query.append("arg='runas=scops'");
        log.info("query====="+query);
        String scriptResults = RestUtil1.load(SALT_API_URL, query.toString(),token);
        log.info("querscriptResultsy====="+scriptResults);
        //读取文件
        String fileName = "jstack.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
             String realPath = "/var/cache/salt/master/minions/"+instanceId+"/files"+tarfile;
             log.info("realPath"+realPath);
             //File file = new File(realPath , tarfile);
             File file = new File(realPath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else {
                System.out.println("error");
            }
        }
    }

    @Override
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String instanceId, String path) {

    }
}
