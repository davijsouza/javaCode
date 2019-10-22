package com.souche.salt_common_01.service;

import com.souche.salt_common_01.entity.ConfigWithBLOBs;

import java.util.List;
import java.util.Map;


public interface NginxService {
    void initialize( );

    List<Map<String, Object>> getInstanceConf(String tableData);

    String getSingleClientResult(String instanceId, String result);

    String updateContent(Integer id, String content, String operate);

    String addNginx(String instanceId, String name, String content, String path);

    List<ConfigWithBLOBs> queryConf(String content);

    Integer disableFile(Integer id, String ifreload);

    Integer effect(Integer id);

    //List<Map<String,Object>> queryConfig(String soneName, String applicationName, String instanceName);
}
