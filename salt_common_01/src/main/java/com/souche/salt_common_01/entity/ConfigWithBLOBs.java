package com.souche.salt_common_01.entity;

public class ConfigWithBLOBs extends Config {
    private String configContent;

    private String version1;

    private String version2;

    private String version3;

    private String version4;

    private String version5;

    public String getConfigContent() {
        return configContent;
    }

    public void setConfigContent(String configContent) {
        this.configContent = configContent == null ? null : configContent.trim();
    }

    public String getVersion1() {
        return version1;
    }

    public void setVersion1(String version1) {
        this.version1 = version1 == null ? null : version1.trim();
    }

    public String getVersion2() {
        return version2;
    }

    public void setVersion2(String version2) {
        this.version2 = version2 == null ? null : version2.trim();
    }

    public String getVersion3() {
        return version3;
    }

    public void setVersion3(String version3) {
        this.version3 = version3 == null ? null : version3.trim();
    }

    public String getVersion4() {
        return version4;
    }

    public void setVersion4(String version4) {
        this.version4 = version4 == null ? null : version4.trim();
    }

    public String getVersion5() {
        return version5;
    }

    public void setVersion5(String version5) {
        this.version5 = version5 == null ? null : version5.trim();
    }
}