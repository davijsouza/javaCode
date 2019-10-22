package com.souche.salt_common_01.entity;

import java.util.List;
import java.util.Map;

public class SonJob extends Job {
     public List<String> getScriptIds() {
        return scriptIds;
    }

    public void setScriptIds(List<String> scriptIds) {
        this.scriptIds = scriptIds;
    }

     private List<String> scriptIds;

    private Map<String,String> scriptIdAndContent;

    public Map<String, String> getScriptIdAndContent() {
        return scriptIdAndContent;
    }

    public void setScriptIdAndContent(Map<String, String> scriptIdAndContent) {
        this.scriptIdAndContent = scriptIdAndContent;
    }

    @Override
    public String toString() {
        return "SonJob{" +
                "scriptIds=" + scriptIds +
                ", scriptIdAndContent=" + scriptIdAndContent +
                '}';
    }
}
