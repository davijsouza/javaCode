package com.souche.salt_common_01.entity;

import lombok.Data;

import java.util.List;

@Data
public class Rewrite {
    private String applicationName ;
    private List<String> instanceNames ;
   private String path;
   private String content;
   private String szoneName;

    public String getSzoneName() {
        return szoneName;
    }

    public void setSzoneName(String szoneName) {
        this.szoneName = szoneName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
