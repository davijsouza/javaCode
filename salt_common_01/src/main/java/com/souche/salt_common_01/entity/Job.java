package com.souche.salt_common_01.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Job {
    private String id;

    private String scriptid;

    private String name;

    private String sort;

    private  String isCommon;

    private String creater;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    private String lastmodifyuser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastmodifytime;

    public String getIsCommon() {
        return isCommon;
    }

    public void setIsCommon(String isCommon) {
        this.isCommon = isCommon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getScriptid() {
        return scriptid;
    }

    public void setScriptid(String scriptid) {
        this.scriptid = scriptid == null ? null : scriptid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLastmodifyuser() {
        return lastmodifyuser;
    }

    public void setLastmodifyuser(String lastmodifyuser) {
        this.lastmodifyuser = lastmodifyuser == null ? null : lastmodifyuser.trim();
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }


    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", scriptid='" + scriptid + '\'' +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                ", creater='" + creater + '\'' +
                ", createtime=" + createtime +
                ", lastmodifyuser='" + lastmodifyuser + '\'' +
                ", lastmodifytime=" + lastmodifytime +
                '}';
    }
}