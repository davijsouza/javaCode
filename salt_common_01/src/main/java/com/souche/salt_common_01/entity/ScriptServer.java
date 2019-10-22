package com.souche.salt_common_01.entity;

import lombok.Data;

@Data
public class ScriptServer {
    private Integer id;

    private String account;

    private String scriptId;

    private String instanceId;

    private String szoneName;

    private String sdoMainName;

    private String productLineName;

    private String productName;

    private String applicationName;

    private String instanceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getScriptId() {
        return scriptId;
    }

    public void setScriptId(String scriptId) {
        this.scriptId = scriptId == null ? null : scriptId.trim();
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
    }

    public String getSzoneName() {
        return szoneName;
    }

    public void setSzoneName(String szoneName) {
        this.szoneName = szoneName == null ? null : szoneName.trim();
    }

    public String getSdoMainName() {
        return sdoMainName;
    }

    public void setSdoMainName(String sdoMainName) {
        this.sdoMainName = sdoMainName == null ? null : sdoMainName.trim();
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName == null ? null : productLineName.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName == null ? null : applicationName.trim();
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName == null ? null : instanceName.trim();
    }
}
