package com.souche.salt_common_01.entity;

import lombok.Data;

@Data
public class AddJob {
    private String jobName;
    private String user;
    private String script;
    private String isCommon;

    @Override
    public String toString() {
        return "AddJob{" +
                "jobName='" + jobName + '\'' +
                ", user='" + user + '\'' +
                ", script='" + script + '\'' +
                ", isCommon='" + isCommon + '\'' +
                '}';
    }

    public String getIsCommon() {
        return isCommon;
    }

    public void setIsCommon(String isCommon) {
        this.isCommon = isCommon;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
