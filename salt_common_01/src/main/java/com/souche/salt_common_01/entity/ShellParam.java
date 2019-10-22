package com.souche.salt_common_01.entity;

import lombok.Data;

import java.io.Serializable;


public class ShellParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private  String content;
    private  String vars;
    private  String account;
    private  String  logId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVars() {
        return vars;
    }

    public void setVars(String vars) {
        this.vars = vars;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }
}
