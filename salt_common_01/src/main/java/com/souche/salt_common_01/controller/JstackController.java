package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.service.JstackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class JstackController {
     @Autowired
     private JstackService jstackService;
    @GetMapping("/lantu/salt/jstack")
    public String getJstackInfo(String instanceId) {

        return jstackService.getInfo(instanceId);
    }

/*
    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {

        String fileName = "download.txt";// 文件名
        if (fileName != null) {

            //设置文件路径
            File file = new File("/tmp/c.txt");
            //File file = new File(realPath , fileName);
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
                        //System.err.println(i);
                    }
                    return "下载成功";
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
            }
        }
        return "下载失败";
    }*/

    @GetMapping("/lantu/salt/downloadJstack")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,String instanceId,String pid) {
           jstackService.downloadJstack(request,response,instanceId,pid);
        return null;
    }

    @GetMapping("/lantu/salt/downloadFile")
    public String downloadFile(HttpServletRequest request,String path, HttpServletResponse response,String instanceId) {
        jstackService.downloadFile(request,response,instanceId,path);
        return null;
    }




}
