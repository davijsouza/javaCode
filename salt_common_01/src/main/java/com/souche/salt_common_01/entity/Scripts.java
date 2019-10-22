package com.souche.salt_common_01.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Scripts {
    private String id;

    private String name;

    private String creater;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String lastMofityUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastModifyTime;

    private String isCommon;

    private String idDeleted;

    private Integer orderNum;

    private String pause;

    private String saltSname;

    private String data1;

    private String data2;

    private String content;

    private List<String> vars;

    public List<String> getVars() {
        return vars;
    }

    public void setVars(List<String> vars) {
        this.vars = vars;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastMofityUser() {
        return lastMofityUser;
    }

    public void setLastMofityUser(String lastMofityUser) {
        this.lastMofityUser = lastMofityUser == null ? null : lastMofityUser.trim();
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getIsCommon() {
        return isCommon;
    }

    public void setIsCommon(String isCommon) {
        this.isCommon = isCommon == null ? null : isCommon.trim();
    }

    public String getIdDeleted() {
        return idDeleted;
    }

    public void setIdDeleted(String idDeleted) {
        this.idDeleted = idDeleted == null ? null : idDeleted.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getPause() {
        return pause;
    }

    public void setPause(String pause) {
        this.pause = pause == null ? null : pause.trim();
    }

    public String getSaltSname() {
        return saltSname;
    }

    public void setSaltSname(String saltSname) {
        this.saltSname = saltSname == null ? null : saltSname.trim();
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1 == null ? null : data1.trim();
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2 == null ? null : data2.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
