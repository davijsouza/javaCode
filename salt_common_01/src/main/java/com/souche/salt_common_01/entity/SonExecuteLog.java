package com.souche.salt_common_01.entity;

import java.util.List;

public class SonExecuteLog extends ExecuteLog{
    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

    private List<String> servers;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    private String script;
    @Override
    public String toString() {
        return "SonExecuteLog{" +
                "servers=" + servers +script+
                '}'+super.toString();
    }
}
