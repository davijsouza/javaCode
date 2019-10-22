package com.souche.salt_common_01.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class JobScriptLog {
        private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        private Date starttime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
         private Date endtime;

        private String totaltime;

        private Integer serverNum;

        private Integer ordernum;

        private String scriptName;
        private  String scriptContent;
        private  String scriptId;
        private  String JobLogId;
        //private String isJob;

  /*  public String getIsJob() {
        return isJob;
    }

    public void setIsJob(String isJob) {
        this.isJob = isJob;
    }*/



    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public Integer getServerNum() {
        return serverNum;
    }

    public void setServerNum(Integer serverNum) {
        this.serverNum = serverNum;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    @Override
    public String toString() {
        return "JobScriptLog{" +
                "status='" + status + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", totaltime='" + totaltime + '\'' +
                ", serverNum=" + serverNum +
                ", ordernum=" + ordernum +
                ", scriptName='" + scriptName + '\'' +
                '}';
    }
}
