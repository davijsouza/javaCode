package com.souche.salt_common_01.entity;

import java.util.List;
import java.util.Map;

public class ScriptSon extends Scripts{
    private String account;

    private List<Map<String,String>> servers;



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<Map<String, String>> getServers() {
        return servers;
    }

    public void setServers(List<Map<String, String>> servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        return "ScriptSon{" +
                "account='" + account + '\'' +
                ", servers=" + servers +
                '}';
    }
}
