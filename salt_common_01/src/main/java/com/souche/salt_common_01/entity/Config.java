package com.souche.salt_common_01.entity;

public class Config {
    private Integer id;

    private String instanceId;

    private String configPath;

    private String configName;

    private String verTime1;

    private String verTime2;

    private String verTime3;

    private String verTime4;

    private String verTime5;

    private String crearer;

    private String modify1;

    private String modify2;

    private String modify3;

    private String modify4;

    private String modify5;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath == null ? null : configPath.trim();
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public String getVerTime1() {
        return verTime1;
    }

    public void setVerTime1(String verTime1) {
        this.verTime1 = verTime1 == null ? null : verTime1.trim();
    }

    public String getVerTime2() {
        return verTime2;
    }

    public void setVerTime2(String verTime2) {
        this.verTime2 = verTime2 == null ? null : verTime2.trim();
    }

    public String getVerTime3() {
        return verTime3;
    }

    public void setVerTime3(String verTime3) {
        this.verTime3 = verTime3 == null ? null : verTime3.trim();
    }

    public String getVerTime4() {
        return verTime4;
    }

    public void setVerTime4(String verTime4) {
        this.verTime4 = verTime4 == null ? null : verTime4.trim();
    }

    public String getVerTime5() {
        return verTime5;
    }

    public void setVerTime5(String verTime5) {
        this.verTime5 = verTime5 == null ? null : verTime5.trim();
    }

    public String getCrearer() {
        return crearer;
    }

    public void setCrearer(String crearer) {
        this.crearer = crearer == null ? null : crearer.trim();
    }

    public String getModify1() {
        return modify1;
    }

    public void setModify1(String modify1) {
        this.modify1 = modify1 == null ? null : modify1.trim();
    }

    public String getModify2() {
        return modify2;
    }

    public void setModify2(String modify2) {
        this.modify2 = modify2 == null ? null : modify2.trim();
    }

    public String getModify3() {
        return modify3;
    }

    public void setModify3(String modify3) {
        this.modify3 = modify3 == null ? null : modify3.trim();
    }

    public String getModify4() {
        return modify4;
    }

    public void setModify4(String modify4) {
        this.modify4 = modify4 == null ? null : modify4.trim();
    }

    public String getModify5() {
        return modify5;
    }

    public void setModify5(String modify5) {
        this.modify5 = modify5 == null ? null : modify5.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}