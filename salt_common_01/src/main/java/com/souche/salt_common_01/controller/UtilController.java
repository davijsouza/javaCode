package com.souche.salt_common_01.controller;

import com.souche.salt_common_01.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UtilController {

    @GetMapping("/lantu/analyzCron")
    public String analyzCron(String crontxt){
        Map<String,String> params=new HashMap<>();
        Map<String,String> header=new HashMap<>();
        header.put("Accept","application/json, text/javascript, */*; q=0.01");
        header.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        header.put("Origin","http://www.bejson.com");
        header.put("Referer"," http://www.bejson.com/othertools/cronvalidate/");
        header.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3)");
        params.put("crontxt",crontxt);
        String result=HttpClientUtil.doPost("http://api.bejson.com/btools/othertools/cron",params,header);
        //System.err.println(result);
        return result;
    }

/*    @PostMapping("/lantu/getjson")
    public void getJson( @RequestBody TT tt){
        System.err.println(tt.getAge());
        System.err.println(tt.getName());
       // System.err.println(yz);
        //System.err.println(age);
    }

    @GetMapping("/lantu/getjson2")
    public void getJson2( @RequestBody List<String> arr){
        System.err.println(arr.size());

        // System.err.println(yz);
        //System.err.println(age);
    }*/

}
