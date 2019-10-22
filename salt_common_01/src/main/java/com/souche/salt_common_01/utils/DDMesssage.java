package com.souche.salt_common_01.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;

import com.dingtalk.api.request.OapiRobotSendRequest;

import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;


public class DDMesssage {




    public  static String TMessage(String DDurl, String messageUrl, String message,String title) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(DDurl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl(messageUrl);
        link.setPicUrl("");
        link.setTitle(title);
        link.setText(message);
        request.setLink(link);
        OapiRobotSendResponse response = client.execute(request);
        return null;
     }

    public static void main(String[] args) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=579ae43300d58867b1025167a0be29fe1ae75ee9ac776f96ab8b9f698b2b80ba");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("1");
        link.setPicUrl("1");
        link.setTitle("1");
        link.setText("1");
        request.setLink(link);
        OapiRobotSendResponse response = client.execute(request);
    }


    /*
    public String TMessage(String url,String message) throws ApiException {
      Map<String,String>  headers=new HashMap<>();
         headers.put("Content-Type","application/json");
        Map<String,Object> params=new HashMap<>();
        Map<String,Object> params2=new HashMap<>();
        params.put("msgtype","text");
        params2.put("content","contenttest");
        params.put("text",params2);
        System.err.println(JSON.toJSONString(params));
        HttpClientUtil.doPostJson(url,JSON.toJSONString(params));


     *//*   DingTalkClient client = new DefaultDingTalkClient(url);
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("link");
        msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
        msg.getLink().setTitle("title");
        msg.getLink().setText("java 调用测试");
        msg.getLink().setMessageUrl("https://lantu.souche-inc.com/#/executeLog");
       // msg.getLink().setPicUrl("test");
        request.setMsg(msg);
        String ass="we";
        OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request,"180af836f7e0b4652eb2f4e50f32fc7c57e6b965d987efbf40748ca159b833c7");
        *//*
     return null;
     }

    public static void main(String[] args) throws ApiException {
        //https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2
        DDMesssage ddMesssage=new DDMesssage();
        ddMesssage.TMessage("https://oapi.dingtalk.com/robot/send?access_token=180af836f7e0b4652eb2f4e50f32fc7c57e6b965d987efbf40748ca159b833c7"
                ,"{'content':'java 测试 https://lantu.souche-inc.com/#/executeLog'}");
    }*/
}
