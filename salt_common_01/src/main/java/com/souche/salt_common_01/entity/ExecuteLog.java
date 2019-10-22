package com.souche.salt_common_01.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class ExecuteLog {
    private Integer id;

    private String missionname;

    private String user;

    private String account;

    private String server;

    private String serverId;


    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date starttime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endtime;

    private String totaltime;

    private String isjob;

    private String jobid;

    private Integer ordernum;

    private String scriptid;

    private String output;

    public String getServerId() {
        return serverId;
    }

    @Override
    public String toString() {
        return "ExecuteLog{" +
                "id=" + id +
                ", missionname='" + missionname + '\'' +
                ", user='" + user + '\'' +
                ", account='" + account + '\'' +
                ", server='" + server + '\'' +
                ", serverId='" + serverId + '\'' +
                ", status='" + status + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", totaltime='" + totaltime + '\'' +
                ", isjob='" + isjob + '\'' +
                ", jobid='" + jobid + '\'' +
                ", ordernum=" + ordernum +
                ", scriptid='" + scriptid + '\'' +
                ", output='" + output + '\'' +
                '}';
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMissionname() {
        return missionname;
    }

    public void setMissionname(String missionname) {
        this.missionname = missionname == null ? null : missionname.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server == null ? null : server.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

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
        this.totaltime = totaltime == null ? null : totaltime.trim();
    }

    public String getIsjob() {
        return isjob;
    }

    public void setIsjob(String isjob) {
        this.isjob = isjob == null ? null : isjob.trim();
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid == null ? null : jobid.trim();
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getScriptid() {
        return scriptid;
    }

    public void setScriptid(String scriptid) {
        this.scriptid = scriptid == null ? null : scriptid.trim();
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output == null ? null : output.trim();
    }
}
