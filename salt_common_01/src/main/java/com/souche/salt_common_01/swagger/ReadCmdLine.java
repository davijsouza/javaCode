package com.souche.salt_common_01.swagger;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCmdLine {
    public static void main(String args[]) {
        Process process = null;
        List<String> processList = new ArrayList<String>();
        try {
//            process = Runtime.getRuntime().exec("echo 'hello world!'");
//            process = Runtime.getRuntime().exec("echo 'hello world!'");
            process = Runtime.getRuntime().exec("/Users/dasouche/E/lm.sh");

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.err.println(process.waitFor());
            String line = "";
            while ((line = input.readLine()) != null) {
                processList.add(line);
                //System.out.println(line);
//                io.write('result.txt')
                 // TODO: 2019-06-18 每次输出的结果请求post到server，server存储到本地日志文件，前端展示到时候，另外到接口去读取日志文件，存关联关系，作业（脚本）和日志（返回结果）
                // 客户端每秒往server端post日志，server端在查看时每秒查看本地日志
               // request http://172.16.33.246:9997/api/ret?type=*&status=running&....
            }

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // created(){}
        // mount
        // destroyed() {websocket wss://}
        for (String line : processList) {
            System.out.println(line);
        }
    }


}
